package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MonthlyWageRecord {

    private int employeeId;
    private String employeeName;
    private float hourlyRate;
    private Map<Integer, Float> dailyEffortMap = new HashMap<>(); // Map of day (1-31) to alpha values

    public MonthlyWageRecord(int employeeId, String employeeName, float hourlyRate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.hourlyRate = hourlyRate;
    }

    public void addDailyEffort(Date date, float alpha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH); // Get the day of the month from the date
        dailyEffortMap.put(day, alpha);
    }

    // Get daily effort for a specific day (return null if not available)
    public Float getDailyEffort(int day) {
        return dailyEffortMap.get(day);
    }

    // Calculate total salary based on daily efforts
    public float getTotalSalary() {
        float total = 0;
        for (Float alpha : dailyEffortMap.values()) {
            total += hourlyRate * 8 * alpha;
        }
        return total;
    }

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }
}
