<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new list</title>
</head>
<body>
<form action = "createNewListServlet" method="post">
List Name: <input type = "text" name = "lsitName"><br />
Purchase date: <input type = "text" name = "month" placeholder="mm" size="4">, 
<input type = "text" name = "day" placeholder="dd" size="4">, 
<input type = "text" name = "year" placeholder="yyyy" size="4"> 
Owner Name: <input type = "text" name = "ownerName"><br />

Available Items:<br />
<select name="allCarsToAdd" multiple size="6">
<c:forEach items="${requestScope.allCars}" var="currentcar">
<option value = "${currentcar.id}">${currentcar.make} | ${currentcar.year} | ${currentcar.model}</option>
 </c:forEach>
 </select>
 <br />
 <input type= "submit" value="Create List and Add Cars">
 </form>
 <a href = "index.html">Go add new items instead.</a>
</body>
</html>