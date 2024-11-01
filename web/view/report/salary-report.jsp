<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Monthly Attendance Report</title>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; text-align: center; border: 1px solid #ddd; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h1>Monthly Attendance Report - Department ${departmentId}</h1>

<form action="report" method="get">
    <label for="department">Select Department:</label>
    <select id="department" name="department" required>
        <option value="">-- Select Department --</option>
        <c:forEach var="dept" items="${departments}">
            <option value="${dept.id}" ${department != null && department.id == dept.id ? "selected" : ""}>${dept.name}</option>
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
        <c:forEach var="y" begin="2022" end="2025">
            <option value="${y}" ${y == year ? "selected" : ""}>${y}</option>
        </c:forEach>
    </select>
    <button type="submit">Filter</button>
</form>

<c:choose>
    <c:when test="${monthlyWages != null && !monthlyWages.isEmpty()}">
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
    </c:when>
    <c:otherwise>
        <p>No records found.</p>
    </c:otherwise>
</c:choose>
</body>
</html>
