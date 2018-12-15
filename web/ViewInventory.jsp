<%-- 
    Document   : ViewInventory
    Created on : Dec 12, 2018, 9:05:15 PM
    Author     : Nathan Lee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <br>
        Branch #: <b>${store.storeid}</b><br>
        Branch Name: <b>${store.storename}</b><br>
        Branch Location: <b>${store.storeaddr}</b><br>
        <table>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publisher Code</th>
                <th>Book Type</th>
                <th>Price</th>
            </tr>
            <c:forEach var="b" items="${books}">
            <tr>
                <td>${b.bookid}</td>
                <td>${b.title}</td>
                <td>${b.author}</td>
                <td>${b.publishercode}</td>
                <td>${b.booktype}</td>
                <td>${b.price}</td>
            </tr>
            </c:forEach>
        </table>
        
        <a href="StoreSelection.jsp">Back to Stores</a>
    </body>
</html>
