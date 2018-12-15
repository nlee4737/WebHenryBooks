<%-- 
    Document   : StoreSelection
    Created on : Dec 11, 2018, 7:21:53 PM
    Author     : Nathan Lee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <script src="ajax.js"></script>
    <script src="inventory.js"></script>
    <script>
        function pageAction() {
            if(ajax) {
                var sid = document.getElementById("storeid").value;
                ajax.open('get','ViewInventory?storeid=' + encodeURIComponent(sid));
                ajax.send(null);
            } else {
                document.selection.submit;
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Selection</title>
    </head>
    <body>
        <h1>Select Store for Inventory:</h1>
        <p>
            User: ${user.userid} - ${user.username}, ${user.adminlevel} Level
        </p>
        <form action="ViewInventory" id="selection" method="post">
            Stores:<br>
            <select id="storeid" name="storeid">
                <c:forEach var="st" items="${stores}">
                    <option ${st.storeid == user.storeid ? 'Selected' : ''}
                        value="${st.storeid}">${st.storename}</option>
                </c:forEach>
            </select>
            <input type="button" value="View Inventory" onclick="pageAction()">
        </form>
        <br>  
        ${msg}
        <br>
        <div id="results"></div>
    </body>
</html>
