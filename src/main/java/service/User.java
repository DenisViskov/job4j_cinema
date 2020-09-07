package service;

import java.util.Objects;

/**
 * Class of user
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public class User {
    /**
     * ID
     */
    private int id;
    /**
     * Name
     */
    private final String name;
    /**
     * Phone number
     */
    private final String number;

    public User(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(number, user.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number);
    }
}
