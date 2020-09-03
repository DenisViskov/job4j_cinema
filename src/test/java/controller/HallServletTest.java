package controller;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class HallServletTest {

    @Test
    public void doGetTest() throws ServletException, IOException {
        new HallServlet().doGet(null,null);
    }
}