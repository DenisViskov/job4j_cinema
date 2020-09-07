package controller;

import org.json.JSONObject;
import persistence.PsqlStore;
import service.Place;
import service.Store;
import service.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 06.09.2020
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = PsqlStore.instOf();
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        int row = Integer.valueOf(req.getParameter("row"));
        int seat = Integer.valueOf(req.getParameter("seat"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        Place place = new Place(0, row, seat, new User(0, name, phone));
        store.addHall(place);
        JSONObject json = new JSONObject();
        json.put("result", "done");
        writer.println(json);
        writer.flush();
    }
}
