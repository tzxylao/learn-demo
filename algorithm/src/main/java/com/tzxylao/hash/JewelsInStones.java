package com.tzxylao.hash;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author laoliangliang
 * @date 2019/8/14 10:27
 */
public class JewelsInStones {
    public int numJewelsInStones(String J, String S) {
        Map<Character, Character> temp = new HashMap<>();
        char[] chars = J.toCharArray();
        for (char aChar : chars) {
            temp.put(aChar, aChar);
        }
        char[] chars1 = S.toCharArray();
        int count = 0;
        for (char c : chars1) {
            Character character = temp.get(c);
            if (character != null) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        JewelsInStones jewels = new JewelsInStones();
        System.out.println(jewels.numJewelsInStones("aA", "aAAbbbbb"));
    }
}
