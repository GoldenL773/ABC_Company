
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 60%; margin: 0 auto; padding: 20px; }
        h2 { text-align: center; color: #333; }
        .details { margin-top: 20px; }
        .details p { font-size: 16px; line-height: 1.6; }
    </style>
</head>
<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
    <div class="container">
        <h2>Employee Details</h2>
        <c:if test="${employee != null}">
            <div class="details">
                <p><strong>Employee ID:</strong> ${employee.id}</p>
                <p><strong>Name:</strong> ${employee.name}</p>
                <p><strong>Phone Number:</strong> ${employee.phoneNumber}</p>
                <p><strong>Address:</strong> ${employee.address}</p>
                <p><strong>Date of Birth:</strong> ${employee.dob}</p>
                <p><strong>Department ID:</strong> ${employee.departmentId}</p>
                <p><strong>Salary ID:</strong> ${employee.salaryId}</p>
            </div>
        </c:if>
        <div class="actions">
            <button onclick="window.location.href='../employee-management/view-available?workshopId=${employee.departmentId}'">Back to Employee Management</button>
        </div>
    </div></div>
</body>
</html>