package service;

import java.util.Collection;

/**
 * Interface of store
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public interface Store {
    /**
     * Method should return places from store
     *
     * @return Collection<Place>
     */
    Collection<Place> getHall();

    /**
     * Method should add place to store
     *
     * @param place
     */
    void addHall(Place place);

    /**
     * Method should add user to store
     *
     * @param user
     * @return User
     */
    User addUser(User user);
}
