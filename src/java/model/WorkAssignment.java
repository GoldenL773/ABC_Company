package model;

import java.util.List;

public class WorkAssignment {
    private int id; // Assignment ID
    private int detailId; // Corresponds to the PlanDetail ID (pdid)
    private int quantity; // Assigned quantity (Ordered Quantity)
    private String note; // Note for assignment
    private int employeeId;
     private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private Employee employee; // Employee associated with the assignment
    private Product product; // Product associated with the assignment

    // Getters and Setters for fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // Getters and Setters for Employee object
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Getters and Setters for Product object
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "WorkAssignment{" +
                "id=" + id +
                ", detailId=" + detailId +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", employee=" + (employee != null ? employee.toString() : "null") +
                ", product=" + (product != null ? product.toString() : "null") +
                '}';
    }
}
