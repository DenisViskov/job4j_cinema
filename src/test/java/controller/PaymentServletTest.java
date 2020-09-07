package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import persistence.PsqlStore;
import service.Store;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PaymentServletTest {

    @Test
    public void doPostTest() throws IOException, ServletException {
        Store store = mock(Store.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        mockStatic(PsqlStore.class);
        when(res.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
        when(PsqlStore.instOf()).thenReturn(store);
        when(req.getParameter("row")).thenReturn("1");
        when(req.getParameter("seat")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("phone")).thenReturn("phone");
        new PaymentServlet().doPost(req, res);
        verify(store).addHall(any());
        verify(res).getOutputStream();
    }
}