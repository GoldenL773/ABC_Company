<div class="sidebar">
    <h2><strong>Sidebar Menu</strong></h2>
    <ul>
        <li><a href="/ABC_Com/dashboard">Home</a></li>
        <li><a href="/ABC_Com/productionplan/list">Production Plan</a></li>
        <li><a href="/ABC_Com/assignment">Assignment</a></li>
        <li><a href="/ABC_Com/attendance-management">Attendance Management</a></li>
        <li><a href="/ABC_Com/report">Report</a></li>
    </ul>
</div>

<style>
    .content{
        margin-left: 260px; /* Ensure the content is offset by the sidebar width */
        padding: 20px;
        width: calc(100% - 260px);
    }
    .sidebar {
        width: 200px;
        background: #333;
        color: #fff;
        height: 100vh; /* Full height */
        position: fixed;
        top: 0;
        left: 0;
        padding: 20px;
        overflow-y: auto;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    }

    .sidebar ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    .sidebar li {
        margin-bottom: 15px; /* Space between items */
    }

    .sidebar a {
        text-align: center;
        color: #fff;
        text-decoration: none;
        display: block;
        padding: 10px;
        border-radius: 5px;
        transition: background 0.3s;
    }

    .sidebar a:hover {
        background: #444; /* Slightly lighter on hover */
    }

    h2:nth-child:first-child{
        text-align: center;
        border-bottom: 1px #fff;
    }

    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .sidebar {
        width: 200px;
        background: #333;
        color: #fff;
        padding-top: 20px;
        position: fixed;
        height: 100%;
    }

    .sidebar h2 {
        text-align: center;
        color: #fff;
    }

    .sidebar ul {
        list-style-type: none;
        padding: 0;
    }

    .sidebar ul li {
        padding: 10px;
        text-align: center;
    }

    .sidebar ul li a {
        color: #fff;
        text-decoration: none;
        display: block;
    }

    .sidebar ul li a:hover {
        background-color: #575757;
    }



    header {
        background: #444;
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
</style>
