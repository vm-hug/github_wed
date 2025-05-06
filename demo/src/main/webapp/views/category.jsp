<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categories List</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    <!-- <style>
        th, td {
            border: 1px solid black;
        }
    </style> -->
</head>
<body>
    <h1>Categories List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${categories}" var="category">
            <tr>
                <td>${category.getCategoryID()}</td>
                <td>${category.getCategoryName()}</td>
                <td>${category.getDescription()}</td>
                <td>
                    <a href="products/getProductByCategoryID/${category.getCategoryID()}">show</a>
                    <a href="deleteCategory?categoryID=${category.getCategoryID()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="products/insertProduct">Insert New Product</a>
</body>
</html>