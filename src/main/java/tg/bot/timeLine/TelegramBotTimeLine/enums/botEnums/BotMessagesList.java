package tg.bot.timeLine.TelegramBotTimeLine.enums.botEnums;

import lombok.Getter;

@Getter
public enum BotMessagesList {
    START_MESSAGE("Hi, i'm your discipline helper bot!" +
            "\nYou can organise your day,and your life" +
            "\nYou need to remember - discipline is really important" +
            "\ndon't forgive,and you stay powerful and happy" +
            "\n I hope, command on this bot is simple for understanding" +
            "\n Good luck, champion"),
    ADD_EVENT_MESSAGE("Write your event from format with commas: " +
            "\n some text event , time to start , time to end (optional), and days when you need this " +
            "\n For example: " +
            "\n I need learn english, 10:00, 11:00, Monday Wednesday Friday" +
            "\n or without time end " +
            "\nI need sleep, 22:00 , every day"),
    ERROR_FORMAT_ADD_EVENT_MESSAGE("Incorrect input format. Please follow the template:\n"+
            " description of the event, start time, [end time], days of the week.\n"+
            "Example: 'Learn English, 10:00, 11:00, Monday Wednesday Friday' or 'Sleep, 22:00, every day'");


    private final String message;

    BotMessagesList(String message) {
        this.message = message;
    }

}
