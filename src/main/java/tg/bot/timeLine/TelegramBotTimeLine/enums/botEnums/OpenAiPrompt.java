package tg.bot.timeLine.TelegramBotTimeLine.enums.botEnums;

public enum OpenAiPrompt {

    PROMPT_PARSE_EVENT_MESSAGE("received an SMS from the user, sms template:\n" +
            "a certain action that he must perform\n" +
            "is the start time, the action that he must perform\n" +
            "is the end time when he can stop performing " +
            "(optionally, that is, this parameter may not exist, since for example, the user can write: go to bed at 22:00, " +
            "and he will not need the end time of the action), and the last point, " +
            "these are the days on which he wants SMS notifications to arrive, in string format..." +
            "but he can also write \"every day\", this will also work, send me a JSON file with this data\n" +
            ", if the SMS that came contains the wrong information, that is, you will not be able to parse it" +
            " according to such a template, then return JSON with error" +
            "\n Message:\n");


    OpenAiPrompt(String prompt) {
    }
}
