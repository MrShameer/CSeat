package com.example.cseat;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private static StudentData _instance;
    public static StudentData getInstance() {
        {
            if (_instance == null)
            {
                _instance = new StudentData();
            }
            return _instance;
        }
    }

    List<String> studentsname = new ArrayList<>(), studentclass = new ArrayList<>(), studentwork = new ArrayList<>(), studentpower = new ArrayList<>();

    public List<String> getStudentsname() {
        return studentsname;
    }

    public void setStudentsname(List<String> studentsname) {
        this.studentsname = studentsname;
    }

    public List<String> getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(List<String> studentclass) {
        this.studentclass = studentclass;
    }

    public List<String> getStudentwork() {
        return studentwork;
    }

    public void setStudentwork(List<String> studentwork) {
        this.studentwork = studentwork;
    }

    public List<String> getStudentpower() {
        return studentpower;
    }

    public void setStudentpower(List<String> studentpower) {
        this.studentpower = studentpower;
    }
}
