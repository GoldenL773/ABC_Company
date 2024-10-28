<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Assign Employees to Plan Detail</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
        <script>
            // Function to automatically hide messages after 5 seconds
            setTimeout(function () {
                const messageElement = document.getElementById('message');
                if (messageElement) {
                    messageElement.style.display = 'none';
                }
            }, 5000);
        </script>
    </head>
    <body>
        <h2>Assign Employees to Production Plan Detail</h2>

        <!-- Display the success message if available -->
        <c:if test="${not empty message}">
            <p id="message" style="color: green; text-align: center;">${message}</p>
        </c:if>

        <div class="plan-detail-section">
            <h3>Plan Detail Information</h3>
            <p><strong>Product:</strong> ${planDetail.product.name}</p>
            <p><strong>Quantity:</strong> ${planDetail.quantity}</p>
            <p><strong>Shift:</strong> ${planDetail.sid}</p>
            <p><strong>Date:</strong> ${planDetail.date}</p>
        </div>

        <div class="employee-assignment-section">
            <h3>Available Employees</h3>
            <form action="assign" method="POST">
                <input type="hidden" name="detailId" value="${planDetail.pdid}"/>
                <label for="employeeId">Select Employee:</label>
                <select id="employeeId" name="employeeId">
                    <c:forEach var="employee" items="${availableEmployees}">
                        <option value="${employee.id}">${employee.name}</option>
                    </c:forEach>
                </select>

                <label for="quantity">Quantity Assigned:</label>
                <input type="number" id="quantity" name="quantity" min="1" required/>

                <label for="note">Note:</label>
                <input type="text" id="note" name="note"/>
                <input type="hidden" name="deptid" value="${deptID}"/>
                <input type="submit" value="Assign Employee"/>
            </form>
        </div>

        <div class="assigned-employees-section">
            <h3>Assigned Employees for This Shift</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Assigned Quantity</th>
                        <th>Note</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="assignment" items="${assignedEmployees}">
                        <tr>
                            <td>${assignment.employee.id}</td>
                            <td>${assignment.employee.name}</td>
                            <td>${assignment.quantity}</td>
                            <td>${assignment.note}</td>
                            <td>
                                <a href="delete?assignmentId=${assignment.id}&detailId=${planDetail.pdid}&deptid=${deptID}" 
                                   onclick="return confirm('Are you sure you want to delete this assignment?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
