package persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Place;
import service.Store;
import service.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Class is a PsqlStore
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public class PsqlStore implements Store {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class);

    /**
     * Pool of connection
     */
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    /**
     * Singleton initialization
     */
    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    /**
     * Method returns store instance
     *
     * @return store
     */
    public static Store instOf() {
        return Lazy.INST;
    }

    /**
     * Method of return places from DB
     *
     * @return collection
     */
    @Override
    public Collection<Place> getHall() {
        List<Place> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM halls");
            while (rs.next()) {
                result.add(new Place(rs.getInt("id"),
                        rs.getInt("rowID"),
                        rs.getInt("seatID"),
                        findUserByID(rs.getInt("accountID"))));
            }
        } catch (SQLException throwables) {
            LOG.error(throwables.getSQLState(), throwables);
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * Method of return rows from hall
     *
     * @return collection
     */
    @Override
    public Collection<Integer> getRows() {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM rows");
            while (rs.next()) {
                result.add(rs.getInt("row"));
            }
        } catch (SQLException throwables) {
            LOG.error(throwables.getSQLState(), throwables);
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * Method of return seats from rows
     *
     * @return collection
     */
    @Override
    public Collection<Integer> getSeats() {
        List<Integer> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM seats");
            while (rs.next()) {
                result.add(rs.getInt("seat"));
            }
        } catch (SQLException throwables) {
            LOG.error(throwables.getSQLState(), throwables);
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * Method of looking for user by given id
     *
     * @param id
     * @return user
     */
    private User findUserByID(int id) {
        User result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE id=(?)")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"));
            }
        } catch (SQLException throwables) {
            LOG.error(throwables.getSQLState(), throwables);
            throwables.printStackTrace();
        }
        if (result == null) {
            throw new NullPointerException("User not found");
        }
        return result;
    }

    /**
     * Method add new bought place to DB
     *
     * @param place
     */
    @Override
    public void addHall(Place place) {
        User user = addUser(place.getUser());
        place.setUser(user);
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO halls(rowID,seatID,accountID) VALUES (?,?,?)")) {
            ps.setInt(1, place.getRow());
            ps.setInt(2, place.getSeat());
            ps.setInt(3, user.getId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method add user to DB
     *
     * @param user
     * @return user
     */
    @Override
    public User addUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO accounts(name,phone) VALUES (?,?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getNumber());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys();) {
                while (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
