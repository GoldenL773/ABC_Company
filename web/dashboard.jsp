 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
        }

        .container1 {
            margin-left: 260px; /* Ensure the content is offset by the sidebar width */
            padding: 20px;
            width: calc(100% - 260px); /* Adjust content width to avoid overflow */
        }
        
        header {
            background: #333;
            color: #fff;
            padding: 20px 0;
            text-align: center;
        }
        
        nav {
            display: flex;
            justify-content: space-around;
            background: #555;
            padding: 10px;
            margin-bottom: 20px;
        }
        
        nav a {
            color: #fff;
            text-decoration: none;
            padding: 5px 10px;
        }
        
        nav a:hover {
            background: #666;
        }
        
        .welcome {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        .features {
            margin-top: 20px;
        }
        
        .features h3 {
            margin-bottom: 10px;
        }
        
        .feature-links {
            list-style-type: none;
            padding: 0;
        }
        
        .feature-links li {
            margin: 5px 0;
        }
        
        .feature-links a {
            color: #007bff;
            text-decoration: none;
        }
        
        .feature-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
    <jsp:include page="/view/master/sidebar.jsp"/>
    <div class="container1">
        <header>
            <h1>Welcome to Your Dashboard</h1>
        </header>
        <nav>
            <a href="dashboard">Home</a>
            <a href="logout">Logout</a>
        </nav>

        <div class="container">
            <div class="welcome">
                <h2>Hello, ${displayName}!</h2>
                <p>Welcome to your dashboard. Here, you can access various features available to you based on your roles.</p>
            </div>
<!--            <div class="features">
                <h3>Your Accessible Features:</h3>
                <ul class="feature-links">
                    <c:forEach var="role" items="${sessionScope.account.roles}">
                        <c:forEach var="feature" items="${role.features}">
                            <li><a href="${feature.url}">${feature.name}</a></li>
                        </c:forEach>
                    </c:forEach>
                </ul>
            </div>-->
        </div>
    </div>
</body>
</html>
