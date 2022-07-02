package com.gordon.leetcode.debug;

public class EatAction implements IActionListener {
    @Override
    public void action(String name) {
        System.out.println(name + " eat");
    }
}
