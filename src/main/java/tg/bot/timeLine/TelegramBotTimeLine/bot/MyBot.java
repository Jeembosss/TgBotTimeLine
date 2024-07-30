package tg.bot.timeLine.TelegramBotTimeLine.bot;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.bot.timeLine.TelegramBotTimeLine.commands.CommandsLogic;
import tg.bot.timeLine.TelegramBotTimeLine.enums.BotCommandsList;
import tg.bot.timeLine.TelegramBotTimeLine.db.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Getter
public class MyBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.username}")
    private String botUsername;

    @Autowired
    private ApplicationContext context;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            HandlersIncomingMessages(update);
        }
    }

    private void HandlersIncomingMessages(Update update) {
        String messageText = update.getMessage().getText();
        String chatId = String.valueOf(update.getMessage().getChatId());

        CommandsLogic command = new CommandsLogic(this, context.getBean(RepositoryService.class));

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

    public void sendMessage(SendMessage text) {
        try {
            execute(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
