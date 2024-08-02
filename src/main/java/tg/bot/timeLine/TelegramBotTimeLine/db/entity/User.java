package tg.bot.timeLine.TelegramBotTimeLine.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import tg.bot.timeLine.TelegramBotTimeLine.enums.userEnums.UserState;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    @Enumerated(EnumType.STRING)
    private UserState  userState;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    public User() {
        this.userState = UserState.NONE;
    }

    public User(String chatId) {
        this.chatId = chatId;
        this.userState = UserState.NONE;
    }

}
