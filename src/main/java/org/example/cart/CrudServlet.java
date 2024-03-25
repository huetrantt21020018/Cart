package org.example.cart;

import org.example.cart.Util.ItemDTO;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/crud")
public class CrudServlet extends HttpServlet {

    private void create(String name) {
        if(name.equals("")) return;
        Map<String, String> mp = new HashMap<>();
        ItemDTO item = new ItemDTO(0, name);
        mp.put("itemID", "" + item.getItemID());
        mp.put("name", "'" + item.getName() + "'");
        Connector.insertItem(mp);
    }
    private void update(int id, String name) {
        Map<String, String> mp = new HashMap<>();
        ItemDTO item = new ItemDTO(id, name);
        mp.put("itemID", "" + item.getItemID());
        mp.put("name", "'" + item.getName() + "'");
        Connector.insertItem(mp);
    }
    private void delete(int id) {
        Connector.delete("store", "itemId="+id);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDTO> items = Connector.selectItem();
        for(ItemDTO item : items) {
            if(!request.getParameter("" + item.getItemID() + "_upd").equals(item.getName())) {
                update(item.getItemID(), request.getParameter("" + item.getItemID() + "_upd"));
            }
            if(request.getParameter("" + item.getItemID() + "_del") != null) {
                delete(item.getItemID());
            }
        }
        if(request.getParameter("newItem") != null) {
            create(request.getParameter("newItem"));
        }
        response.sendRedirect("/Cart_war_exploded/admin");
    }

    public void destroy() {
    }
}