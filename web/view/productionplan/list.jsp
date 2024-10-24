<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Production Plans</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
            }
            .filter-section {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Production Plans</h1>
        
        <!-- Create button redirects to create page -->
        <a href="create">
            <button>Create New Plan</button>
        </a>
        <div class="filter-section">
            <form action="list" method="GET">
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
                <a href="list">
                    <button type="button">Reset Filters</button>
                </a>
            </form>
        </div>

        <!-- Display the list of plans in a table -->
        <table>
            <thead>
                <tr>
                    <th>Plan Name</th>
                    <th>Department Name</th>
                    <th>Date From</th>
                    <th>Date To</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="plan" items="${plans}">
                    <tr>
                        <td>${plan.name}</td>
                        <td>${plan.dept.name}</td>
                        <td>${plan.start}</td>
                        <td>${plan.end}</td>
                        <td>
                            <a href="list/details?plid=${plan.id}">View Details</a>
                            <form action="delete" method="POST" style="display:inline;">
                                <input type="hidden" name="plid" value="${plan.id}"/>
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this plan?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
