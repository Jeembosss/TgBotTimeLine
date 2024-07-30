package tg.bot.timeLine.TelegramBotTimeLine.enums;

import lombok.Getter;

@Getter
public enum BotCommandsList {
    START("/start", "start message"),
    ADD_EVENT("/add_event", "add event for organise day"),
    ON_REMINDERS("/start_reminder" , "start the reminders"),
    OFF_REMINDERS("/stop_reminder", "start the reminders"),
    LIST_OF_REMINDERS("/show_list_reminders", "all list of reminders"),
    CLEAR_LIST("/clear_list" , "clear all events"),
    REDACT_LIST("/redact_list", "redact existing events");


    private final String command;
    private final String description;

    BotCommandsList(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public static BotCommandsList fromString(String command) {
        for (BotCommandsList botCommandsList : BotCommandsList.values()) {
            if (botCommandsList.getCommand().equalsIgnoreCase(command)) {
                return botCommandsList;
            }
        }
        throw new IllegalArgumentException("No enum constant for command: " + command);
    }

}
