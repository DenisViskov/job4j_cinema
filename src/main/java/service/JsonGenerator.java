package service;

import org.json.JSONObject;
import persistence.PsqlStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Class is a json generator
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.09.2020
 */
public class JsonGenerator implements Parser {

    /**
     * Store
     */
    private final Store store;

    public JsonGenerator(Store store) {
        this.store = store;
    }

    /**
     * Method generate places in json
     *
     * @return JSONObject
     */
    @Override
    public JSONObject aggregatePlace() {
        List<Place> hall = new ArrayList<>(store.getHall());
        JSONObject result = new JSONObject();
        hall.forEach(elem -> {
            result.put(String.valueOf(elem.getRow()), new JSONObject());
        });
        hall.forEach(elem -> {
            JSONObject seat = result.getJSONObject(String.valueOf(elem.getRow()));
            if (elem.getUser() != null) {
                seat.put(String.valueOf(elem.getSeat()), false);
            } else {
                seat.put(String.valueOf(elem.getSeat()), true);
            }
        });
        return result;
    }
}
