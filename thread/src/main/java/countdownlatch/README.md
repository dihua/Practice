#countdownlatch

包括一个计数器，该计数器初始化为一个正数，表示需要等待的事件数量。

countdownlatch.await()  等待其他线程先执行，当计数器=0，唤醒
countdownlatch.countdown() 计数器减1

1.一次性，当计数器=0时，不能回退，所有被await()阻塞的线程都被唤醒