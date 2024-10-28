<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Production Plan Assignments</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
</head>
<body>
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
        <table border="1">
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
</body>
</html>