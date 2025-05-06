<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <p> Do you really want to assign product:
        <strong>${product.productName}</strong> to other category ?
    </p>

    <form:form method="POST" action="/products/updateProduct/${product.productID}" modelAttribute="product">
        <form:input path="productName" placeholder="Enter product's name"/>
        <form:errors path="productName" cssClass="error"/>
        <br/>
        <form:input path="price" placeholder="Enter product's price"/>
        <form:errors path="price" cssClass="error"/>
        <br/>
        <form:input path="description" placeholder="Enter product's description"/>
        <form:errors path="description" cssClass="error"/>
        <br/>
        <form:select path="categoryID"> 
            <c:forEach var="category" items="${categories}">
                <form:option value="${category.getCategoryID()}">
                    ${category.getCategoryName()}
                </form:option>
            </c:forEach>
        </form:select>

        <input type="submit" value="Update"/>
    </form:form>
    <form:form 
        method="POST" 
        action="/products/deleteProduct/${product.productID}"
        onsubmit="return confirm('Do you really want to delete this product?') ? true : false"
        >
        <input type="submit" value="Delete"/>
    </form:form>
</body>
</html>
