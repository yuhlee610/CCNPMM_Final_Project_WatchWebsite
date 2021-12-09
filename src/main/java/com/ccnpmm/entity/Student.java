package com.ccnpmm.entity;

public class Student {
    private String studentId;
    private String studentName;
    private String studentMobileNum;
     
    public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentMobileNum() {
		return studentMobileNum;
	}
	public void setStudentMobileNum(String studentMobileNum) {
		this.studentMobileNum = studentMobileNum;
	}
	public Student() {
          
    }
    public Student(String studentId, String studentName, String studentMobileNum) {
         super();
         this.studentId = studentId;
         this.studentName = studentName;
         this.studentMobileNum = studentMobileNum;
    }

    // getters and setters method
    //overriden toString() method
}