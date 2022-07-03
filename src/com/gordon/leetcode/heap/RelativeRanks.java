package com.gordon.leetcode.heap;

import java.util.*;

/**
 * 506 相对名次
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * 示例 2：
 * <p>
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 *  
 * <p>
 * 提示：
 * <p>
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * score 中的所有值 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/relative-ranks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RelativeRanks {
    public static void main(String[] args) {
        RelativeRanks demo = new RelativeRanks();
        int[] arr = new int[]{2, 1, 5, 6, 3};
        String[] ans = demo.findRelativeRanks(arr);
        System.out.println(Arrays.toString(ans));
    }

    public String[] findRelativeRanks(int[] score) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        score = Arrays.stream(score).boxed().sorted((a, b) -> b - a).mapToInt(p -> p).toArray();

        String[] ans = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            ans[map.get(score[i])] = getScoreRank(i);
        }
        return ans;
    }

    private String getScoreRank(int rank) {
        if (rank == 0) {
            return "Gold Medal";
        } else if (rank == 1) {
            return "Silver Medal";
        } else if (rank == 2) {
            return "Bronze Medal";
        } else {
            return String.valueOf(rank + 1);
        }
    }

    /**
     * 用二维数组来实现
     *
     * @param score
     * @return
     */
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        String[] desc = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        int[][] arr = new int[n][2];
        //用一个二维数组,第一个元素保存当前的元素值,第二元素保存元素在数组中的位置
        for (int i = 0; i < n; i++) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        String[] ans = new String[n];
        //按元素的数值进行逆序排序
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        for (int i = 0; i < n; i++) {
            //arr[i][1]是当前元素对应的位置,对其进行编号
            if (i < 3) {
                ans[arr[i][1]] = desc[i];
            } else {
                ans[arr[i][1]] = String.valueOf(i + 1);
            }
        }
        return ans;
    }
}
