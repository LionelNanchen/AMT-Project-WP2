package ch.heigvd.gamification.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "point_scale")
public class PointScale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @ManyToOne
    private Application application;

    public PointScale(){}

    public PointScale(String type, Application app){
        this.name = type;
        this.application = app;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}