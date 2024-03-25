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
<p>Danh sach mat hang</p>
<form action="crud" method="post">
    <table>
        <tr>
            <th>Xoa</th>
            <th>ID</th>
            <th>Ten mat hang</th>
        </tr>
        <%
            List<ItemDTO> items;
            items = (List<ItemDTO>) request.getAttribute("items");
            for (ItemDTO item : items) {
        %>
        <tr>
            <td><input type="checkbox" name="<%= item.getItemID() %>_del"></td>
            <td><%= item.getItemID() %></td>
            <td><input type="text" name="<%= item.getItemID() %>_upd" value="<%= item.getName() %>"></td>
        </tr>
        <%
            }
        %>

    </table>
    <br/>
    <p>Them mat hang moi</p>
    <input type="text" name="newItem">

    <br/>
    <input type="submit" value="Cap nhat">
</form>
</body>
</html>

