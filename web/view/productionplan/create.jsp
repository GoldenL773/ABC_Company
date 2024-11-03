<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plan Page</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
        <style>
            /* Styling for buttons and select fields */
            input[type="submit"], button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover, button:hover {
                background-color: #45a049;
            }

            select {
                padding: 8px;
                border-radius: 4px;
                border: 1px solid #ccc;
                font-size: 14px;
                margin-bottom: 10px;
            }

            select:focus {
                border-color: #4CAF50;
                outline: none;
            }

            input[type="text"], input[type="date"] {

                border-radius: 4px;
                border: 1px solid #ccc;
                margin-bottom: 10px;
                width: 100%;
            }

            .plan-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            .plan-table th, .plan-table td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }

            .plan-table th {
                background-color: #f2f2f2;
            }
        </style>
        <script type="text/javascript">
            function validateForm() {
                let fromDate = new Date(document.forms["planForm"]["from"].value);
                let toDate = new Date(document.forms["planForm"]["to"].value);
                let today = new Date();

                // Check if the from date is not earlier than today
                if (fromDate <= today) {
                    alert("The 'From' date must be greater than today's date.");
                    return false;
                }

                // Check if the to date is not earlier than the from date
                if (toDate <= fromDate) {
                    alert("The 'To' date must be greater than the 'From' date.");
                    return false;
                }

                return true;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
            <form name="planForm" action="create" method="POST" onsubmit="return validateForm();">
                <label for="name">Plan Name:</label>
                <input type="text" required name="name" id="name"/> <br/>
                <label for="from">From:</label>
                <input type="date" name="from" id="from"/> 
                <label for="to">To:</label>
                <input type="date" name="to" id="to"/> <br/>
                <label for="did">Workshop:</label>
                <select name="did" id="did">
                    <c:forEach items="${requestScope.depts}" var="d">
                        <option value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select>
                <br/>
                <table border="1" class="plan-table">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Estimated Effort</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.products}" var="p">
                            <tr>
                                <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                                <td><input type="text" name="quantity${p.id}"/></td>
                                <td><input type="text" name="effort${p.id}"/></td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
                <input type="submit" value="Save"/>
            </form>
        </div>
    </body>
</html>
