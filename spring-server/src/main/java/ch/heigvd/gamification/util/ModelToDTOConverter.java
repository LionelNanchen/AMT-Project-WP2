package ch.heigvd.gamification.util;

import ch.heigvd.gamification.api.dto.*;
import ch.heigvd.gamification.model.*;

import java.util.ArrayList;

public class ModelToDTOConverter {

    public static BadgesResponseDTO convert(Badge badge) {
        BadgesResponseDTO br = new BadgesResponseDTO();
        br.setName(badge.getName());
        br.setDescription(badge.getDescription());
        br.setId(badge.getId());
        return br;
    }

    public static PointScaleSummaryDTO convert(PointScale pointScale) {
        PointScaleSummaryDTO rs = new PointScaleSummaryDTO();
        rs.setPointScaleId(pointScale.getId());
        rs.setPointScaleName(pointScale.getName());

        return rs;
    }

    // Rule ---> RuleResponseDTO
    // Rule <--- RuleResponseDTO
    public static RuleResponseDTO convert(Rule rule) {
        RuleResponseDTO rr = new RuleResponseDTO();
        rr.setQuantity(rule.getQuantity());
        rr.setType(rule.getType());
        rr.setName(rule.getName());
        rr.setId(rule.getId());
        rr.setBadge(convert(rule.getBadge()));
        rr.setPointScale(convert(rule.getPointScale()));
        return rr;
    }

    public static Rule convert(RuleDTO ruleDTO) {
        Rule rm = new Rule();
        rm.setType(ruleDTO.getType());
        rm.setQuantity(ruleDTO.getQuantity());
        rm.setName(ruleDTO.getName());
        rm.setConditions(new ArrayList<>());
        if (ruleDTO.getConditions() == null)
            return rm;
        for(ConditionDTO condition : ruleDTO.getConditions()) {
            rm.getConditions().add(convert(condition));
        }
        return rm;
    }

    public static RuleCondition convert(ConditionDTO conditionDTO) {
        RuleCondition rc = new RuleCondition();
        rc.setKey(conditionDTO.getKey());
        rc.setOperator(conditionDTO.getOperator());
        rc.setValue(conditionDTO.getValue());
        return rc;
    }


    // EVENT
    public static Event convert(EventDTO eventDTO)
    {
        Event event = new Event();
        event.setType(eventDTO.getType());
        event.setTimestamp(eventDTO.getTimestamp().toLocalDateTime());
        event.setProperties(new ArrayList<>());
        if (eventDTO.getProperties() != null) {
            for(ValueDTO property : eventDTO.getProperties()) {
                Property prop = ModelToDTOConverter.convert(property);
                event.getProperties().add(prop);
                prop.setEvent(event);
            }
        }

        return event;
    }

    // Property
    public static Property convert(ValueDTO value) {
        Property property = new Property();
        property.setName(value.getKey());
        property.setValue(value.getValue());
        return property;
    }
}
