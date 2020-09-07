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
        if (hall.isEmpty()) {
            return ifHallIsEmpty(store);
        }
        return ifHallIsNotEmpty(store);
    }

    /**
     * Method generates places in case when hall is not empty
     *
     * @param store
     * @return JSONObject
     */
    private JSONObject ifHallIsNotEmpty(Store store) {
        JSONObject result = ifHallIsEmpty(store);
        List<Place> hall = new ArrayList<>(store.getHall());
        hall.forEach(place -> {
            JSONObject elem = result.getJSONObject(String.valueOf(place.getRow()));
            elem.put(String.valueOf(place.getSeat()), false);
            result.put(String.valueOf(place.getRow()), elem);
        });
        return result;
    }

    /**
     * Method generates places in case when hall is empty
     *
     * @param store
     * @return JSONObject
     */
    private JSONObject ifHallIsEmpty(Store store) {
        JSONObject result = new JSONObject();
        for (int i = 0; i < store.getRows().size(); i++) {
            int row = i + 1;
            JSONObject jsonObject = new JSONObject();
            store.getSeats()
                    .forEach(seat -> jsonObject.put(String.valueOf(seat), true));
            result.put(String.valueOf(row), jsonObject);
        }
        return result;
    }
}
