package tg.bot.timeLine.TelegramBotTimeLine.commands;

import lombok.extern.log4j.Log4j2;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import tg.bot.timeLine.TelegramBotTimeLine.JSONParse.JsonToObject;
import tg.bot.timeLine.TelegramBotTimeLine.bot.MyBot;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;
import tg.bot.timeLine.TelegramBotTimeLine.db.service.OpenAIService;
import tg.bot.timeLine.TelegramBotTimeLine.enums.botEnums.BotMessagesList;
import tg.bot.timeLine.TelegramBotTimeLine.db.service.RepositoryService;
import tg.bot.timeLine.TelegramBotTimeLine.enums.userEnums.UserState;


@Log4j2
@Service
public class CommandsLogic {
    private final MyBot myBot;
    private final RepositoryService repositoryService;
    @Autowired
    private OpenAIService openAIService;

    public CommandsLogic(MyBot myBot, RepositoryService repositoryService) {
        this.myBot = myBot;
        this.repositoryService = repositoryService;
    }


    public void startCommand(String chatId) {
        myBot.sendMessage(new SendMessage(chatId, BotMessagesList.START_MESSAGE.getMessage()));
        if (repositoryService.getUserRepository().findByChatId(chatId) == null) {
            User newUser = new User(chatId);
            repositoryService.saveUser(newUser);
        }
    }

    public void addEventCommand(String chatId) {
        SendMessage message = new SendMessage(chatId, BotMessagesList.ADD_EVENT_MESSAGE.getMessage());
        message.setReplyMarkup(myBot.createButtonsKeyBoard(new String[]{"Cansel"}, new String[]{"cansel_to_add_event"}));
        myBot.sendMessage(message);
        repositoryService.getUserByChatId(chatId).setUserState(UserState.ADDING_EVENT);

        repositoryService.updateUserState(chatId, UserState.ADDING_EVENT);
    }

    public void catchMessage(String chatId, String message) {
        JsonToObject jsonToObject = new JsonToObject();

        Event event = jsonToObject.JsonToEvent(openAIService.processEvent(message), chatId);
        
//        repositoryService.updateUserState(chatId, UserState.NONE);
        //не забыть юсерстейт поменять тут или в майбот в ифе самом
        //добавить в бд
    }


    public void onReminders(String chatId) {

    }

    public void offReminders(String chatId) {

    }

    public void listReminders(String chatId) {
    }

    public void clearList(String chatId) {

    }

    public void redactList(String chatId) {

    }

    public void errorCommand(String chatId) {
        myBot.sendMessage(new SendMessage(chatId, "error command"));
    }
}
