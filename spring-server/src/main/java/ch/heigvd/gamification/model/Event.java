package ch.heigvd.gamification.model;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String type;

    @Column
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Property> properties;

    public Event() {
        this.properties = new ArrayList<>();
    }

    public int getQuantityFromProperties() {
        try {
            for (Property property : this.properties) {
                if (property.getName().toLowerCase().equals("quantity"))
                    return Integer.parseInt(property.getValue());
            }
        } catch(NumberFormatException exception) {
            return 1;
        }
        return 1;
    }

    public boolean checkProperties(List<RuleCondition> conditions) {
        for (RuleCondition condition : conditions) {
            if (!checkProperties(condition))
                return false;
        }
        return true;
    }

    public boolean checkProperties(RuleCondition condition) {
        Property property = this.getPropertyByName(condition.getKey());
        if (property == null)
            return false;
        
        switch (condition.getOperator()) {
            case "==":
                return property.getValue().compareTo(condition.getValue()) == 0;
            case "!=":
                return property.getValue().compareTo(condition.getValue()) != 0;
            case "<":
                return property.getValue().compareTo(condition.getValue()) < 0;
            case ">":
                return property.getValue().compareTo(condition.getValue()) > 0;
        }
        return true;
    }

    public Property getPropertyByName(String name) {
        for (Property property : this.properties) {
            if (property.getName().equals(name))
                return property;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}