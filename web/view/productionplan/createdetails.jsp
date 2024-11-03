<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Plan Details</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css' />">
        <style>
            input[type="text"], select {
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin: 5px 0;
                width: 90%;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 10px;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            .content {
                margin-left: 260px;
                padding: 20px;
                width: calc(100% - 260px);
            }
        </style>
        <script>
            // Function to confirm before submitting the form
            function confirmCreation() {
                return confirm("Are you sure you want to create this plan?");
            };
        </script>
    </head>
    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="content">
            <h1>Plan Details</h1>
            <form action="details" method="POST">
                <input type="hidden" name="plid" value="${plan.id}"/>
                <c:forEach items="${plan.headers}" var="head">
                    <input type="hidden" name="productIds" value="${head.product.id}"/>
                </c:forEach>

                <table>
                    <tr>
                        <th>Date</th>
                        <c:forEach items="${plan.headers}" var="head">
                            <th>${head.product.name}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach var="date" items="${dateRange}">
                        <input type="hidden" name="dateRange" value="${date}"/>
                    </c:forEach>
                    <c:forEach var="date" items="${dateRange}">
                        <tr>
                            <td>${date}</td>
                            <c:forEach items="${plan.headers}" var="head">
                                <td>
                                    K1: <input type="text" name="quantity_${date}_${head.product.id}_1"/>
                                    K2: <input type="text" name="quantity_${date}_${head.product.id}_2"/>
                                    K3: <input type="text" name="quantity_${date}_${head.product.id}_3"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Save Shift Quantities" onclick="confirmCreation()"/>
            </form>
        </div>
    </body>
</html>
