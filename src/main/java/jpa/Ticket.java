package jpa;

import javax.persistence.*;
import java.util.List;


@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private String status;

    private String priority;

    @ElementCollection
    private List<String> tags;

    @ManyToOne
    private User owner;

    @ManyToOne
    private User assignedTo;

    @OneToMany(mappedBy = "ticket")
    private List<TicketComment> comments;

    public Ticket() {
    }

    public Ticket(String title, String body, String status, String priority, List<String> tags, User owner, User assignedTo) {
        this.title = title;
        this.body = body;
        this.status = status;
        this.priority = priority;
        this.tags = tags;
        this.owner = owner;
        this.assignedTo = assignedTo;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }


    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", owner=" + owner.getName() +
                ", assignedTo=" + assignedTo.getName() +
                '}';
    }


    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}