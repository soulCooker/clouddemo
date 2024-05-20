package com.jayll.springboot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testIncrement() throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();

        long startMillis = System.currentTimeMillis();
        ThreadLocal<Long> v = ThreadLocal.withInitial(()-> 0L);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1000, 1000, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000000), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000000; i++) {
            poolExecutor.submit(new Runnable() {
                @Override
                public void run() {
//                    v.set(v.get()+1);
                    atomicLong.incrementAndGet();
//                    if (v.get() % 1000==0) {
//                        System.out.println(Thread.currentThread() + ":" + v.get());
//                    }
                }
            });
        }

        poolExecutor.shutdown();
        poolExecutor.awaitTermination(1000, TimeUnit.SECONDS);

        System.out.println("cost:" + (System.currentTimeMillis() - startMillis) + ", res:" + atomicLong);
    }
}
