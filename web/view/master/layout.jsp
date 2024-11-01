<%-- 
    Document   : layout
    Created on : Nov 1, 2024, 12:43:39 AM
    Author     : Golden  Lightning
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- layout.jsp -->
<html>
<head>
    <title>My Application</title>
</head>
<body>
     <%@ include file="/WEB-INF/view/master/sidebar.jsp" %>

    <div class="content">
        <jsp:include page="${contentPage}" />
    </div>

    <style>
        .content {
            margin-left: 260px;
            padding: 20px;
        }
    </style>
</body>
</html>

