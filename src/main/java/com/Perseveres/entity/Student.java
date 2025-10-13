package com.Perseveres.entity;

public class Student {
    private Long id;
    private String studentId;  // 学号
    private String name;       // 学生姓名
    private String major;      // 专业
    private Integer age;
    private String gender;

    // 构造方法
    public Student() {}

    public Student(String studentId, String name, String major, Integer age, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.age = age;
        this.gender = gender;
    }

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}