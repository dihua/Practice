#wait

1.会释放锁
2.将线程放入waitset队列中等待（waitset 双向循环链表）
3.等待notify/noyifyAll线程唤醒，将从waitset转移到entryset中，然后等待竞争获取锁