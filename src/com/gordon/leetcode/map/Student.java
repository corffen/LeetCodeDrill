package com.gordon.leetcode.map;

public class Student {

    private String name;
    private int age;
    private int grade;
    private int sex;
    private int score;

    public Student() {
    }

    public Student(String name, int age, int grade, int sex, int score) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.sex = sex;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", sex=" + sex +
                ", score=" + score +
                '}';
    }
}
