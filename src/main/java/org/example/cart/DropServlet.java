package org.example.cart;

import org.example.cart.Util.ItemDTO;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/delete")
public class DropServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ItemDTO> items = Connector.selectItem();
        for(ItemDTO item : items) {
            if(request.getParameter("" + item.getItemID()) != null) {
                Connector.delete("cart", "itemID=" + item.getItemID());
            }
        }
        response.sendRedirect("/Cart_war_exploded/cart");
    }

    public void destroy() {
    }
}