package com.gordon.leetcode.map;

import java.util.*;

public class MapSolution {

    private static List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 55; i++) {
            names.add("item" + i);
        }
        return names;
    }

    private static List<Integer> getScores() {
        List<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < 55; i++) {
            scores.add(45 + i);
        }
        return scores;
    }

    private static List<Integer> getGrades() {
        return Arrays.asList(1, 2, 3, 4, 5);
    }

    private static List<Integer> getAges() {
        List<Integer> ages = new ArrayList<Integer>();
        for (int i = 0; i < 25; i++) {
            ages.add(15 + i);
        }
        return ages;
    }

    private static <T> T getRandomItem(List<T> datas) {
        Collections.shuffle(datas);
        return datas.get(0);
    }

    private static Student generateStudent() {
        Student student = new Student();
        student.setAge(getRandomItem(getAges()));
        student.setGrade(getRandomItem(getGrades()));
        student.setScore(getRandomItem(getScores()));
        student.setName(getRandomItem(getNames()));
        student.setSex(getRandomItem(Arrays.asList(0, 1)));
        return student;
    }

    public static void main(String[] args) {
        TreeSet<Student> map = new TreeSet<>(new StudentComparator());
        List<Student> students = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        System.out.println("start =" + t1);
        for (int i = 0; i < 100000; i++) {
            students.add(generateStudent());
        }
        students.sort(new StudentComparator());
        System.out.println(students);

        System.out.println("end=" + (System.currentTimeMillis() - t1));
    }
}
