<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Plan Details</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
            }
            .content input[type="submit"], .content button {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 8px 16px;
                margin: 5px 0;
                cursor: pointer;
                border-radius: 4px;
                transition: background-color 0.3s;
            }
            .content input[type="submit"]:hover, .content button:hover {
                background-color: #0056b3;
            }
            .content select {
                padding: 6px;
                border-radius: 4px;
                border: 1px solid #ccc;
                margin-right: 10px;
            }
            .plan-info, .plan-detail-section {
                margin-bottom: 20px;
            }
            .plan-info p, .plan-detail-section p {
                margin: 5px 0;
            }
            .message {
                background-color: #d4edda;
                color: #155724;
                padding: 10px;
                border: 1px solid #c3e6cb;
                margin-bottom: 20px;
                border-radius: 4px;
                position: fixed;
                top: 20px;
                right: 20px;
                z-index: 1000;
                transition: opacity 0.5s ease-out;
            }
            .hidden {
                opacity: 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
            <h1>${plan.name}</h1>
            <p><strong>Plan ID:</strong> ${plan.id}</p>
            <p><strong>Workshop:</strong> ${plan.dept.name}</p>
            <p><strong>Workshop Manager:</strong> ${plan.dept.id}</p>

            <h3>General Plan:</h3>

            <table>
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
            <table>
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
                        <td>K${detail.sid}</td>
                        <td>${detail.quantity}</td>
                        <td>${detail.note}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
