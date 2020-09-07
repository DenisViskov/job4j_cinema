package service;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonGeneratorTest {

    @Test
    public void whenWeHavePersonsTest() {
        Store store = mock(Store.class);
        JSONObject result = new JSONObject();
        JSONObject seatFirst = new JSONObject();
        JSONObject seatSecond = new JSONObject();
        JSONObject seatThird = new JSONObject();
        seatFirst.put("1", false);
        seatFirst.put("2", true);
        seatFirst.put("3", true);
        seatSecond.put("1", true);
        seatSecond.put("2", false);
        seatSecond.put("3", true);
        seatThird.put("1", true);
        seatThird.put("2", true);
        seatThird.put("3", false);
        result.put("1", seatFirst);
        result.put("2", seatSecond);
        result.put("3", seatThird);
        JsonGenerator generator = new JsonGenerator(store);
        when(store.getRows()).thenReturn(List.of(1, 2, 3));
        when(store.getSeats()).thenReturn(List.of(1, 2, 3));
        when(store.getHall()).thenReturn(List.of(new Place(1, 1, 1, null),
                new Place(2, 2, 2, null),
                new Place(3, 3, 3, null)));
        JSONObject out = generator.aggregatePlace();
        assertThat(out.toString(), is(result.toString()));
    }

    @Test
    public void whenWeDontHavePersonsTest() {
        Store store = mock(Store.class);
        JSONObject result = new JSONObject();
        JSONObject seatFirst = new JSONObject();
        JSONObject seatSecond = new JSONObject();
        JSONObject seatThird = new JSONObject();
        seatFirst.put("1", true);
        seatFirst.put("2", true);
        seatFirst.put("3", true);
        seatSecond.put("1", true);
        seatSecond.put("2", true);
        seatSecond.put("3", true);
        seatThird.put("1", true);
        seatThird.put("2", true);
        seatThird.put("3", true);
        result.put("1", seatFirst);
        result.put("2", seatSecond);
        result.put("3", seatThird);
        JsonGenerator generator = new JsonGenerator(store);
        when(store.getRows()).thenReturn(List.of(1, 2, 3));
        when(store.getSeats()).thenReturn(List.of(1, 2, 3));
        when(store.getHall()).thenReturn(Collections.emptyList());
        JSONObject out = generator.aggregatePlace();
        assertThat(out.toString(), is(result.toString()));
    }

}