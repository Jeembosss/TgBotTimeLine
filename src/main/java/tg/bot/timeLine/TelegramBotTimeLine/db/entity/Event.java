package tg.bot.timeLine.TelegramBotTimeLine.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("event")
    private String description;
    @JsonProperty("timeStart")
    private LocalTime timeStart;
    @JsonProperty("timeEnd")
    private LocalTime timeEnd;
    @JsonProperty("days")
    private String days;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Event() {
    }

    public Event(String description, User user, LocalTime timeStart, String days) {
        this.description = description;
        this.user = user;
        this.timeStart = timeStart;
        this.days = days;
        timeEnd = null;
    }

    public Event(String description, User user, LocalTime timeStart, LocalTime timeEnd, String days) {
        this.description = description;
        this.user = user;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.days = days;
    }

}
