package tg.bot.timeLine.TelegramBotTimeLine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String date;
    private String timeStart;
    private String timeEnd;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Event() {
    }

    public Event(String description, String date, User user, String timeStart) {
        this.description = description;
        this.date = date;
        this.user = user;
        this.timeStart = timeStart;
        timeEnd = "";
    }

    public Event(String description, String date, User user, String timeStart, String timeEnd) {
        this.description = description;
        this.date = date;
        this.user = user;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

}
