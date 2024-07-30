package tg.bot.timeLine.TelegramBotTimeLine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.bot.timeLine.TelegramBotTimeLine.entity.User;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
    User findByChatId(String chatId);
}
