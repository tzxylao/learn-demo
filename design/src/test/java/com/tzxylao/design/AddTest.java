package com.tzxylao.design;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laoliangliang
 * @since 19/4/11 下午8:34
 */
public class AddTest {

    @Test
    public void testAdd() {
        ListNode l1 = new ListNode(2);

        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(5);
        l4.next = l5;
        l5.next = l6;

        ListNode listNode = addTwoNumbers(l1, l4);
        System.out.println("" + listNode.val + listNode.next.val + listNode.next.next.val + "");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addByRecursion(l1, l2, 0);
    }

    public ListNode addByRecursion(ListNode l1, ListNode l2, int initialValue) {
        int valueL1 = l1 != null ? l1.val : 0;
        int valueL2 = l2 != null ? l2.val : 0;
        int sum = valueL1 + valueL2 + initialValue;
        ListNode last = new ListNode(sum % 10);
        if ((l1 != null && l1.next != null) || (l2 != null && l2.next != null) || sum >= 10) {
            ListNode add = addByRecursion(l1 != null ? l1.next : null, l2 != null ? l2.next : null, sum >= 10 ? sum / 10 : 0);
            last.next = add;
        }
        return last;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void testList() {
        String s = "pwwkew";
        List<Character> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        int count = 0;
        int max = -1;
        for (char c : chars) {
            if (list.contains(c)) {
                if(count > max){
                    max = count;
                }
                while(list.contains(c)){
                    count--;
                    list.remove(0);
                }
            }
            list.add(c);
            count++;
        }
        if(count >= max){
            max = count;
        }
        System.out.println(max);
    }
}
