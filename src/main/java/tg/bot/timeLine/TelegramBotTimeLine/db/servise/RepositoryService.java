package tg.bot.timeLine.TelegramBotTimeLine.db.servise;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;
import tg.bot.timeLine.TelegramBotTimeLine.db.repositories.EventRepositories;
import tg.bot.timeLine.TelegramBotTimeLine.db.repositories.UserRepositories;

import java.util.List;

@Service
@Getter
public class RepositoryService {
    private final UserRepositories userRepository;
    private final EventRepositories eventRepository;
    @Autowired
    public RepositoryService(UserRepositories userRepository, EventRepositories eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public User getUserByChatId(String chatId) {
        return userRepository.findByChatId(chatId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.findByUser(user);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
