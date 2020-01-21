package com.nevercaution.demoinflearnapi.events;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDatetime;
    private LocalDateTime endEnrollmentDatetime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrolment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;

}
