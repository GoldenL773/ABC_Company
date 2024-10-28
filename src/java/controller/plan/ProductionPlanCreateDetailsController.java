package controller.plan;

import controller.authentication.BaseRBACController;
import dal.ProductDBContext;
import dal.ProductionPlanDBContext;
import dal.ProductionPlanDetailDBContext;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import model.PlanDetail;
import model.Product;
import model.ProductionPlan;
import model.ProductionPlanHeader;
import model.auth.User;

public class ProductionPlanCreateDetailsController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        int plid = Integer.parseInt(request.getParameter("plid"));
        ProductionPlanDBContext pldb = new ProductionPlanDBContext();
        ProductDBContext dbProduct = new ProductDBContext();

        ProductionPlan plan = pldb.get(plid);
        request.setAttribute("plan", plan);
        request.setAttribute("products", dbProduct.list());

        List<LocalDate> dates = getDateRange(plan.getStart(), plan.getEnd());
        request.setAttribute("dateRange", dates);

        request.getRequestDispatcher("../view/productionplan/createdetails.jsp").forward(request, response);
    }

    public List<LocalDate> getDateRange(Date startDate, Date endDate) {
        // Convert java.sql.Date to java.time.LocalDate
        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();

        List<LocalDate> dates = new ArrayList<>();

        // Loop through the range of dates
        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        return dates;
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) 
            throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plid"));

        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
        List<ProductionPlanHeader> headers = dbContext.getHeadersByPlanId(planId);

        // Get date range from the request
        String[] dateRange = request.getParameterValues("dateRange");
        if (dateRange == null || dateRange.length == 0) {
            response.getWriter().println("Date range is missing.");
            return;
        }

        List<PlanDetail> details = new ArrayList<>();

        for (ProductionPlanHeader header : headers) {
            int productId = header.getProduct().getId();

            for (String dateStr : dateRange) {
                Date date = Date.valueOf(dateStr);

                for (int shift = 1; shift <= 3; shift++) {
                    int shiftId = shift;
                    String quantityParam = "quantity_" + dateStr + "_" + productId + "_" + shiftId;
                    String rawQuantity = request.getParameter(quantityParam);
                    int quantity = rawQuantity != null && !rawQuantity.isEmpty() ? Integer.parseInt(rawQuantity) : 0;

                    if (quantity > 0) {

                        PlanDetail detail = new PlanDetail();
                        detail.setHeader(header);
                        detail.setSid(shiftId);
                        detail.setDate(date);
                        detail.setQuantity(quantity);

                        details.add(detail);
                    }
                }
            }
        }

        if (!details.isEmpty()) {
            ProductionPlanDetailDBContext detailDbContext = new ProductionPlanDetailDBContext();
            detailDbContext.insert(details);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Plan has been created successfully.");
            response.sendRedirect("../productionplan/list?plid=" + planId);
        } else {
            response.getWriter().println("No valid shift quantities to save.");
        }
    }

}
