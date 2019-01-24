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
@Table(name = "application")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 45, unique = true)
    private String name;

    @Column(length = 45)
    private String description;

    @Column
    private String appKey;

    @Column
    private String appToken;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "application")
    private List<Badge> badges = new ArrayList<>();

    @OneToMany(mappedBy = "application")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "application")
    private List<PointScale> pointScales = new ArrayList<>();

    public Application() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<PointScale> getPointScales() {
        return pointScales;
    }

    public void setPointScales(List<PointScale> pointScales) {
        this.pointScales = pointScales;
    }
}
