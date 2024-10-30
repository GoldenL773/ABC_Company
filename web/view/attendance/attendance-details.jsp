<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Attendance Management - Details</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
            .edit-mode {
                display: none;
            }
            .button-container {
                margin: 10px 0;
            }
        </style>
        <script>
            function toggleEditMode() {
                document.querySelectorAll('.view-mode').forEach(el => el.style.display = 'none');
                document.querySelectorAll('.edit-mode').forEach(el => el.style.display = 'inline');
                document.getElementById('editBtn').style.display = 'none';
                document.getElementById('saveBtn').style.display = 'inline';
                document.getElementById('cancelBtn').style.display = 'inline';
            }

            function cancelEdit() {
                document.querySelectorAll('.view-mode').forEach(el => el.style.display = 'inline');
                document.querySelectorAll('.edit-mode').forEach(el => el.style.display = 'none');
                document.getElementById('editBtn').style.display = 'inline';
                document.getElementById('saveBtn').style.display = 'none';
                document.getElementById('cancelBtn').style.display = 'none';
            }

            function submitUpdates() {
                document.getElementById('attendanceForm').submit();
            }
        </script>
    </head>
    <body>
        <h1>Attendance Details for Department: ${department.name} on ${date}</h1>

        <!-- Back to Dashboard Button -->
        <div class="button-container">
            <button onclick="window.location.href = '/dashboard'">Back to Dashboard</button>
        </div>

        <!-- Filter Selection Form -->
        <form action="details" method="get">
            <!-- Date input to pass the selected date -->
            <input type="hidden" name="date" value="${date}" />

            <label for="department">Select Department:</label>
            <select id="department" name="department" required>
                <option value="">-- Select Department --</option>
                <c:forEach var="dept" items="${departments}">
                    <option value="${dept.id}" ${department != null && department.id == dept.id ? "selected" : ""}>${dept.name}</option>
                </c:forEach>
            </select>

            <button type="submit">Filter</button>
        </form>

        <!-- Edit and Save Buttons -->
        <button id="editBtn" onclick="toggleEditMode()">Edit</button>
        <button id="saveBtn" onclick="submitUpdates()" style="display: none;">Save</button>
        <button id="cancelBtn" onclick="cancelEdit()" style="display: none;">Cancel</button>

        <!-- Attendance Table -->
        <form id="attendanceForm" action="/attendance-management/update" method="post">
            <input type="hidden" name="date" value="${date}">
            <input type="hidden" name="department" value="${department.id}">

            <table>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Full Name</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Ordered Quantity</th>
                        <th>Actual Quantity</th>
                        <th>Alpha</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="attendance" items="${attendances}">
                        <tr>
                            <!-- Display Employee and Product Details from WorkAssignment -->
                            <td>${attendance.workAssignment.employee.id}</td>
                            <td>${attendance.workAssignment.employee.name}</td>
                            <td>${attendance.workAssignment.product.id}</td>
                            <td>${attendance.workAssignment.product.name}</td>
                            <td>${attendance.workAssignment.quantity}</td>

                            <!-- Editable fields for Attendance data: Actual Quantity, Alpha, and Note -->
                            <td>
                                <span class="view-mode">${attendance.actualQuantity != null ? attendance.actualQuantity : "0"}</span>
                                <input type="number" name="actualQuantity_${attendance.atid}" value="${attendance.actualQuantity != null ? attendance.actualQuantity : "0"}" class="edit-mode">
                            </td>
                            <td>
                                <span class="view-mode">${attendance.alpha != null ? attendance.alpha : "0"}</span>
                                <input type="number" step="0.1" name="alpha_${attendance.atid}" value="${attendance.alpha != null ? attendance.alpha : "0"}" class="edit-mode">
                            </td>
                            <td>
                                <span class="view-mode">${attendance.note != null ? attendance.note : ""}</span>
                                <input type="text" name="note_${attendance.atid}" value="${attendance.note != null ? attendance.note : ""}" class="edit-mode">
                            </td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
        </form>

        <!-- Summary Section -->
        <div class="summary">
            <p>Total Employees: ${totalEmployees}</p>
            <p>Total Completed Output: ${totalCompletedOutput} products</p>
        </div>
    </body>
</html>
