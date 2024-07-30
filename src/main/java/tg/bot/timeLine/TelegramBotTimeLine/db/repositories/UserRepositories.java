package tg.bot.timeLine.TelegramBotTimeLine.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.bot.timeLine.TelegramBotTimeLine.db.entity.User;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
    User findByChatId(String chatId);
}
