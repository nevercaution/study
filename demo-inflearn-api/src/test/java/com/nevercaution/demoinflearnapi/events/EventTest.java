package com.nevercaution.demoinflearnapi.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("inflearn spring rest api")
                .description("rest api development with spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    @Test
    @Parameters//(method = "parametersForTestFree")
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    /**
     * parametersFor 가 prefix 이다.
     * 원래는 해당 메소드 사용시에 @Parameters(method = "parametersForTestFree")
     * 라고 명시를 해줘야 하지만 컨벤션이므로 생략이 가능하다.
     * @return
     */
    private Object[] parametersForTestFree() {
        return new Object[] {
                new Object[] {0, 0, true},
                new Object[] {100, 0, false},
                new Object[] {0, 100, false},
                new Object[] {100, 200, false}
        };
    }

    @Test
    @Parameters(method = "parametersForTestOffline")
    public void testOffline(String location, boolean isOffline) {
        // Given
        Event event = Event.builder()
                .location(location)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private Object[] parametersForTestOffline() {
        return new Object[] {
                new Object[] {"Gangnam station", true},
                new Object[] {null, false},
                new Object[] {"         ", false}
        };
    }

}