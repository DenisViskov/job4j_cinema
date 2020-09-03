package persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Place;
import service.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Properties;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
public class PsqlStore implements Store<Place> {

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
    public Collection<Place> findFreePlaces() {
        return null;
    }
}
