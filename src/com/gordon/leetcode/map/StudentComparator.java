package com.gordon.leetcode.map;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getScore() == o2.getScore()) {
            return Integer.compare(o2.getGrade(), o1.getGrade());
        } else {
            return Integer.compare(o2.getScore(), o1.getScore());
        }
    }
}
