package com.nevercaution.demoinflearnapi.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// beanSerializer 객체를 사용해서 serialize 한다.
public class EventResource extends EntityModel<Event> {

    public EventResource(Event event, Link... links) {
        super(event, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }

//    @JsonUnwrapped // serialize 할 때 event 로 감싸지 않고 풀어주고 싶을 때 사용.
//    private Event event;
//
//    public EventResource(Event event) {
//        this.event = event;
//        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
//    }
//
//    public Event getEvent() {
//        return event;
//    }
}
