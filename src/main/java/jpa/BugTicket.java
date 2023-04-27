package jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("BUG")
public class BugTicket extends Ticket {

    private String stepsToReproduce;

    private String expectedBehavior;

    private String actualBehavior;

    public BugTicket() {
        super();
    }

    public BugTicket(String title, String body, String status, String priority, List<String> tags, User owner, User assignedTo, String stepsToReproduce, String expectedBehavior, String actualBehavior) {
        super(title, body, status, priority, tags, owner, assignedTo);
        this.stepsToReproduce = stepsToReproduce;
        this.expectedBehavior = expectedBehavior;
        this.actualBehavior = actualBehavior;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public String getExpectedBehavior() {
        return expectedBehavior;
    }

    public void setExpectedBehavior(String expectedBehavior) {
        this.expectedBehavior = expectedBehavior;
    }

    public String getActualBehavior() {
        return actualBehavior;
    }

    public void setActualBehavior(String actualBehavior) {
        this.actualBehavior = actualBehavior;
    }
}
