package com.srsoft.saptagirimess.ui.modal;

import java.util.List;

public class Attendance {

    private String date;
    private List<String> lunch;
    private List<String> dinner;

    public Attendance(String date, List<String> lunch, List<String> dinner) {
        this.date = date;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Attendance() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
}
