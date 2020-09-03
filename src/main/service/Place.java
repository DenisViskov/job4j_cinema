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

    public Place(int id, int row, int seat) {
        this.id = id;
        this.row = row;
        this.seat = seat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return id == place.id &&
                row == place.row &&
                seat == place.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, seat);
    }
}
