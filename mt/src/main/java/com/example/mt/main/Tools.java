package com.example.mt.main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.*;

public class Tools {

    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    private static Tools tools;
    public static Tools getInstacnce(){
        if (tools==null)
            tools = new Tools();
        return tools;
    }

    public void readWriteLock(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        lock.readLock().unlock();
        lock.writeLock().lock();
        lock.writeLock().unlock();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        Timer timer = new Timer();
    }
}