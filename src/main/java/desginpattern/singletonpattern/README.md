//懒汉式
//双重检查锁
class Singleton()　｛

	//静态 对象 volatile【防止（第14行）重排序】
	private static volatile Singleton instance;
	private Singleton() {}

	//静态工厂方法
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
					//a. memory = allocate() //分配内存
					//b. ctorInstanc(memory) //初始化对象
					//c. instance = memory //设置instance指向刚分配的地址						
				}
			}
		}
		return instance;
	}
｝

//饿汉式
class Singleton() {

	private static final Singleton INSTANCE = new Singleton();

	private Singleton() {}

	private static Singleton getInstance() {
		return INSTANCE;
	}
}

//妙用
class Sinleton() {
	private static class SingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}
	private Singleton() {}
	
	private static Singleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

/**
对于内部类SingletonHolder，它是一个饿汉式的单例实现，在SingletonHolder初始化的时候会由ClassLoader来保证同步，使INSTANCE是一个真·单例。

同时，由于SingletonHolder是一个内部类，只在外部类的Singleton的getInstance()中被使用，所以它被加载的时机也就是在getInstance()方法第一次被调用的时候。
——

它利用了ClassLoader来保证了同步，同时又能让开发者控制类加载的时机。从内部看是一个饿汉式的单例，但是从外部看来，又的确是懒汉式的实现。
*/


//枚举
public enum Singleton {
	INSTANCE;
	public void fun1() { 
        // do something
    }
}

Singleton.INSTANCE.fun1();


分类
懒汉式：懒汉式是指应用启动时并不会初始化相应的实例，而是在第一次使用时加载，也就是所谓的延时加载吧，关于延时加载还有很多话聊，笔者就不一一谈了。
饿汉式：饿汉式是指应用启动时就初始化相应的实例，可能说相对来说比较简单。
饿汉式
先讲讲饿汉式，这个比较简单，直接加载就可以了。直接上代码：

public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton(){}
    
    public static Singleton getInstance(){
        return singleton;
    }
}
也有人这样写，不过原理是一样的，都是在类静态初始化阶段初始化实例：

public class Singleton {
    private static Singleton singleton;

    private Singleton(){}
    static {
        singleton = new Singleton();
    }
    public static Singleton getInstance(){
        return singleton;
    }
}
饿汉式没过多可讲的，下面我们分析一下懒汉式。

懒汉式
最简单的实现
不多说，直接上代码。

public class Singleton {
    private static Singleton singleton;

    private Singleton(){}
    
    public static Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
这个代码在单线程环境下会良好运行，但在多线程环境下会有较大问题，也就是所谓的线程不安全。设想一下，线程A在运行到singleton = new Singleton()时，线程B刚好在进行singleton == null, 这时线程B会继续进入if块，而重新对线程A已经实例化的singleton进行重新实例化，这样就冲突了，这还是简单的两个线程，如果是多个线程同时进行，那就比较严重了。
解决这个问题的最简单方法是用同步块synchronized

synchronized实现
public class Singleton {
    private static Singleton singleton;

    private Singleton(){}
    
    public static synchronized Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
这个肯定是线程安全的，因为整个方法都被锁住了，但这样解决了初始化实例的问题，却导致了每次只能有一个线程调用该方法，其他线程都会被锁住，这样就会导致较大的性能损失。解决这个问题可以使用DCL(Double Check Lock)

DCL非线程安全的实现
public class Singleton {
    private static Singleton singleton;

    private Singleton(){}
    
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized(Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
我们先分析一下这个代码。

只有实例第一次被访问时，才会有线程进入同步块，这样极大提高了性能。避免了synchronized带来的较大性能损失。
第一次访问时，如果有多个线程同时进入if块，只有第一个线程会获得锁，其他线程被阻塞，第一个线程可以创建实例。
第一次访问时，被阻塞的线程会进入同步块，进行第二次check,如果此时实例不为null，则返回。
仔细一想，这个代码挺完美的，但是不是这个样子的，具体问题出现在哪呢？
Java程序创建一个实例的过程为：
分配内存空间
初始化对象
将内存空间的地址赋值给对应的引用
但是由于指令重排的原因，什么是指令重排？指令重排序是JVM为了优化指令，提高程序运行效率。指令重排序包括编译器重排序和运行时重排序。JVM规范规定，指令重排序可以在不影响单线程程序执行结果前提下进行。既然这样，那么在应用真正运行时可能是这个样子的：
分配内存空间
将内存空间的地址赋值给对应的引用
初始化对象
线程执行顺序
根据上图分析可以看出new Singleton()时可能会导致错误。所以解决这个问题的方法：

禁止初始化阶段的发生重排序
初始化阶段可以发生重排序，但不能被其他线程“知道”。
DCL线程安全实现--volatile实现
volatile是Java中的一个关键字，使用该关键字修饰的变量在被变更时会被其他变量可见。关于volatile的具体内容，请自行百度。

public class Singleton {
    //通过volatile关键字来确保安全
    private volatile static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
基于ClassLoader的实现
这个方案是利用ClassLoader本身的机制来避免多个线程同时实例化该变量。也就是解决的上面说的2. 初始化阶段可以发生重排序，但不能被其他线程“知道”。

public class Singleton {
    private static class SingletonHolder{
        public static Singleton singleton = new Singleton();
    }
    
    public static Singleton getInstance(){
        return SingletonHolder.singleton;
    }
}

作者：JonahCui
链接：https://www.jianshu.com/p/d82cbb83f393
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。