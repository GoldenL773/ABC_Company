<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
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
    <script>
        setTimeout(function() {
            var messageElement = document.getElementById('success-message');
            if (messageElement) {
                messageElement.style.display = 'none';
            }
        }, 5000);
    </script>
</head>
<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
    <div class="container">
        <h2>Edit Employee</h2>
        <c:if test="${not empty message}">
            <p id="success-message" style="color: green; text-align: center;">${message}</p>
        </c:if>
        <form action="edit-employee" method="post">
            <input type="hidden" id="id" name="id" value="${employee.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${employee.name}" required>

            <label for="departmentId">Department ID:</label>
            <input type="number" id="departmentId" name="departmentId" value="${employee.departmentId}" required>

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${employee.phoneNumber}" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${employee.address}" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="${employee.dob}" required>

            <label for="salaryId">Salary ID:</label>
            <input type="number" id="salaryId" name="salaryId" value="${employee.salaryId}" required>

            <div class="actions">
                <button type="submit">Save Changes</button>
                <button type="button" onclick="window.location.href='/employee-manage'">Cancel</button>
            </div>
        </form>
    </div></div>
</body>
</html>
