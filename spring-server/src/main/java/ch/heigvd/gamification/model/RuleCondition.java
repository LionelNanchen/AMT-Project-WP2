package ch.heigvd.gamification.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rules_conditions")
public class RuleCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "rule_key")
    private String key;

    @Column(name = "rule_operator")
    private String operator;

    @Column(name = "rule_value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    private Rule rule;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
