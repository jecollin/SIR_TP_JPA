package jpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TicketComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private LocalDateTime timestamp;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private User user;

    public TicketComment() {
    }

    public TicketComment(String text, LocalDateTime timestamp, Ticket ticket, User user) {
        this.text = text;
        this.timestamp = timestamp;
        this.ticket = ticket;
        this.user = user;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}