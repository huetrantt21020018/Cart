package org.example.cart;

import org.example.cart.Util.Item;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "store", value = "/store")
public class Store extends HttpServlet {
    List<Item> items;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        items = Connector.select("store");
        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("store.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}