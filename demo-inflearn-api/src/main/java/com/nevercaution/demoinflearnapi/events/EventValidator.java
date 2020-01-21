package com.nevercaution.demoinflearnapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            errors.reject("wrongPrices", "Values of prices are wrong");  // global error
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
        endEventDateTime.isBefore(eventDto.getCloseEnrollmentDatetime()) ||
        endEventDateTime.isBefore(eventDto.getBeginEnrollmentDatetime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrong.");  // field error
        }

        // TODO: beginEventDateTime
        // TODO: closeEnrollmentDateTime
    }
}
