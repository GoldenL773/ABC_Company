<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: 0 auto; padding: 20px; }
        h2 { text-align: center; color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }
        .actions { margin-top: 20px; text-align: center; }
        .actions button { margin-right: 10px; padding: 10px 15px; }
    </style>
</head>
<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
    <div class="container">
        <h2>Employee Management</h2>
        <c:if test="${empty employees}">
            <p>No employees found in this department.</p>
        </c:if>
           
        <c:if test="${not empty employees}">
            <table>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Date of Birth</th>
                        <th>Department ID</th>
                        <th>Salary ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employees}">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.phoneNumber}</td>
                            <td>${employee.address}</td>
                            <td>${employee.dob}</td>
                            <td>${employee.departmentId}</td>
                            <td>${employee.salaryId}</td>
                            <td>
                                <a href="../employee-management/view-details?eid=${employee.id}">View Details</a> |
                                <a href="edit-employee?eid=${employee.id}">Edit</a> |
                                <a href="deleteEmployee?eid=${employee.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <div class="actions">
            <button onclick="window.location.href='add-employee'">Add New Employee</button>
        </div>
    </div></div>
</body>
</html>