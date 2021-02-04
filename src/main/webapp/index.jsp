<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <style>
        table{
            border-collapse: collapse;
        }
        tr,td,th{
            border: 1px solid blueviolet;
        }
    </style>
</head>
<body>
<h1>Click <a href="${pageContext.request.contextPath}/home">here</a> to show product list</h1>
<a href="${pageContext.request.contextPath}/product/save.jsp">Add product</a>
</body>
</html>
