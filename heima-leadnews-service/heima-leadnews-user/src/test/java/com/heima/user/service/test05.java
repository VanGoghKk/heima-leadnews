package com.heima.user.service;

public class test05 {
}


class 死锁 {

    private static final String lock1 = "lock1";

    private static final String lock2 = "lock2";

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {

            while (true) {

                synchronized (lock1) {

                    try {

                        System.out.println(Thread.currentThread().getName() + lock1);

                        Thread.sleep(1000);

                        synchronized (lock2){

                            System.out.println(Thread.currentThread().getName() + lock2);

                        }

                    } catch (InterruptedException e) {

                        throw new RuntimeException(e);

                    }

                }

            }

        });

        Thread thread2 = new Thread(() -> {

            while (true) {

                synchronized (lock2) {

                    try {

                        System.out.println(Thread.currentThread().getName() + lock2);

                        Thread.sleep(1000);

                        synchronized (lock1){

                            System.out.println(Thread.currentThread().getName() + lock1);

                        }

                    } catch (InterruptedException e) {

                        throw new RuntimeException(e);

                    }

                }

            }

        });

        thread1.start();

        thread2.start();

    }

}

