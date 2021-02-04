<%@ page import="model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update</title>
</head>
<body>
<h1>Update product.</h1>
<form action="${pageContext.request.contextPath}/home?action=update" method="post">
    <table>
        <tr>
            <td>Id</td>
            <td><label>
                <input type="text" name="id" value="${id}">
            </label></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><label>
                <input type="text" name="name" value="${name}">
            </label></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><label>
                <input type="text" name="price" value="${price}">
            </label></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><label>
                <input type="text" name="description" value="${description}">
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
</body>
</html>
