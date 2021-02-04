<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List Product</title>
    <style>
        table {
            border-collapse: collapse;
        }

        tr, td, th {
            border: 1px solid blueviolet;
        }
    </style>
</head>
<body>
<h1>Product list:</h1>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PRICE</th>
        <th>DESCRIPTION</th>
        <th>UPDATE</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/home?action=update&id=${product.getId()}&name=${product.getName()}&price=${product.getPrice()}&description=${product.getDescription()}">Update</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/home?action=delete&id=${product.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/product/save.jsp">Add product</a>
<table>
    <tr>
        <td>Search product by id:</td>
        <td>
            <form action="${pageContext.request.contextPath}/home?action=search" method="post">
                <label>
                    <input type="text" name="id">
                </label>
                <input type="submit" value="Search">
            </form>
        </td>
    </tr>
    <tr>
        <td>Search product by name:</td>
        <td>
            <form action="${pageContext.request.contextPath}/home?action=search&target=name" method="post">
                <label>
                    <input type="text" name="name">
                </label>
                <input type="submit" value="Search">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
