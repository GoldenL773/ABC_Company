<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plan Page</title>
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
        <form name="planForm" action="create" method="POST" onsubmit="return validateForm();">
            Plan Name: <input type="text" required name="name"/> <br/>
            From: <input type="date" name="from"/> To: <input type="date" name="to"/> <br/>
            Workshop: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1px">
                <tr>
                    <td>Product</td>
                    <td>Quantity</td>
                    <td>Estimated Effort</td>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                    <td><input type="text" name="quantity${p.id}"/></td>
                    <td><input type="text" name="effort${p.id}"/></td>
                </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
