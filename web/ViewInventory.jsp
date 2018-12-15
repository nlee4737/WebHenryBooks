<%-- 
    Document   : ViewInventory
    Created on : Dec 12, 2018, 9:05:15 PM
    Author     : Nathan Lee
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <a href="StoreSelection.jsp">Back to Stores</a>
    </body>
</html>
