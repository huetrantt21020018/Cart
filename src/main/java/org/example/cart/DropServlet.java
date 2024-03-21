package org.example.cart;

import org.example.cart.Util.Item;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/delete")
public class DropServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Item> items = Connector.select("store");
        for(Item item : items) {
            if(request.getParameter("" + item.getItemID()) != null) {
                Connector.delete("cart", "itemID=" + item.getItemID());
            }
        }
        response.sendRedirect("/Cart_war_exploded/cart");
    }

    public void destroy() {
    }
}