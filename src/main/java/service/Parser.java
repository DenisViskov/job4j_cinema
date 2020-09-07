package service;

import org.json.JSONObject;

/**
 * Interface of parse
 *
 * @author Денис Висков
 * @version 1.0
 * @since 07.09.2020
 */
public interface Parser {
    /**
     * Method should parse places from storage and put they to JSONObject
     *
     * @return JSONObject
     */
    JSONObject aggregatePlace();
}
