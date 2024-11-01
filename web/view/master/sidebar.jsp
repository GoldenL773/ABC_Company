<!-- sidebar.jsp -->
<div class="sidebar">
    <h2>Navigation</h2>
    <ul>
        <li><a href="/ABC_Com/productionplan/list">Production Plan</a></li>
        <li><a href="/ABC_Com/assignment">Assignment</a></li>
        <li><a href="/ABC_Com/attendance-management">Attendance Management</a></li>
        <li><a href="/ABC_Com/report">Report</a></li>
        <!--<li><a href="/settings">Settings</a></li>-->
    </ul>
</div>

<style>
    /* Sidebar styles */
    .sidebar {
        width: 200px;
        height: 100vh;
        position: fixed;
        left: 0;
        top: 0;
        background-color: #333;
        color: #fff;
        padding: 20px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        z-index: 1000;
    }
    .sidebar h2 {
        color: #fff;
        font-size: 18px;
        margin-top: 0;
        margin-bottom: 20px;
    }
    .sidebar ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }
    .sidebar ul li {
        margin-bottom: 15px;
    }
    .sidebar ul li a {
        color: #fff;
        text-decoration: none;
        font-size: 16px;
        display: block;
        padding: 5px;
        border-radius: 3px;
    }
    .sidebar ul li a:hover {
        background-color: #444;
    }
</style>
