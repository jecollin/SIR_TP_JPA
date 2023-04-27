package jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("FEATURE_REQUEST")
public class FeatureRequestTicket extends Ticket {

    private String requestedFeature;

    public FeatureRequestTicket() {
        super();
    }

    public FeatureRequestTicket(String title, String body, String status, String priority, List<String> tags, User owner, User assignedTo, String requestedFeature) {
        super(title, body, status, priority, tags, owner, assignedTo);
        this.requestedFeature = requestedFeature;
    }

    public String getRequestedFeature() {
        return requestedFeature;
    }

    public void setRequestedFeature(String requestedFeature) {
        this.requestedFeature = requestedFeature;
    }
}
