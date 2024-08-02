package tg.bot.timeLine.TelegramBotTimeLine.configs;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tg.bot.timeLine.TelegramBotTimeLine.bot.MyBot;

@Getter
@Configuration
public class BotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(MyBot myBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);  // Создаем новый экземпляр TelegramBotsApi
        telegramBotsApi.registerBot(myBot);  // Регистрируем нашего бота
        return telegramBotsApi;  // Возвращаем экземпляр TelegramBotsApi
    }
}
