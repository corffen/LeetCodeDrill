package com.gordon.leetcode.heap;

import java.util.*;

public class Solution {
    /**
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * <p>
     * 丑数 就是只包含质因数2、3 和/或5的正整数。
     * 示例 1：
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     *
     * @param n
     * @return
     */
    int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        set.add(1L);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            Long cur = pq.poll();
            ans = cur;
            for (int factor : factors) {
                long next = cur * factor;
                if (set.add(next)) {
                    pq.offer(next);
                }
            }
        }
        return (int) ans;
    }

    /**
     * 超级丑数
     * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
     * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
     * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
     * 示例 1：
     * 输入：n = 12, primes = [2,7,13,19]
     * 输出：32
     * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     * <p>
     * 示例 2：
     * 输入：n = 1, primes = [2,3,5]
     * 输出：1
     * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        set.add(1L);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            Long cur = pq.poll();
            ans = cur;
            for (int factor : primes) {
                long next = cur * factor;
                if (set.add(next)) {
                    pq.offer(next);
                }
            }
        }
        return (int) ans;
    }

    /**
     * 前k个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> occs = new HashMap<>();
        for (int num : nums) {
            occs.put(num, occs.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (Map.Entry<Integer, Integer> entry : occs.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (pq.size() == k) {
                if (pq.peek()[1] < count) {
                    pq.poll();
                    pq.offer(new int[]{num, count});
                }
            } else {
                pq.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        int index = 0;
        while (!pq.isEmpty()) {
            ret[index++] = pq.poll()[0];
        }
        return ret;
    }

    /**
     * 378. 有序矩阵中第 K 小的元素
     * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     * <p>
     * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<ItemData> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.num));
        for (int i = 0; i < m; i++) {
            pq.offer(new ItemData(matrix[i][0], i, 0));
        }
        while (--k > 0 && !pq.isEmpty()) {
            ItemData itemData = pq.poll();
            if (itemData.col + 1 < n) {
                pq.offer(new ItemData(matrix[itemData.row][itemData.col + 1], itemData.row, itemData.col + 1));
            }
        }
        return pq.poll().num;
    }

    class ItemData {
        int num;
        int row;
        int col;

        public ItemData(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * 451. 根据字符出现频率排序
     * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
     * <p>
     * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
     * 输入: s = "cccaaa"
     * 输出: "cccaaa"
     * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        PriorityQueue<CharFreq> pq = new PriorityQueue<>((o1, o2) -> o2.freq - o1.freq);
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new CharFreq(entry.getKey(), entry.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            CharFreq item = pq.poll();
            int count = item.freq;
            while (count > 0) {
                sb.append(item.c);
                count--;
            }
        }
        return sb.toString();
    }

    class CharFreq {
        char c;
        int freq;

        public CharFreq(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * <p>
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * <p>
     * 你需要计算完成所有任务所需要的 最短时间 。
     *
     * @param tasks
     * @param n
     * @return
     */
//    public int leastInterval(char[] tasks, int n) {
//
//    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<ClosestData> pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1.num - o1.anchor) == Math.abs(o2.anchor - o2.num)) {
                return o1.index - o2.index;
            }
            return Math.abs(o1.num - o1.anchor) - Math.abs(o2.anchor - o2.num);
        });

        for (int i = 0; i < arr.length; i++) {
            pq.offer(new ClosestData(x, arr[i], i));
        }
        List<ClosestData> ans = new ArrayList<>();
        while (k-- > 0) {
            ClosestData data = pq.poll();
            ans.add(data);
        }
        ans.sort(Comparator.comparingInt(o -> o.index));
        List<Integer> ret = new ArrayList<>();
        for (ClosestData an : ans) {
            ret.add(an.num);
        }
        return ret;
    }

    static class ClosestData {
        int anchor;
        int num;
        int index;

        public ClosestData(int anchor, int num, int index) {
            this.anchor = anchor;
            this.num = num;
            this.index = index;
        }
    }

    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int prevLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(prevLength + 1);
            } else {
                map.get(x).offer(1);
            }
            System.out.println(map);
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entrySet) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<FreqData> pq = new PriorityQueue<>((o1, o2) ->
                o1.freq == o2.freq ? o1.word.compareTo(o2.word) : o2.freq - o1.freq);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(new FreqData(entry.getKey(), entry.getValue()));
        }
        List<String> ret = new ArrayList<>();
        while (k-- > 0) {
            FreqData freqData = pq.poll();
            ret.add(freqData.word);
        }
        return ret;
    }

    static class FreqData {
        String word;
        int freq;

        public FreqData(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }


    public static void main(String[] args) {
//        String s = "helloword";
//        char[] tasks = s.toCharArray();
//        int[] hash = new int[26];
//        for (char task : tasks) {
//            hash[task - 'a'] += 1;
//        }
//        System.out.println(Arrays.toString(hash));
//        Arrays.sort(hash);
//        System.out.println(Arrays.toString(hash));
        Solution demo = new Solution();
        int[] arr = {1, 2, 3, 3, 4, 5, 5, 6, 6};
        boolean possible = demo.isPossible(arr);
    }
}
