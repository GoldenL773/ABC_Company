<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Plan Details</title>

        <script>
            // Function to confirm before submitting the form
            function confirmCreation() {
                return confirm("Are you sure you want to create this plan?");
            };
        </script>
    </head>
    <body>
        <h1>Plan Details</h1>
        <form action="details" method="POST">

            <input type="hidden" name="plid" value="${plan.id}"/>

            <!-- Hidden input for all product IDs (comma-separated or one per input) -->
            <c:forEach items="${plan.headers}" var="head">
                <input type="hidden" name="productIds" value="${head.product.id}"/>
            </c:forEach>

            <table border="1">
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
    </body>
</html>
