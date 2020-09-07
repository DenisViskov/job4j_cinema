package service;

import java.util.Collection;

/**
 * Интерфейс реализующий способность
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public interface Store {
    Collection<Place> getHall();

    Collection<Integer> getRows();

    Collection<Integer> getSeats();

    void addHall(Place place);

    User addUser(User user);
}
