package org.example.cart;

import org.example.cart.Util.ItemDTO;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/admin")
public class Admin extends HttpServlet {

    List<ItemDTO> items;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        items = Connector.selectItem();
        request.setAttribute("items", items);
        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
        rd.forward(request, response);
    }

    public void destroy() {
    }
}