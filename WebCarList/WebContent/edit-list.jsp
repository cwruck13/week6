<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit An Existing List</title>
</head>
<body>
<form action = "editListDetailsServlet" method="post">
<input type= "hidden" name = "id" value="${listToEdit.id}">
List Name: <input type = "text" name = "listName" value= "${listToEdit.listName}"><br />

Purchase Date: <input type ="text" name = "month" placeholder="mm" size="4" value="${month}">,
<input type ="text" name = "day" placeholder="dd" size="4" value="${date}">,
<input type ="text" name = "year" placeholder="yyyy" size="4" value="${year}"> 

Owner Name: <input type = "text" name = "ownerName" value= "${listToEdit.owner.ownerName}">

Available Items: <br />

<select name="allItemsToAdd" multiple size="6">
<c:forEach items="${requestScope.allCars}" var="currentcar">
<option value = "${currentcar.id}">${currentcar.make} | ${currentcar.year} | ${currentcar.model}</option>
</c:forEach>
</select>
<br />
<input type="submit" value= "Edit List and Add Cars">
</form>
<a href = "index.html">Go add new cars instead</a>
</body>
</html>