#wait notify/notifyAll

基于对象object的monitor锁，而非线程 

调用前提：
    调用对象object.wait()/notify()/noyifyAll() 必须当前线程持有对象的monitor（锁），
    所以必须在同步块/方法中进行（synchronized块或者synchronized方法）


1.wait会释放锁
【对比：sleep不会释放对象锁，只是让当前线程暂停执行一段时间，从而让其他线程有机会继续执行】

2.调用对象object.wait()后会将线程放入waitset队列中等待（waitset 双向循环链表）.线程状态由Running变为waiting

3.noyify()是唤醒一个正在等待这个对象的monitor的线程，具体哪一个不清楚
  noyifyAll()是唤醒所有等待这个对象的monitor的线程
  
（notify()/noyifyAll() 只是唤醒线程，并不能决定哪个线程能够获取到monitor锁）
【唤醒 不等于 立即拿到锁】
【只有等调用完notify()或者notifyAll()并退出synchronized块，释放对象的锁后，其余线程才可获得锁执行。】

4.等待线程被唤醒后，会从waitset转移到entryset中，然后等待竞争获取锁

5.当wait返回时，说明已经获取到锁了

