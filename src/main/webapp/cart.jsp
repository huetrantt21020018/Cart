<%@ page import="org.example.cart.Util.ItemDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.cart.Util.OrderDTO" %><%--
  Created by IntelliJ IDEA.
  User: HueTTT
  Date: 3/19/2024
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Gio hang</title>
    </head>
    <body>

        <p>Giỏ hàng của <%= session.getAttribute("name") %></p>
        <form action="delete" method = "post">
            <table>
                <tr>
                    <th></th>
                    <th>Tên hàng</th>
                    <th>Số lượng</th>
                </tr>
                <%
                    List<OrderDTO> items;
                    items = (List<OrderDTO>) request.getAttribute("items");
                    for (OrderDTO item : items) {
                %>
                <tr>
                    <td><input type="checkbox" name="<%= item.getItemID() %>"></td>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getCount() %></td>
                </tr>
                <%
                    }
                %>

            </table>
            <input type="submit" value="Xóa khỏi giỏ">
        </form>

        <br/>
        <a href="store">Về cửa hàng</a>
    </body>
</html>
