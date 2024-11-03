<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Home - Dashboard</title>
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
                margin-left: 260px;
                padding: 20px;
                width: calc(100% - 260px);
            }

            header {
                background: #333;
                color: #fff;
                padding: 20px 0;
                text-align: center;
                border-radius: 5px;
            }

            .welcome {
                background: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }

            .products-section {
                display: flex;
                justify-content: space-between;
                gap: 20px;
            }

            .product-card {
                background: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                padding: 15px;
                flex: 1;
                text-align: center;
                transition: transform 0.3s;
            }

            .product-card:hover {
                transform: scale(1.05);
            }

            .product-card h3 {
                margin-top: 0;
                color: #333;
            }

            .product-card img {
                width: 100%;
                border-radius: 5px;
                margin-bottom: 10px;
            }

            .product-card p {
                color: #666;
            }
        </style>
    </head>

    <body>
        <jsp:include page="/view/master/sidebar.jsp"/>
        <div class="container1">
            <header>
                <h1>Home</h1>
            </header>

            <div class="welcome">
                <h2>Hello, ${displayName}!</h2>
                <p>Welcome to the company’s home page. Here, you can explore our key products and stay updated with our latest initiatives.</p>
            </div>

            <div class="products-section">
                <div class="product-card">
                    <img src="images/pngtree-remove-from-basket-png-image_10188191.png" alt="Giỏ">
                    <h3>Giỏ</h3>
                    <p>The perfect woven basket for various uses. Durable, eco-friendly, and crafted with care.</p>
                </div>
                <div class="product-card">
                    <img src="images/ewer234312.jpg" alt="Thúng">
                    <h3>Thúng</h3>
                    <p>Versatile and strong, ideal for storage and transport. Handmade with attention to detail.</p>
                </div>
                <div class="product-card">
                    <img src="images/met.jpg" alt="Mẹt">
                    <h3>Mẹt</h3>
                    <p>An essential for traditional and modern uses alike, combining aesthetics and function.</p>
                </div>
            </div>
        </div>
    </body>
</html>
