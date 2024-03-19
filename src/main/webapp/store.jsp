<%@ page import="java.util.List" %>
<%@ page import="org.example.cart.Util.Item" %><%--
  Created by IntelliJ IDEA.
  User: HueTTT
  Date: 3/19/2024
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <p>Chọn mặt hàng muốn mua</p>
        <form action="add">
            <%
                List<Item> items = (List<Item>) request.getAttribute("items");
                for (Item item : items) {
            %>
            <input type="checkbox" name="<%= item.getItemID() %>">
            <%= item.getName() %><br>
            <%
                }
            %>

            <br>
            <input type="submit" value="Thêm vào giỏ">
        </form>
        <a href="cart">Xem giỏ hàng</a>
    </body>
</html>
