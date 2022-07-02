package com.gordon.leetcode.bit;

import java.util.Arrays;
import java.util.BitSet;

public class BitSetSolution {

    public static void main(String[] args) {
        BitSet set = new BitSet(10); //10 bits set

        //set() 设为true
        set.set(0);
        set.set(1);
        set.set(5);
        System.out.println(set); // 应该是列出值为true的那些位的坐标！

        // 8 bit >> 1 byte,  就是说截取8位，转成byte。 就是0010 0011 >>
        System.out.println(Arrays.toString(set.toByteArray()));
        // 64 bit >> 1 long
        System.out.println(Arrays.toString(set.toLongArray()));
    }
}
