<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Production Plan Details</title>
    </head>
    <body>
        <h1>${plan.name}</h1>
        <p><strong>Plan ID:</strong> ${plan.id}</p>
        <p><strong>Workshop:</strong> ${plan.dept.name}</p>
        <p><strong>Workshop Manager:</strong> ${plan.dept.id}</p>

        <h3>General Plan:</h3>

        <table border="1">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Quantity (unit: piece)</th>
            </tr>
            <c:forEach var="head" items="${plan.headers}">
                <tr>
                    <td>${head.product.id}</td>
                    <td>${head.product.name}</td>
                    <td>${head.id}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>The detailed production plan is listed in the table below:</h3>
        <table border="1">
            <tr>
                <th>Date</th>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Shift</th>
                <th>Quantity (unit: piece)</th>
                <th>Note</th>
            </tr>
            <c:forEach var="detail" items="${details}">
                <tr>
                    <td>${detail.date}</td>
                    <td>${detail.product.id}</td>
                    <td>${detail.product.name}</td>
                    <td>k${detail.sid}</td>
                    <td>${detail.quantity}</td>
                    <td>${detail.note}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
