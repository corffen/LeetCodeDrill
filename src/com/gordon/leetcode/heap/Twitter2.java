package com.gordon.leetcode.heap;

import java.util.*;

public class Twitter2 {
    private Map<Integer, Tweet> mTweetMap;
    private Map<Integer, Set<Integer>> followMap;
    private static int sTimestamp = 0;
    private static final PriorityQueue<Tweet> pq = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);

    public Twitter2() {
        mTweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        sTimestamp++;
        Tweet oldTweet = mTweetMap.get(userId);
        Tweet newTweet = new Tweet(sTimestamp, tweetId);
        if (oldTweet != null) {
            newTweet.next = oldTweet;
        }
        mTweetMap.put(userId, newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        pq.clear();
        Tweet tweet = mTweetMap.get(userId);
        if (tweet != null) {
            pq.offer(tweet);
        }
        Set<Integer> followList = followMap.get(userId);
        if (followList != null) {
            for (Integer followeeId : followList) {
                Tweet followerTweet = mTweetMap.get(followeeId);
                if (followerTweet != null) {
                    pq.offer(followerTweet);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Tweet poll = pq.poll();
            ans.add(poll.id);
            if (poll.next != null) {
                pq.offer(poll.next);
            }
            count++;
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followList = followMap.get(followerId);
        if (followList == null) {
            Set<Integer> set = new HashSet<>();
            set.add(followeeId);
            followMap.put(followerId, set);
        }else{
            if (followList.contains(followeeId)) {
                return;
            }
            followList.add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followList = followMap.get(followerId);
        if (followList != null) {
            followList.remove(followeeId);
        }
    }

    private class Tweet {
        int timestamp;
        int id;
        Tweet next;

        public Tweet(int timestamp, int id) {
            this.timestamp = timestamp;
            this.id = id;
        }
    }
}
