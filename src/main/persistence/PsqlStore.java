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

    @Override
    public User findUserByID(int id) {
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
        return result;
    }
}
