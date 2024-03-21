package org.example.cart;

import org.example.cart.Util.Item;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
        import javax.servlet.annotation.*;


@WebServlet(value = "/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = Connector.select("store");
        for(Item item : items) {
            if(request.getParameter("" + item.getItemID()) != null) {
                Map<String, String> map = new HashMap<>();
                map.put("itemID", "" + item.getItemID());
                map.put("count", request.getParameter("" + item.getItemID() + "_count"));
                Connector.insert("cart", map);
            }
        }
        response.sendRedirect("/Cart_war_exploded/cart");
    }

    public void destroy() {
    }
}