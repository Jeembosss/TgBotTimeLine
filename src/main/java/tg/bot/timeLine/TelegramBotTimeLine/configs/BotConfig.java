package tg.bot.timeLine.TelegramBotTimeLine.configs;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import tg.bot.timeLine.TelegramBotTimeLine.bot.MyBot;

@Getter
@Configuration
@ComponentScan(basePackages = "tg.bot.timeLine.TelegramBotTimeLine")
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);  // Создаем новый экземпляр TelegramBotsApi
        telegramBotsApi.registerBot(telegramBot());  // Регистрируем нашего бота
        return telegramBotsApi;  // Возвращаем экземпляр TelegramBotsApi
    }

    @Bean
    public MyBot telegramBot() {
        return new MyBot();  // Возвращаем новый экземпляр нашего бота с заданными параметрами
    }

}
