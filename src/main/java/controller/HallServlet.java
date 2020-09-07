package controller;


import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.json.*;
import persistence.PsqlStore;
import service.JsonGenerator;
import service.Parser;
import service.Place;
import service.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class is a Hall servlet
 *
 * @author Денис Висков
 * @version 1.0
 * @since 03.09.2020
 */
@WebServlet("/index")
public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Parser parser = new JsonGenerator(PsqlStore.instOf());
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        JSONObject result = parser.aggregatePlace();
        writer.print(result);
        writer.flush();
    }
}
