package service;

import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public class Place {
    /**
     * ID
     */
    private final int id;
    /**
     * Row
     */
    private final int row;
    /**
     * Seat
     */
    private final int seat;
    /**
     * User
     */
    private final User user;

    public Place(int id, int row, int seat, User user) {
        this.id = id;
        this.row = row;
        this.seat = seat;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                row == place.row &&
                seat == place.seat &&
                Objects.equals(user, place.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, seat, user);
    }
}
