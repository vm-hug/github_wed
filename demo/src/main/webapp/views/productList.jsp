<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <h1>Product List by categories</h1>
    <table>
        <tr>
            <th>Product's ID</th>
            <th>Product's Name</th>
            <th>Category ID</th>
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.getProductID()}</td>
                <td>${product.getProductName()}</td>
                <td>${product.getCategoryID()}</td>
                <td>${product.getFormattedPrice()}</td>
                <td>${product.getDescription()}</td>
                <td>
                    <a href="../../products/changeCategory/${product.getProductID()}">Update this Product</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>