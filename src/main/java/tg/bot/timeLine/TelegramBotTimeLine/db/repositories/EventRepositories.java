package tg.bot.timeLine.TelegramBotTimeLine.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.Event;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;

import java.util.List;

@Repository
public interface EventRepositories extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
}
