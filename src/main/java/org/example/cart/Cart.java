package org.example.cart;

import org.example.cart.Util.Order;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "cart", value = "/cart")
public class Cart extends HttpServlet {
    List<Order> items;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        items = Connector.joinSelect("cart", "store", "cart.itemID = store.itemID");
        request.setAttribute("items", items);
        System.out.println(items);
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}