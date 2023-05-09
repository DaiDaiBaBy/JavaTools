package com.zhoufu.springbootlogback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Lock;
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

}
