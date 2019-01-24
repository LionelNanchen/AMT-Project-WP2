package ch.heigvd.gamification.util;

import ch.heigvd.gamification.api.dto.*;
import ch.heigvd.gamification.api.dto.BadgesResponseDTO;
import ch.heigvd.gamification.api.dto.PointScaleIdDTO;
import ch.heigvd.gamification.api.dto.RuleResponseDTO;
import ch.heigvd.gamification.model.*;

import java.util.ArrayList;

public class ModelToDTOConverter {


    // BADGES
    public static BadgesResponseDTO convert(Badge badge) {
        ch.heigvd.gamification.api.dto.BadgesResponseDTO output = new BadgesResponseDTO();
        output.setDescription(badge.getDescription());
        output.setName(badge.getName());
        output.setId(badge.getId());
        return output;
    }

    // EVENT
    public static Event convert(ch.heigvd.gamification.api.dto.EventDTO eventDTO) {
        Event output = new Event();
        output.setType(eventDTO.getType());
        output.setTimestamp(eventDTO.getTimestamp().toLocalDateTime());
        output.setProperties(new ArrayList<>());
        if (eventDTO.getProperties() != null) {
            for(ch.heigvd.gamification.api.dto.ValueDTO property : eventDTO.getProperties()) {
                Property prop = ModelToDTOConverter.convert(property);
                output.getProperties().add(prop);
                prop.setEvent(output);
            }
        }

        return output;
    }

    // POINTSCALE
    public static PointScaleIdDTO convert(PointScale pointScale) {
        ch.heigvd.gamification.api.dto.PointScaleIdDTO output = new PointScaleIdDTO();
        output.setPointScaleName(pointScale.getName());
        output.setPointScaleId(pointScale.getId());
        return output;
    }

    // PROPERTY
    public static Property convert(ch.heigvd.gamification.api.dto.ValueDTO value) {
        Property output = new Property();
        output.setName(value.getKey());
        output.setValue(value.getValue());
        return output;
    }

    // RULE
    public static Rule convert(ch.heigvd.gamification.api.dto.RuleDTO ruleDTO) {
        Rule output = new Rule();
        output.setConditions(new ArrayList<>());
        output.setName(ruleDTO.getName());
        output.setQuantity(ruleDTO.getQuantity());
        output.setType(ruleDTO.getType());

        if (ruleDTO.getConditions() != null) {
            for(ch.heigvd.gamification.api.dto.ConditionDTO condition : ruleDTO.getConditions()) {
                output.getConditions().add(convert(condition));
            }
        }
        return output;
    }


    public static RuleCondition convert(ch.heigvd.gamification.api.dto.ConditionDTO conditionDTO) {
        RuleCondition output = new RuleCondition();
        output.setKey(conditionDTO.getKey());
        output.setOperator(conditionDTO.getOperator());
        output.setValue(conditionDTO.getValue());
        return output;
    }

    public static RuleResponseDTO convert(Rule rule) {
        ch.heigvd.gamification.api.dto.RuleResponseDTO output = new RuleResponseDTO();
        output.setBadge(convert(rule.getBadge()));
        output.setId(rule.getId());
        output.setName(rule.getName());
        output.setPointScale(convert(rule.getPointScale()));
        output.setQuantity(rule.getQuantity());
        output.setType(rule.getType());
        return output;
    }
}
