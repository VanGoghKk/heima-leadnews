package com.heima.user.service;


import org.hibernate.validator.constraints.Length;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SyncThread implements Runnable {
    private static int count;
    public SyncThread() {
        count = 0;
    }
    public  void run() {
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程名:"+Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int getCount() {
        return count;
    }
}
public class test04 {


    public static void main(String[] args) {
        System.out.println("使用关键字synchronized");
//        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
     if (intervals.length==1){
         return intervals;
     }
    return null;
    }
}


class Main {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            acquireLock();
        });

        Thread t2 = new Thread(() -> {
            acquireLock();
        });

        t1.start();
        t2.start();
    }

    private static void acquireLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到了锁");
            // 执行同步的代码块
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放了锁");
            lock.unlock(); // 释放锁
        }
    }
}

*

//class ListNode {
//      int val;
//      ListNode next;
//      ListNode(int x) {
//          val = x;
//          next = null;
//      }
//  }
class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null){
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
         while (temp != null){
             if (visited.contains(temp)){
                 return temp;
             }
             temp = temp.next;
         }

        return null;
    }
    public ListNode getNode(ListNode headA, ListNode headB){
        if (headA==null||headB==null)return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB){
           pA=pA==null?headB:pA.next;
           pB=pB==null?headA:pB.next;
        }
        return pA;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution3 {
    public ListNode reverseList(ListNode head) {
        ListNode node = head;
        ListNode pre=null;
        while (node!=null){
            ListNode next =node.next;

            node.next=pre;
            pre=node;
            node=next;

        }
        return pre;

    }
    public boolean isPalindrome(ListNode head) {
        boolean is = true;
     ListNode temp = head;
     Stack<Integer> stack = new Stack<>();
     while (temp!=null){
         stack.push(temp.val);
         temp=temp.next;
     }

     while (head!=null){
         if (head.val!=stack.pop()){
             is = false;
         }
         head=head.next;
     }
     return is;
    }
    public boolean hasCycle(ListNode head) {
        ListNode temp =head;
        Set<ListNode> set = new HashSet<>();
        set.add(temp);
        while (!set.contains(temp.next)||){

            if (set.contains(temp.next)){
                return true;
            }
            temp=temp.next;
        }
        return false;
    }
}