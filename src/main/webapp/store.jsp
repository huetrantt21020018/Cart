<%@ page import="java.util.List" %>
<%@ page import="org.example.cart.Util.ItemDTO" %><%--
  Created by IntelliJ IDEA.
  User: HueTTT
  Date: 3/19/2024
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <p><%= session.getAttribute("name") %> hãy chọn mặt hàng muốn mua</p>
        <form action="add" method="post">
            <table>
                <tr>
                    <th></th>
                    <th>Tên hàng</th>
                    <th>Số lượng</th>
                </tr>
                <%
                    List<ItemDTO> items;
                    items = (List<ItemDTO>) request.getAttribute("items");
                    for (ItemDTO item : items) {
                %>
                <tr>
                    <td><input type="checkbox" name="<%= item.getItemID() %>"></td>
                    <td><%= item.getName() %></td>
                    <td><input type="number" name="<%= item.getItemID() %>_count"></td>
                </tr>
                <%
                    }
                %>

            </table>
            <br>
            <input type="submit" value="Thêm vào giỏ">
        </form>
        <a href="cart">Xem giỏ hàng</a>
    </body>
</html>

