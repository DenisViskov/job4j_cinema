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
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher(req.getContextPath() + "/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private JSONArray aggregatePlace() {
        JSONArray result = new JSONArray();
        Store store = PsqlStore.instOf();
        List<Place> hall = new ArrayList<>(store.getHall());
        for (int i = 0; i< store.getRows().size();i++){

        }
        return result;
    }
}
