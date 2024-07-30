package tg.bot.timeLine.TelegramBotTimeLine.enums;

import lombok.Getter;

@Getter
public enum BotMessagesList {
    START_MESSAGE("Hi, i'm your discipline helper bot!" +
            "\nYou can organise your day,and your life" +
            "\nYou need to remember - discipline is really important" +
            "\ndon't forgive,and you stay powerful and happy" +
            "\n I hope, command on this bot is simple for understanding" +
            "\n Good luck, champion");


    private final String message;

    BotMessagesList(String message) {
        this.message = message;
    }

}
