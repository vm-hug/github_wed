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
    <h2>Insert new Product</h2>
    <form:form method="POST" action="/products/insertProduct" modelAttribute="product">
        <form:input type="text" path="productName" placeholder="Enter product's name"/>
        <form:errors path="productName" cssClass="error"/>
        <br/>
        <form:input type="number" path="price" placeholder="Enter product's price"/>
        <form:errors path="price" cssClass="error"/>
        <br/>
        <form:input type="text" path="description" placeholder="Enter product's description"/>
        <form:errors path="description" cssClass="error"/>
        <br/>
        <form:select path="categoryID"> 
            <c:forEach var="category" items="${categories}">
                <form:option value="${category.getCategoryID()}">
                    ${category.getCategoryName()}
                </form:option>
            </c:forEach>
        </form:select>
        <p class="error" >${error}</p>
        <input type="submit" value="Insert"/>
    </form:form>
</body>
</html>
