package ch.heigvd.gamification.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_model")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Application application;

    // Represent the user id in the remote application which call us
    // Example, in pinterest your username is mra94 and in WP2 mra. This permits to map to a unique Id to the remote service.
    @Column
    private String remoteUserId;

    @OneToMany(mappedBy = "user")
    private List<Reward> reward;

    @ManyToMany(mappedBy = "users")
    private List<Badge> badges;

    public User() {
        this.reward = new ArrayList<>();
        this.badges = new ArrayList<>();
    }

    public User(Application application, String remoteUserId) {
        this.application = application;
        this.remoteUserId = remoteUserId;
    }

    public User(long userId, String remoteUserId, Application app) {
        this.id = userId;
        this.remoteUserId = remoteUserId;
        this.application = app;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Application getApplicationModel() {
        return application;
    }

    public void setApplication(Application applicationModel) {
        this.application = applicationModel;
    }

    public String getRemoteUserId() {
        return remoteUserId;
    }

    public void setRemoteUserId(String remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    public List<Reward> getAwardModels() {
        return reward;
    }

    public void setAwardModels(List<Reward> rewardModels) {
        this.reward = rewardModels;
    }

    public List<Badge> getBadgeModels() {
        return badges;
    }

    public void setBadgeModels(List<Badge> badgeModels) {
        this.badges = badgeModels;
    }
}