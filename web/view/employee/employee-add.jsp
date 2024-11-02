<%-- 
    Document   : employee-add
    Created on : Oct 27, 2024, 11:41:59 AM
    Author     : Golden  Lightning
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Employee</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 60%; margin: 0 auto; padding: 20px; }
        h2 { text-align: center; color: #333; }
        form { margin-top: 20px; }
        label { display: block; margin: 10px 0 5px; }
        input[type="text"], input[type="date"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
        }
        .actions { text-align: center; margin-top: 20px; }
        .actions button { padding: 10px 15px; }
    </style>
</head>
<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
    <div class="container">
        <h2>Add New Employee</h2>
        <form action="add-employee" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="departmentId">Department ID:</label>
            <input type="number" id="departmentId" name="departmentId" required>

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="salaryId">Salary ID:</label>
            <input type="number" id="salaryId" name="salaryId" required>

            <div class="actions">
                <button type="submit">Add Employee</button>
                <button type="button" onclick="window.location.href='/employee-manage'">Cancel</button>
            </div>
        </form>
    </div></div>
</body>
</html>

