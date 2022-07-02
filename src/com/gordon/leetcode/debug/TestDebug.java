package com.gordon.leetcode.debug;

public class TestDebug {

    public static void main(String[] args) {
        testMethodPoint();
//        testMethodStack();
//        testFieldPoint();
    }

    private static void testMethodPoint() {
        IActionListener actionListener = new WalkAction();
        actionListener.action("gordon");

        actionListener = new EatAction();
        actionListener.action("corffen");

    }

    private static void testFieldPoint() {
        Person person = new Person("zhangsan", 8);

//        person.setAge(18);
//        person.setName("lin");
        int age = person.getAge();
        System.out.println("age=" + age);
        System.out.println(person);
    }

    private static void testMethodStack() {
        TestDebug debug = new TestDebug();
        debug.method1();
    }

    private void method1() {
        method2();
    }

    private void method2() {
        method3();
    }

    private void method3() {
        method4();
    }

    private void method4() {
        System.out.println("method4");
    }
}
