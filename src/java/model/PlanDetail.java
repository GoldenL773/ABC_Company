package model;
 import java.sql.*;
public class PlanDetail {

    private int pdid; 
    private ProductionPlanHeader header; 
    private int sid;  
    private Date date;
    private int quantity;
    private String note; 
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public ProductionPlanHeader getHeader() {
        return header;
    }

    public void setHeader(ProductionPlanHeader header) {
        this.header = header;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}




