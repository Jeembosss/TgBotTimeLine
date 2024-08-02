package tg.bot.timeLine.TelegramBotTimeLine.bot;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.bot.timeLine.TelegramBotTimeLine.commands.CommandsLogic;
import tg.bot.timeLine.TelegramBotTimeLine.enums.botEnums.BotCommandsList;
import tg.bot.timeLine.TelegramBotTimeLine.db.service.RepositoryService;
import tg.bot.timeLine.TelegramBotTimeLine.enums.userEnums.UserState;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Getter
@Component
public class MyBot extends TelegramLongPollingBot {
    private final RepositoryService repositoryService;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.username}")
    private String botUsername;

    private final ApplicationContext context;

    public MyBot(ApplicationContext context, RepositoryService repositoryService) {
        this.context = context;
        this.repositoryService = repositoryService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            HandlersIncomingMessages(update, chatId);
        } else if (update.hasCallbackQuery()) {
            String chatIdCallBack = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
            handleCallBackQuery(update, chatIdCallBack);
        }
    }

    private void HandlersIncomingMessages(Update update, String chatId) {
        String messageText = update.getMessage().getText();

        CommandsLogic command = new CommandsLogic(this, context.getBean(RepositoryService.class));
        if (repositoryService.getUserByChatId(chatId) != null) {
            switch (repositoryService.getUserStateByChatId(chatId)) {
                case ADDING_EVENT -> command.catchMessage(chatId, messageText);
            }
            return;
        }

        switch (BotCommandsList.fromString(messageText)) {
            case START -> command.startCommand(chatId);
            case ADD_EVENT -> command.addEventCommand(chatId);
            case ON_REMINDERS -> command.onReminders(chatId);
            case OFF_REMINDERS -> command.offReminders(chatId);
            case LIST_OF_REMINDERS -> command.listReminders(chatId);
            case CLEAR_LIST -> command.clearList(chatId);
            case REDACT_LIST -> command.redactList(chatId);
            default -> command.errorCommand(chatId);
        }
    }

    public void handleCallBackQuery(Update update, String chatId) {
        String callbackQuery = update.getCallbackQuery().getData();
        switch (callbackQuery) {
            case "cansel_to_add_event" -> {
                repositoryService.updateUserState(chatId, UserState.NONE);
                sendMessage(new SendMessage(chatId, "Cansel success"));
            }
        }
        sendMessage(new AnswerCallbackQuery(update.getCallbackQuery().getId()));

    }

    private void sendMessage(AnswerCallbackQuery answerCallbackQuery) {
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(BotApiMethodMessage text) {
        try {
            execute(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InlineKeyboardMarkup createButtonsKeyBoard(String[] name, String[] callbackData) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        if (name.length == callbackData.length) {
            for (int i = 0; i < name.length; i++) {
                row.add(createOneButton(name[i], callbackData[i]));
            }
        }
        rows.add(row);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    public InlineKeyboardButton createOneButton(String name, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(name);
        button.setCallbackData(callbackData);
        return button;
    }

}
