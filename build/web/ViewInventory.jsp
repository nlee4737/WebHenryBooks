<%-- 
    Document   : ViewInventory
    Created on : Dec 12, 2018, 9:05:15 PM
    Author     : Nathan Lee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html> 
    <head>
        <script>
            function inputValidation() {
                let bookcode = document.UpdateInventory.bookcode.value;
                if (bookcode == null || bookcode == "") {
                    var para = document.createElement("p");
                    var node = document.createTextNode("Enter a Book Code");
                    para.appendChild(node);

                    document.getElementById("UpdateInventory").appendChild(para);
                    document.UpdateInventory.appendChild(para);
                } else {
                    document.UpdateInventory.submit();
                }
            }
        </script> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <br>
        Branch #: <b>${store.storeid}</b><br>
        Branch Name: <b>${store.storename}</b><br>
        Branch Location: <b>${store.storeaddr}</b><br>

        <c:if test="${user.adminlevel == 'Admn'}">
            <form action="UpdateInventory" id="UpdateInventory" name="UpdateInventory" method="post">
                <input type="text" name="bookcode" id="bookcode"/>
                <input type="button" value="Edit Inventory" onclick="inputValidation()"/>
            </form>
        </c:if>

        <table border="1px">
            <tr>
                <th>Store</th>
                <th>Book Code</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="b" items="${books}">
                <tr>
                    <td>${b.store}</td>
                    <td>${b.bookcode}</td>
                    <td>${b.booktitle}</td>
                    <td><fmt:formatNumber value="${b.bookprice}" type="currency"></fmt:formatNumber></td>               
                    <td>${b.quantity}</td>
                </tr>
            </c:forEach>
        </table>
        ${msg}
        <a href="StoreSelection.jsp">Back to Stores</a>
    </body>
</html>
