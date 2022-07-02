package com.gordon.leetcode.dfs;


import com.gordon.leetcode.heap.Solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFSSolution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        //如果路径的长度path.size()
        // 加上 剩余空间的长度(n-curr+1) < k (需要的长度),就不满足条件
        //这里的curr就是i,转换一下,就是如下
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    System.out.println("mask= " + convertToString(mask) + ",1<<i =" + convertToString(1 << i));
                    t.add(nums[i]);
                }
            }
            System.out.println("result=" + t);
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        dfs(0, nums, t, ans);
        System.out.println(ans);
        return ans;
    }

    public void dfs(int cur, int[] nums, List<Integer> t, List<List<Integer>> ans) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums, t, ans);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums, t, ans);
    }

    private String convertToString(int num) {
        return Integer.toBinaryString(num);
    }

    private void dfs(int[] nums, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (begin > nums.length) {
            return;
        }
        System.out.println("path=" + path);
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayDeque<Integer> path = new ArrayDeque<>();
        dfs(candidates, target, ans, path, 0);
        return ans;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> ans, Deque<Integer> path, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(path));
        }
        dfs(candidates, target, ans, path, idx + 1);
        if (target - candidates[idx] >= 0) {
            path.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, path, idx);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        DFSSolution solution = new DFSSolution();
//        int n = 5;
//        int k = 3;
//        List<List<Integer>> res = solution.combine(n, k);
//        System.out.println(res);
        solution.subsets2(new int[]{1, 2, 3});
    }

}

