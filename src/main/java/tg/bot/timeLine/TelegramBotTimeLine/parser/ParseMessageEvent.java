package tg.bot.timeLine.TelegramBotTimeLine.parser;

import lombok.extern.log4j.Log4j2;
import tg.bot.timeLine.TelegramBotTimeLine.bot.MyBot;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;

@Log4j2
public class ParseMessageEvent {
    private MyBot myBot;

    public ParseMessageEvent(MyBot myBot) {
        this.myBot = myBot;
    }

    public Event eventDetails(String message, User userByChatId) {
//        try {
//            String[] columns = message.split(",");
//            String eventName = sanitizeInput(columns[0].trim());
//            String startTimeStr = sanitizeInput(columns[1].trim());
//            LocalTime startTime = parseTime(startTimeStr);
//            if (columns.length == 3) {
//                LocalTime endTime = null;
//            } else if (columns.length == 4) {
//                String endTimeStr = sanitizeInput(columns[2].trim());
//                endTime = parseTime(endTimeStr);
//            }  else {
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    private void messageProcessing() {
    }
}
