package tg.bot.timeLine.TelegramBotTimeLine.commands;

import lombok.extern.log4j.Log4j2;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import tg.bot.timeLine.TelegramBotTimeLine.bot.MyBot;
import tg.bot.timeLine.TelegramBotTimeLine.entity.User;
import tg.bot.timeLine.TelegramBotTimeLine.enums.BotMessagesList;
import tg.bot.timeLine.TelegramBotTimeLine.servise.RepositoryService;


@Log4j2
@Service
public class CommandsLogic {
    private final MyBot myBot;
    private final RepositoryService repositoryService;

    public CommandsLogic(MyBot myBot) {
        this.myBot = myBot;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        repositoryService = context.getBean(RepositoryService.class);
    }


    public void startCommand(String chatId) {
        if (repositoryService.getUserRepository().findByChatId(chatId) == null) {
            User newUser = new User();
            newUser.setChatId(chatId);
            repositoryService.saveUser(newUser);
        }
        myBot.sendMessage(new SendMessage(chatId,BotMessagesList.START_MESSAGE.getMessage()));
    }

    public void addEventCommand(String chatId) {

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

    }
}
