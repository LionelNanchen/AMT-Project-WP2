package ch.heigvd.gamification.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Amrani Kamil, Nanchen Lionel, Nicole Olivier, Reka Mentor
 * AMT WP2 2018-2019
 */
@Entity
@Table(name = "user")
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
    private List<Reward> rewards;

    @ManyToMany(mappedBy = "users")
    private List<Badge> badges;

    public User() {
        this.rewards = new ArrayList<>();
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getRemoteUserId() {
        return remoteUserId;
    }

    public void setRemoteUserId(String remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    public List<Reward> getReward() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}