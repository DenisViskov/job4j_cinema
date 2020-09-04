package controller;


import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.json.*;
import persistence.PsqlStore;
import service.Place;
import service.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
@WebServlet("/index")
public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        aggregatePlace();
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private JSONObject aggregatePlace() {
        Store store = PsqlStore.instOf();
        List<Place> hall = new ArrayList<>(store.getHall());
        if (hall.isEmpty()) {
            return ifHallIsEmpty(store);
        }
        return ifHallIsNotEmpty(store);
    }

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
