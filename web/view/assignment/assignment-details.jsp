<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Production Plan Details</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css'/>">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                background-color: #f4f4f4;
            }
           
            .sidebar {
                width: 250px;
                background: #333;
                color: #fff;
                height: 100vh;
                position: fixed;
                top: 0;
                left: 0;
                padding: 20px;
                overflow-y: auto;
                box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            }
            .sidebar h2 {
                text-align: center;
                color: #fff;
                margin-bottom: 20px;
            }
            .sidebar ul {
                list-style-type: none;
                padding: 0;
            }
            .sidebar li {
                margin-bottom: 15px;
            }
            .sidebar a {
                color: #fff;
                text-decoration: none;
                display: block;
                padding: 10px;
                border-radius: 5px;
                transition: background 0.3s;
            }
            .sidebar a:hover {
                background: #444;
            }
            .content {
                margin-left: 260px;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
            button {
                background-color: #333;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }
            button:hover {
                background-color: #444;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
             <!-- Button to go back -->
            <a href="../assignment"><button type="button">Back to Plans</button></a>

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
                <table>
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

                   </div>
    </body>
</html>
