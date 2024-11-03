<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Production Plan Assignments</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
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
            margin-bottom: 20px;
            color: #fff;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
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
            margin-left: 250px;
            padding: 20px;
            background-color: #fff;
            min-height: 100vh;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .filter-section {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .filter-section label {
            margin-right: 10px;
            font-weight: bold;
        }

        .filter-section input[type="text"],
        .filter-section select {
            margin-right: 10px;
            padding: 5px;
            border-radius: 3px;
            border: 1px solid #ddd;
        }

        .filter-section input[type="submit"],
        .filter-section button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }

        .filter-section button:hover,
        .filter-section input[type="submit"]:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
    <div class="content">
        <h2>Production Plans for Assignment</h2>

        <div class="filter-section">
            <form action="assignment" method="GET">
                <!-- Filter by Name -->
                <label for="name">Plan Name:</label>
                <input type="text" id="name" name="name" value="${param.name}" placeholder="Search by name"/>

                <!-- Filter by Month and Year -->
                <label for="month">Month:</label>
                <select id="month" name="month">
                    <option value="">All</option>
                    <c:forEach var="i" begin="1" end="12">
                        <option value="${i}" ${i == param.month ? 'selected' : ''}>${i}</option>
                    </c:forEach>
                </select>

                <label for="year">Year:</label>
                <select id="year" name="year">
                    <option value="">All</option>
                    <c:forEach var="y" begin="2020" end="2025">
                        <option value="${y}" ${y == param.year ? 'selected' : ''}>${y}</option>
                    </c:forEach>
                </select>

                <!-- Filter by Department (Dropdown) -->
                <label for="deptId">Department:</label>
                <select id="deptId" name="deptId">
                    <option value="">All Departments</option>
                    <c:forEach var="dept" items="${depts}">
                        <option value="${dept.id}" ${dept.id == param.deptId ? 'selected' : ''}>${dept.name}</option>
                    </c:forEach>
                </select>

                <!-- Submit button for the search -->
                <input type="submit" value="Search"/>
                <a href="assignment">
                    <button type="button">Reset Filters</button>
                </a>
            </form>
        </div>

        <c:if test="${not empty plans}">
            <h3>Production Plans</h3>
            <table>
                <thead>
                    <tr>
                        <th>Plan ID</th>
                        <th>Plan Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="plan" items="${plans}">
                        <tr>
                            <td>${plan.id}</td>
                            <td>${plan.name}</td>
                            <td>${plan.start}</td>
                            <td>${plan.end}</td>
                            <td><a href="assignment/details?id=${plan.id}">View Details</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty plans}">
            <p>No production plans found for the given criteria.</p>
        </c:if>
    </div>
</body>
</html>
