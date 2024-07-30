package tg.bot.timeLine.TelegramBotTimeLine.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    public User() {
    }

    public User(String chatId) {
        this.chatId = chatId;
    }
}
