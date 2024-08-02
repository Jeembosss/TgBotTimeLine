package tg.bot.timeLine.TelegramBotTimeLine.JSONParse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;
import tg.bot.timeLine.TelegramBotTimeLine.db.service.RepositoryService;

public class JsonToObject {

    public Event JsonToEvent(String json, String chatId) {
        ObjectMapper mapper = new ObjectMapper();
        Event event;
        try {
            event = mapper.readValue(json, Event.class);
            return event;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
