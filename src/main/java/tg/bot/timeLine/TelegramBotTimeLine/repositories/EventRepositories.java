package tg.bot.timeLine.TelegramBotTimeLine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.bot.timeLine.TelegramBotTimeLine.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.entity.User;

import java.util.List;

@Repository
public interface EventRepositories extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
}
