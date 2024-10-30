package model;

import java.sql.Date;

public class Attendance {
    private int atid; // Attendance ID
    private Date date; // Date from PlanDetails
    private int actualQuantity; // Actual quantity worked by the employee
    private float alpha; // Effort multiplier
    private String note; // Additional notes
    private WorkAssignment workAssignment; // Reference to WorkAssignment object

    // Getters and Setters
    public int getAtid() {
        return atid;
    }

    public void setAtid(int atid) {
        this.atid = atid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public WorkAssignment getWorkAssignment() {
        return workAssignment;
    }

    public void setWorkAssignment(WorkAssignment workAssignment) {
        this.workAssignment = workAssignment;
    }

    
}
