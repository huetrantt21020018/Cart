package org.example.cart;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/init")
public class HelloServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("name", request.getParameter("name"));
        System.out.println("set attribute to session");
        response.sendRedirect("/Cart_war_exploded/store");
    }

    public void destroy() {
    }
}