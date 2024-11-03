<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Monthly Attendance Report</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                color: #333;
                overflow-x: hidden;
            }
            .table-wrapper {
    overflow-x: auto;
    margin-bottom: 20px;
    padding-right: 10px; /* Add a bit of padding to the right */
}

.content {
    margin-left: 260px;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: hidden; /* Ensures content stays within the boundary */
}

            h1, h2, h3 {
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 8px;
                text-align: center;
                border: 1px solid #ddd;
            }
            th {
                background-color: #555;
                color: #fff;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .filter-section {
                margin-bottom: 20px;
                padding: 10px;
                background-color: #333;
                color: #fff;
                border-radius: 5px;
            }
            label {
                margin-right: 10px;
            }
            select, button, input[type="submit"] {
                padding: 6px 12px;
                margin-right: 10px;
                border-radius: 4px;
                border: 1px solid #333;
                background-color: #333;
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            select:hover, button:hover, input[type="submit"]:hover {
                background-color: #555;
            }
            input[type="text"] {
                padding: 6px;
                border-radius: 4px;
                border: 1px solid #ddd;
                margin-right: 10px;
            }
            p {
                color: #333;
            }
            

        </style>
    </head>
    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
            <h1>Monthly Attendance Report - Department ${departmentId}</h1>

            <form action="report" method="get" class="filter-section">
                <label for="department">Select Department:</label>
                <select id="department" name="department" required>
                    <option value="">-- Select Department --</option>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.id}" ${param.department != null && dept.id == param.department ? "selected" : ""}>${dept.name}</option>
                    </c:forEach>
                </select>

                <label for="month">Month:</label>
                <select name="month" id="month">
                    <c:forEach var="m" begin="1" end="12">
                        <option value="${m}" ${m == month ? "selected" : ""}>${m}</option>
                    </c:forEach>
                </select>

                <label for="year">Year:</label>
                <select name="year" id="year">
                    <c:forEach var="y" begin="2022" end="2030">
                        <option value="${y}" ${y == year ? "selected" : ""}>${y}</option>
                    </c:forEach>
                </select>
                <button type="submit">Filter</button>
            </form>

            <c:choose>
                <c:when test="${monthlyWages != null && !monthlyWages.isEmpty()}">
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Employee ID</th>
                                    <th>Full Name</th>
                                        <c:forEach var="day" begin="1" end="${30}">
                                        <th>Day ${day}</th>
                                        </c:forEach>
                                    <th>Total Salary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="record" items="${monthlyWages}">
                                    <tr>
                                        <td>${record.employeeId}</td>
                                        <td>${record.employeeName}</td>
                                        <c:forEach var="day" begin="1" end="30">
                                            <td>${record.getDailyEffort(day) != null ? record.getDailyEffort(day) : ""}</td>
                                        </c:forEach>
                                        <td>${record.getTotalSalary()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>No records found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
