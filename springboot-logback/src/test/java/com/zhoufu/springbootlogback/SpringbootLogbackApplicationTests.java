package com.zhoufu.springbootlogback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class SpringbootLogbackApplicationTests {

    private static Lock lock = new ReentrantLock();
    @Test
    void contextLoads() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("=====外层");
                lock.lock();
                try {
                    System.out.println("=====内层");
                } finally {
//                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "----调用开始");
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }

    @Test
    public void test2(){
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in a");
            LockSupport.park(); // 阻塞  类似于wait
            System.out.println(Thread.currentThread().getName() + "\t" + "----a 被唤醒");
        },"a");
        a.start();

        //等三秒
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 唤醒A
        Thread b = new Thread(() -> {
            LockSupport.unpark(a); // 唤醒a
            System.out.println(Thread.currentThread().getName() + "\t" + "----唤醒a");
        },"b");
        b.start();
    }

}
