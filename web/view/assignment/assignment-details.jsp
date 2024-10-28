<%-- 
    Document   : assignment-details
    Created on : Oct 27, 2024, 5:42:11 PM
    Author     : Golden  Lightning
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Production Plan Details</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
</head>
<body>
    <h2>Production Plan Details</h2>
    
    <!-- Display production plan general information -->
    <div class="plan-info">
        <p><strong>Plan ID:</strong> ${plan.id}</p>
        <p><strong>Plan Name:</strong> ${plan.name}</p>
        <p><strong>Start Date:</strong> ${plan.start}</p>
        <p><strong>End Date:</strong> ${plan.end}</p>
    </div>

    <h3>Details of Production Plan</h3>
    <c:if test="${not empty planDetails}">
        <table border="1">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Shift</th>
                    <th>Quantity</th>
                    <th>Note</th>
                    <th>Assign Employees</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detail" items="${planDetails}">
                    <tr>
                        <td>${detail.date}</td>
                        <td>${detail.product.id}</td>
                        <td>${detail.product.name}</td>
                        <td>${detail.sid}</td>
                        <td>${detail.quantity}</td>
                        <td>${detail.note}</td>
                        <td><a href="assign?detailId=${detail.pdid}&deptid=${plan.dept.id}">Assign Employees</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <c:if test="${empty planDetails}">
        <p>No details available for this production plan.</p>
    </c:if>

    <!-- Button to go back -->
    <a href="../assignment"><button type="button">Back to Plans</button></a>
</body>
</html>

