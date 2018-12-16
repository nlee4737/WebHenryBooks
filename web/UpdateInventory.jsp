<%-- 
    Document   : UpdateInventory
    Created on : Dec 15, 2018, 2:15:59 PM
    Author     : Nathan Lee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="ajax.js"></script>
    <script>
        
        function pageAction(intent) {
            document.UpdateInventory.intent.value = intent;
            document.UpdateInventory.submit();
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Inventory</title>    
</head>
<body>
    <p>User: ${user.userid} - ${user.username}, ${user.adminlevel} Level</p><br>
    <br>
    <p>Branch #: <b>${store.storeid}</b><br>
        Branch Name: <b>${store.storename}</b><br>
        Branch Location: <b>${store.storeaddr}</b><br></p>
    <form action="UpdateInventory" name="UpdateInventory" method="post">
        <input type="text" id="newQuantity" name="newQuantity" value="" placeholder="New Book Quantity"/>
        <input type="button" value="Update" onclick="pageAction('update')"/>    
        <input type="button" value="Cancel" onclick="pageAction('cancel')"/>
        <input type="hidden" name="intent" id="intent" value="">
    </form>
    ${msg}
</body>
</html>
