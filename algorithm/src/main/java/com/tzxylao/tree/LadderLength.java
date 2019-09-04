package com.tzxylao.tree;

import javafx.util.Pair;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author laoliangliang
 * @date 2019/8/14 12:01
 */
public class LadderLength {
    private int L;

    private Map<String, List<String>> allComboDict;

    public LadderLength() {
        this.L = 0;
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited,
                              Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        Integer level = node.getValue();


        for (int i = 0; i < L; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i+1, L);
            for (String s : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(s)) {
                    return level + othersVisited.get(s);
                }

                if (!visited.containsKey(s)) {
                    visited.put(s, level + 1);
                    Q.add(new Pair<>(s, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        this.L = beginWord.length();

        //放入字典wordList的字典
        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i+1, L);
                List<String> transformations = this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                this.allComboDict.put(newWord, transformations);
            }
        });

        Queue<Pair<String, Integer>> qBegin = new LinkedList<>();
        Queue<Pair<String, Integer>> qEnd = new LinkedList<>();
        qBegin.add(new Pair<>(beginWord, 1));
        qEnd.add(new Pair<>(endWord, 1));

        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!qBegin.isEmpty() && !qEnd.isEmpty()) {
            int ans = visitWordNode(qBegin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            ans = visitWordNode(qEnd, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

}
