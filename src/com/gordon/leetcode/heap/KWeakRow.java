package com.gordon.leetcode.heap;

import java.util.Arrays;

public class KWeakRow {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            int oneCount = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    oneCount++;
                } else {
                    //因为1都在前面,到这里就可以跳出循环了.当然还可以使用二分查找来找到位置.
                    break;
                }
            }
            arr[i][0] = oneCount;
            arr[i][1] = i;
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });
        int[] ans = new int[k];
        int i = 0;
        while (i < k) {
            ans[i] = arr[i][1];//第几行
            i++;
        }
        return ans;
    }
}
