## final

1. final可以修饰类、方法、属性
2. final用来修饰一个类：此类不能被其他类继承，比如String、StringBuffer、System
3. final用来修饰方法：此方法不能被重写，比如Object类中的getClass()方法
4. 用来修饰变量，此时变量就变成一个常量
   1. 修饰成员变量
      1. 显式初始化
      2. 代码块中赋值
      3. 构造器中初始化
   2. 修饰局部变量，只能调用不能修改
5. static final ：全局常量

## abstract

1. abstract可以修饰的结构：类和方法
2. abstract修饰类：抽象类
   - 此类不能实例化，不能new
   - 抽象类中一定有构造器，便于子类实例化时调用
3. abstract修饰方法：抽象方法
   - 只有方法声明，没有方法体
   - 包含抽象方法的类一定是抽象类，反之不一定
   - 非抽象子类必须重写抽象父类的抽象方法
4. abstract使用注意
   - abstract不能用来修饰：属性、构造器等结构
   - abstract不能用来修饰私有方法、静态方法、final方法、final的类
5. 抽象类的匿名子类

## 接口Interface

1. 接口用Interface来定义
2. 接口和类是并行的结构
3. 定义接口
   1. JDK7及以前，只能定义全局常量和抽象方法
      - 全局常量
      - 抽象方法
   2. JDK8还可以定义静态方法、默认方法
      1. 接口中定义的静态方法，只能通过接口来调用
      2. 通过实现类的对象，可以调用接口的默认方法
      3. 如果实现类重写了默认方法，调用时会调用重写以后的方法
      4. 如果子类（或实现类）继承的父类和实现的接口有同方法签名方法，默认调用父类的方法
      5. 如果实现类实现了多个接口，多个接口定义了相同方法签名的方法，编译冲突
      6. 通过接口.super.方法调用接口的默认方法
4. 接口不能定义构造器
5. 使用implements来实现接口
6. 类可以实现多个接口
7. 接口与接口之间可以继承
8. 接口的具体使用体现多态性
9. 接口可以看作是一种规范
10. 匿名实现类

## 内部类

1. 成员内部类
   1. 静态
   2. 非静态
2. 局部内部类
   1. 方法内
   2. 代码块内
   3. 构造器内

## 异常

### 概述

程序执行过程中发生的不正常的情况。

### 分类

1. 运行时异常
2. 非运行时异常

### 常见的异常

- NullPointerException（空指针异常）
- ArrayIndexOutOfBoundsException（数组越界异常）
- ClassCastException（类型转化异常）
- NumberFormatException（数字转化异常）
- InputMismatchException（输入不匹配异常）
- ArithmeticException（算术异常）

### 异常处理

1. try-catch-finally
2. throws
3. throw：手动抛出

## 线程

### 概念

- 程序是为了完成特定任务，是一组指令的集合。指的是一段静态代码
- 进程是运行起来的程序，或者说是程序一次运行过程，有其生命周期
- 线程：进程可以细化为线程
  - 若一个进程同一时间并行执行多个线程，就是支持多线程的
  - 线程作为调度和执行单位，每个线程拥有独立的运行栈和程序计数器，线程切换开销小
  - 一个进程中的多个线程共享内存单元/内存地址空间

### 线程的创建和使用

1. 继承Thread类

   1. 创建一个继承于Thread类的子类
   2. 重写Thread类的run()方法
   3. 创建Thread类的子类的对象
   4. 通过此对象调用start()

   ```java
   public class ThreadTest {
       public static void main(String[] args) {
           // 3.创建子类对象
           MyTread t1 = new MyTread();
           // 4.调用start方法
           t1.start();
           // 问题一：不能直接调用run方法启动线程，此时启动的是main线程
           // t1.run();
           // 问题二：在启动一个线程不能让已经启动的线程去载此启动，需要重新new一个对象
           // t1.start();
           MyTread t2 = new MyTread();
           t2.start();
           //以下操作在main线程中
           for (int i = 0; i < 100; i++) {
               if(i%2==0){
                   System.out.println(Thread.currentThread().getName() + "***" + i);
               }
           }
       }
   }
   
   class MyTread  extends Thread{// 1.继承Thread类
       @Override
       public void run() { // 2.实现run方法
           for (int i = 0; i < 100; i++) {
               if(i%2==0){
                   System.out.println(Thread.currentThread().getName() + "***" + i);
               }
           }
       }
   }
   ```

2. 实现Runable接口

   1. 创建一个实现Runable接口的类
   2. 重写run()方法
   3. 创建实现类的对象
   4. 将此对象传入到Thread类的构造其中，创建Thread对象
   5. 通过Thread类的对象调用start()方法



> 两种方式对比

优先使用实现Runable接口

1. 实现方式比继承局限性大
2. 实现方式更适合来处理多个线程共享数据情况

### 线程的常用方法

1. start()：启动当前线程；调用当前线程的run()
2. run()：通常需要重写Thread类中此方法，将创建的线程要执行的操作写在此方法中
3. currentThread()：静态方法，返回执行当前代码的线程
4. getName()：获取当前线程的名字
5. setName()：设置当前线程的名字
6. yield()：释放当前cpu的执行权
7. join()：在线程A中调用线程B.join()，此时线程A就进入阻塞状态，直到线程B执行完以后，线程A才结束阻塞状态
8. stop()：强制结束线程生命周期（已废弃）
9. sleep(long millitime)：将当前线程休眠一段时间,在指定毫秒时间类线程是阻塞状态
10. isAlive()：判断线程是否还在生命周期内

### 线程优先级

在cpu空闲的时候，优先级高的执行概率高，并非一定优先级高就线执行。

1. MIN_PRIORITY：最小优先级 1
2. NORM_PRIORITY：默认优先级 5
3. MAX_PRIORITY：最大优先级 10

### 线程的生命周期

1. 初始化

2. 就绪（可运行）

3. 运行

4. 阻塞

5. 死亡

   ![](./images/线程生命周期.png)

### 线程的同步

多个线程共享数据，会造成操作不完整。

1. 同步代码块

   ```java
   synchronized(同步监视器) {
   	// 需要被同步的代码	
   }
   ```

   - 操作共享数据的的代码是需要被同步的代码
   - 共享数据：多个线程共同操作的变量，比如卖车票的车票
   - 同步监视器：锁，任何一个类的对象，都可以当锁
     - 要求：多个线程必须要共用一把锁
     - 实现Runable接口的可以使用`synchronized(this) `，此时this是唯一的
     - 在继承Thread类的可以使用`synchronized(字符串/类)` 

2. 同步方法

   如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。

   - 就是将代码块抽成方法并加上`synchronized`
   - 如果是实现Runable接口的类的锁就是this
   - 如果是继承Thread类的子类的锁的同步方法需要使用static

3. 死锁

   不同的线程分别占用对方需要的资源不放弃，都在等待获取对方放弃自己需要的同步资源，就形成了线程的死锁。

   出现死锁不会出现异常，不会提示，只会阻塞。

4. Lock(锁)

   - 从JDK5.0开始，通过显式定义同步锁对象来实现同步。同步锁使用Lock对象充当。

   - java.util.concurrent.locks.Lock接口时控制多个线程对共享资源进行访问的工具

   - 锁提供了对共享资源的独占访问，每次只能有一个线程对Lock对象加锁，线程开始访问共享资源之前应该先获得Lock对象

   - ReentrantLock类实现了Lock接口，它与**synchronized**相同的并发性和内存语义

   - 使用Lock

     1. 实例化一个lock

        ```java
        private Lock lock = new ReentrantLock();
        ```

        

     2. 调用lock方法，锁住资源

        ```java
        lock.lock();
        try{
            ...
        }
        ```

        在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
        **说明一：**如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
        **说明二：**如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
        **说明三：**在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。

     3. 在finally中释放锁

        ```java
        finally {
            //3.在finally中解锁
            lock.unlock();
        }
        ```

        

     4. lock对象与synchronized一样都需要保证锁唯一性，如果是继承thread类的需要使用**static**

        ```java
        private static Lock lock = new ReentrantLock();
        ```

        

### 线程的通信

> 线程通信的例子：使用2个线程打印1-100：线程1线程2交替打印

```java
public class CommunitarianTest {
    public static void main(String[] args) {
        Num num = new Num();
        Thread t1 = new Thread(num, "线程1");
        Thread t2 = new Thread(num, "线程2");
        t1.start();
        t2.start();
    }
}

class Num implements Runnable{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //            lock.lock();
                try {
                    notify();
                    if(num<=100){
                        Thread.sleep(10);
                        System.out.println(Thread.currentThread().getName()+":"+num);
                        num++;
                        wait();
                    }else{
                        break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
//                lock.unlock();
                }
            }
        }
    }
}
```

> 涉及到三个方法

 * wait()：一旦执行此方法，当前线程就会进入阻塞状态，并释放锁
 * notify()：一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程，就会唤醒优先级高的那个
 * notifyAll()：一旦执行此方法，就会唤醒所有被wait的线程

> 说明

- 线程通信三个方法只能用在同步方法和同步代码块中
 * wait()和notify()都是this调用的
 * 三个方法定义在Object中

> 面试题：sleep()和wait()的异同

 * 相同点：一旦执行方法，都可以时当前线程进入阻塞状态
 * 不同点：
    1. 两个方法声明位置不同：Thread类中声明sleep()，Object类中声明wait()
    *  调用地方不同：sleep()可以在任何地方调用，wait()只能在同步代码块或同步方法中
    3. sleep(long m)到指定时间后变成就绪状态，wait()必须等待唤醒(**notify、notifyAll**)
    4. 关于是否释放锁：如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放锁，wait()会释放锁

> 生产者/消费者经典问题

生产者(Productor)将产品交给店员(Clerk),而消费者(Customer)从店员处取走产品，店员一次最多只能持有固定数量的产品(比如20)，如果生产者试图通过生产更多的产品，店员会让生产者停一下，如果店员中有空位放产品了，在通知生产者继续生产，如果店员手里没有产品了，店员会让消费者等一下，如果有产品在通知消费者。

> 可能出现问题：

 * 生产者生产比消费者消费快，消费者会漏掉数据

 * 消费者比生产者快，消费者会取到相同的数据

   ```java
   public class ProductTest {
   
       public static void main(String[] args) {
           Clerk clerk = new Clerk();
           Thread p1 = new Thread(new Productor(clerk), "生产者1");
           Thread c1 = new Thread(new Customer(clerk), "消费者1");
           p1.start();
           c1.start();
   
       }
   }
   
   class Productor implements Runnable { // 生产者
       private Clerk clerk;
   
       public Productor(Clerk clerk) {
           this.clerk = clerk;
       }
   
       @Override
       public void run() {
           System.out.println(Thread.currentThread().getName()+"开始生产产品");
           while (true) {
               try {
                   Thread.sleep(100);
               }catch (Exception e){
                   e.printStackTrace();
               }
               clerk.addProduct();
           }
       }
   }
   class Customer implements Runnable{ // 消费者
       private Clerk clerk;
   
       public Customer(Clerk clerk) {
           this.clerk = clerk;
       }
   
       @Override
       public void run() {
           System.out.println(Thread.currentThread().getName()+"开始消费产品");
           while (true) {
               try {
                   Thread.sleep(200);
               }catch (Exception e){
                   e.printStackTrace();
               }
               clerk.subProduct();
           }
       }
   }
   class Clerk {
       private int productCount = 0;
   
       public synchronized void addProduct(){
           if(productCount<20){
               productCount++;
               System.out.println("生产者开始生产第"+productCount+"个产品");
               notify();//生产者生产完了唤醒消费者线程
           }else {
               try {
                   wait();//等待
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
       public synchronized void subProduct(){
           if(productCount>0){
               System.out.println("消费者开始消费第"+productCount+"个产品");
               productCount--;
               notify();
           }else {
               try {
                   wait();//等待
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }
   ```

   

### JDK5.0新增线程创建方式

#### 实现Callable接口

> 与使用Runable相比，Callable功能更强大

1. 相比run()方法，可以有返回值
2. 方法可以抛出异常
3. 支持泛型返回值
4. 需要借助FutureTask类，比如获取返回结果

> Future接口

1. 可以对具体的Runable、Callable任务的执行结果进行取消、查询是否完成、获取结果等
 *  FutureTask是Future接口的唯一实现类
 *  FutureTask同时实现Runable接口和Future接口。它即作为Runable被线程执行，又可以作为Future得到Callable的返回值

> 操作步骤

 *  1.创建一个实现Callable接口的实现类
 *  2.重写call()方法，向线程需要操作的代码写在call()方法中
 *  3.创建Callable接口实现类的对象
 *  4.将Callable接口实现类的对象传入FutureTask构造器中，创建一个FutureTask的对象
 *  5.将FutureTask的对象传入Thread构造器，创建Thread对象，调用start()方法
 *  6.获取call()方法返回值

```java
public class CallableTest {

    public static void main(String[] args) {
        Call call = new Call();//3.创建Callable接口实现类的对象
        FutureTask futureTask = new FutureTask<>(call);//4.将Callable接口实现类的对象传入FutureTask构造器中，创建一个FutureTask的对象
        new Thread(futureTask).start();//启动线程,5.将FutureTask的对象传入Thread构造器，创建Thread对象，调用start()方法
        try { // futuretask.get方法的返回值就是重写call()方法的返回值,6.获取call()方法返回值
            Object sum = futureTask.get();
            System.out.println(sum);
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
class Call implements Callable{//1.创建一个实现Callable接口的实现类

    @Override
    public Object call() throws Exception {//2.重写call()方法，向线程需要操作的代码写在call()方法中
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i%2==0){
                System.out.println(i);
                sum+=i;
            }
        }
        return sum;
    }
}
```



#### 使用线程池

> 问题：经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大

如果我们能提前创建好多个线程，放入线程池中，使用的时候直接获取，使用完毕就放回池中。可以避免频繁创建销毁线程、实现重复利用。类似公交车、地铁。

> 优点

1. 提高响应速度（减少创建新线程的时间）
2. 降低资源消耗（重复利用线程池中的线程，无需每次都创建）
3. 便于线程管理
   - corePoolSize：核心池的大小
   - maximumPoolSize：最大线程数
   - keepAliveTime：线程无任务最大存在时间

>  线程池创建线程
>
> ExecutorService和Executors
>
> ExecutorService：真正的线程池接口，常见子类ThreadPoolExecutor

 *  1.void execute(Runnable command)：执行任务/命令，没有返回值，一般用来执行Runable
 *  2.<T> Future<T> submit(Callable<T> task)：执行任务/命令，有返回值，一般执行Callable
 *  3.void shutdown()：关闭连接池

> Executors：工具类，线程池工厂类，用于创建返回不同类型的线程池

 *         Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
 *         Executors.newFixedThreadPool()：创建一个可重用固定线程数的线程池
 *         Executors.newSingleThreadExecutor()：创建一个只有一个线程的线程池
 *         Executors.newScheduledThreadPool()：创建一个线程池，可以安排延迟执行或者定期执行

> 使用线程池

1. 提供指定线程数量的线程池
2. 执行指定线程的操作，需要提供实现Runable接口或者Callable的实现类
3. 关闭连接池

```java
public class ThreadPoolTest {

    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //设置线程池的属性
//        ((ThreadPoolExecutor) service).setCorePoolSize(10);

        //2.执行指定线程的操作，需要提供实现Runable接口或者Callable的实现类
        service.execute(new NumberThread());//适合适用于Runable
        service.submit(new NumberThread2());//适合适用于Callable

        service.shutdown();//关闭连接池

    }
}
class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
class NumberThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum+=i;
            }
        }
        return sum;
    }
}
```

> 注意

线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：

 * 1）FixedThreadPool和SingleThreadPool:

   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:

   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM

```java
 ExecutorService service = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>() );
```

##### **1. 为什么使用线程池**

诸如 Web 服务器、数据库服务器、文件服务器或邮件服务器之类的许多服务器应用程序都面向处理来自某些远程来源的大量短小的任务。请求以某种方式到达服务器，这种方式可能是通过网络协议（例如 HTTP、FTP 或 POP）、通过 JMS 队列或者可能通过轮询数据库。不管请求如何到达，服务器应用程序中经常出现的情况是：单个任务处理的时间很短而请求的数目却是巨大的。

构建服务器应用程序的一个简单模型是：每当一个请求到达就创建一个新线程，然后在新线程中为请求服务。实际上对于原型开发这种方法工作得很好，但如果试图部署以这种方式运行的服务器应用程序，那么这种方法的严重不足就很明显。每个请求对应一个线程（thread-per-request）方法的不足之一是：为每个请求创建一个新线程的开销很大；为每个请求创建新线程的服务器在创建和销毁线程上花费的时间和消耗的系统资源要比花在处理实际的用户请求的时间和资源更多。

除了创建和销毁线程的开销之外，活动的线程也消耗系统资源。在一个 JVM 里创建太多的线程可能会导致系统由于过度消耗内存而用完内存或“切换过度”。为了防止资源不足，服务器应用程序需要一些办法来限制任何给定时刻处理的请求数目。

线程池为线程生命周期开销问题和资源不足问题提供了解决方案。通过对多个任务重用线程，线程创建的开销被分摊到了多个任务上。其好处是，因为在请求到达时线程已经存在，所以无意中也消除了线程创建所带来的延迟。这样，就可以立即为请求服务，使应用程序响应更快。而且，通过适当地调整线程池中的线程数目，也就是当请求的数目超过某个阈值时，就强制其它任何新到的请求一直等待，直到获得一个线程来处理为止，从而可以防止资源不足。

##### **2. 使用线程池的风险**

虽然线程池是构建多线程应用程序的强大机制，但使用它并不是没有风险的。用线程池构建的应用程序容易遭受任何其它多线程应用程序容易遭受的所有并发风险，诸如同步错误和死锁，它还容易遭受特定于线程池的少数其它风险，诸如与池有关的死锁、资源不足和线程泄漏。

###### **2.1 死锁**

任何多线程应用程序都有死锁风险。当一组进程或线程中的每一个都在等待一个只有该组中另一个进程才能引起的事件时，我们就说这组进程或线程 死锁了。死锁的最简单情形是：线程 A 持有对象 X 的独占锁，并且在等待对象 Y 的锁，而线程 B 持有对象 Y 的独占锁，却在等待对象 X 的锁。除非有某种方法来打破对锁的等待（Java 锁定不支持这种方法），否则死锁的线程将永远等下去。

虽然任何多线程程序中都有死锁的风险，但线程池却引入了另一种死锁可能，在那种情况下，所有池线程都在执行已阻塞的等待队列中另一任务的执行结果的任务，但这一任务却因为没有未被占用的线程而不能运行。当线程池被用来实现涉及许多交互对象的模拟，被模拟的对象可以相互发送查询，这些查询接下来作为排队的任务执行，查询对象又同步等待着响应时，会发生这种情况。

###### **2.2 资源不足**

线程池的一个优点在于：相对于其它替代调度机制（有些我们已经讨论过）而言，它们通常执行得很好。但只有恰当地调整了线程池大小时才是这样的。线程消耗包括内存和其它系统资源在内的大量资源。除了 Thread 对象所需的内存之外，每个线程都需要两个可能很大的执行调用堆栈。除此以外，JVM 可能会为每个 Java 线程创建一个本机线程，这些本机线程将消耗额外的系统资源。最后，虽然线程之间切换的调度开销很小，但如果有很多线程，环境切换也可能严重地影响程序的性能。

如果线程池太大，那么被那些线程消耗的资源可能严重地影响系统性能。在线程之间进行切换将会浪费时间，而且使用超出比您实际需要的线程可能会引起资源匮乏问题，因为池线程正在消耗一些资源，而这些资源可能会被其它任务更有效地利用。除了线程自身所使用的资源以外，服务请求时所做的工作可能需要其它资源，例如JDBC 连接、套接字或文件。这些也都是有限资源，有太多的并发请求也可能引起失效，例如不能分配 JDBC 连接。

###### **2.3 并发错误**

线程池和其它排队机制依靠使用 wait() 和 notify() 方法，这两个方法都难于使用。如果编码不正确，那么可能丢失通知，导致线程保持空闲状态，尽管队列中有工作要处理。使用这些方法时，必须格外小心。而最好使用现有的、已经知道能工作的实现，例如 util.concurrent 包。

###### **2.4 线程泄漏**

各种类型的线程池中一个严重的风险是线程泄漏，当从池中除去一个线程以执行一项任务，而在任务完成后该线程却没有返回池时，会发生这种情况。发生线程泄漏的一种情形出现在任务抛出一个 RuntimeException 或一个 Error 时。如果池类没有捕捉到它们，那么线程只会退出而线程池的大小将会永久减少一个。当这种情况发生的次数足够多时，线程池最终就为空，而且系统将停止，因为没有可用的线程来处理任务。

有些任务可能会永远等待某些资源或来自用户的输入，而这些资源又不能保证变得可用，用户可能也已经回家了，诸如此类的任务会永久停止，而这些停止的任务也会引起和线程泄漏同样的问题。如果某个线程被这样一个任务永久地消耗着，那么它实际上就被从池除去了。对于这样的任务，应该要么只给予它们自己的线程，要么只让它们等待有限的时间。

###### **2.5 请求过载**

仅仅是请求就压垮了服务器，这种情况是可能的。在这种情形下，我们可能不想将每个到来的请求都排队到我们的工作队列，因为排在队列中等待执行的任务可能会消耗太多的系统资源并引起资源缺乏。在这种情形下决定如何做取决于您自己；在某些情况下，您可以简单地抛弃请求，依靠更高级别的协议稍后重试请求，您也可以用一个指出服务器暂时很忙的响应来拒绝请求。

##### **3.** **有效使用线程池的准则**

只要您遵循几条简单的准则，线程池可以成为构建服务器应用程序的极其有效的方法：

不要对那些同步等待其它任务结果的任务排队。这可能会导致上面所描述的那种形式的死锁，在那种死锁中，所有线程都被一些任务所占用，这些任务依次等待排队任务的结果，而这些任务又无法执行，因为所有的线程都很忙。

在为时间可能很长的操作使用合用的线程时要小心。如果程序必须等待诸如 I/O 完成这样的某个资源，那么请指定最长的等待时间，以及随后是失效还是将任务重新排队以便稍后执行。这样做保证了：通过将某个线程释放给某个可能成功完成的任务，从而将最终取得某些进展。

理解任务。要有效地调整线程池大小，您需要理解正在排队的任务以及它们正在做什么。它们是 CPU 限制的（CPU-bound）吗？它们是 I/O 限制的（I/O-bound）吗？您的答案将影响您如何调整应用程序。如果您有不同的任务类，这些类有着截然不同的特征，那么为不同任务类设置多个工作队列可能会有意义，这样可以相应地调整每个池。

##### **4. 线程池的大小设置**

调整线程池的大小基本上就是避免两类错误：线程太少或线程太多。幸运的是，对于大多数应用程序来说，太多和太少之间的余地相当宽。

请回忆：在应用程序中使用线程有两个主要优点，尽管在等待诸如 I/O 的慢操作，但允许继续进行处理，并且可以利用多处理器。在运行于具有 N 个处理器机器上的计算限制的应用程序中，在线程数目接近 N 时添加额外的线程可能会改善总处理能力，而在线程数目超过 N 时添加额外的线程将不起作用。事实上，太多的线程甚至会降低性能，因为它会导致额外的环境切换开销。

线程池的最佳大小取决于可用处理器的数目以及工作队列中的任务的性质。若在一个具有 N 个处理器的系统上只有一个工作队列，其中全部是计算性质的任务，在线程池具有 N 或 N+1 个线程时一般会获得最大的 CPU 利用率。

对于那些可能需要等待 I/O 完成的任务（例如，从套接字读取 HTTP 请求的任务），需要让池的大小超过可用处理器的数目，因为并不是所有线程都一直在工作。通过使用概要分析，您可以估计某个典型请求的等待时间（WT）与服务时间（ST）之间的比例。如果我们将这一比例称之为 WT/ST，那么对于一个具有 N 个处理器的系统，需要设置大约 N*(1+WT/ST) 个线程来保持处理器得到充分利用。

处理器利用率不是调整线程池大小过程中的唯一考虑事项。随着线程池的增长，您可能会碰到调度程序、可用内存方面的限制，或者其它系统资源方面的限制，例如套接字、打开的文件句柄或数据库连接等的数目。

##### **5. 常用的几种线程池**

###### **5.1 newCachedThreadPool**

创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。

这种类型的线程池特点是：

- 工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
- 如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
- 在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。

示例代码如下：

```java
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }
}
```



###### **5.2 newFixedThreadPool**

创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。

FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。

示例代码如下：

```java
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
```

因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。

定长线程池的大小最好根据系统资源进行设置如Runtime.getRuntime().availableProcessors()。

 

###### **5.3 newSingleThreadExecutor**

创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。

示例代码如下：

```java
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
```



###### **5.4 newScheduleThreadPool**

创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。

延迟3秒执行，延迟执行示例代码如下：

```JAVA
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }
}
```

表示延迟1秒后每3秒执行一次，定期执行示例代码如下：

```java
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
```

### Volatitle关键字

> Java的volatile关键字用于标记一个变量“应当存储在主存”。更确切地说，每次读取volatile变量，都应该从主存读取，而不是从CPU缓存读取。每次写入一个volatile变量，应该写到主存中，而不是仅仅写到CPU缓存。

> volatile关键字的特性

- 内存可见性
- 禁止指令重排列

> 什么时内存可见性？

Java虚拟机规范试图定义一种Java内存模型（JMM）,来屏蔽掉各种硬件和操作系统的内存访问差异，让Java程序在各种平台上都能达到一致的内存访问效果。

![]()

## Java常用类

### 字符串相关

#### 概念

- String类是一个final类，不能被继承，代表不可变的字符串序列
- 字符串是常量，在初始化后不能修改
- String对象的字符内存存储在一个char[]中
 * String 实现了Serializable接口，可序列化
 * String 实现了Comparable，可以比较大小
 - String代表不可变字符序列
    *  当对字符串重新赋值时需要重写指定内存区域赋值，不能使用原有的value进行赋值
    *  当对现有字符串进行连接操作的时候，也需要重新指定内存区域赋值，并将引用指向新的内存地址
 *  调用replace()方法修改制定字符或字符串时，也必须重新指定内存区域赋值
 * 通过字面量的形式（区别于new）给一个字符串赋值，此时额字符串声明在字符串常量中（方法区中）
 * 字符串常量池不会存储相同内容的字符串

#### String类

> 实例化方式

1. 通过字面量方式，常量池方法区（`String str = "abc"`）
   1. 常量池中创建一个`"abc"`的字符串常量
   2. 栈中新建一个str的引用，指针指向常量池的`"abc"`的内存地址
2. 通过new方式，堆中（`String str = new String("abc")`）
   1. 常量池中创建一个`"abc"`的字符串常量
   2. 堆中创建new结构对象，指针指向常量池的`"abc"`的内存地址
   3. 栈中新建一个str的引用，指针指向堆中的new对象的内存地址

> 面试题：`String str = new String("abc")`方式创建对象，在内存中创建了几个对象？

两个对象：一个是堆空间中的new结构，另一个是char[]对应的常量池中的`"abc"`。

> 判断字符串

```java
public void test3(){
    String s1 = "JavaEE";
    String s2 = "hadoop";
    String s3 = "JavaEEhadoop";
    String s4 = "JavaEE" + "hadoop";
    String s5 = s1 + "hadoop";
    String s6 = "JavaEE" + s2;
    String s7 = s1 + s2;

    System.out.println(s3==s4);//true
    System.out.println(s3==s5);//false
    //intern方法返回常量池中的引用，s3是常量池"JavaEEhadoop"的引用地址，s5.intern()也返回常量池"JavaEEhadoop"的引用地址
    System.out.println(s3==s5.intern());//true
    System.out.println(s3==s6);//false
    System.out.println(s5==s6);//false
    System.out.println(s3==s7);//false
    System.out.println(s5==s7);//false
    System.out.println(s6==s7);//false
}
```

- 常量与常量的的拼接结果在常量池，且常量池中不会存在相同的结果
- 只要其中一个是变量，结果就在堆中
- 如果调用intern()方法，返回值常量池中的

> 测试题

```java
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};
    public void change(String str, char[] ch){
        str = "test ok";
        ch[0] = 'b';
    }
    public static void main(String[] args) {
        StringTest ex = new StringTest();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and");
        System.out.println(ex.ch);//good andbest
    }
}
```

成员变量`str`传入形参`str`局部变量，此时两个str都指向`new String("good")`的地址，之后形参`str`的局部变量引用指向常量池的`test`的地址，但是成员变量的地址没变化，不改变，ch是数组，数组地址不变，修改数组的内部值生效，所以`t`改成`b`了。

> 关于String的内存结构，JDK1.7以前字符串常量池在方法区(**永久代实现**)中，JDK1.7，将字符串常量池移入heap(**堆中**)，JDK1.8又将常量池移入方法区(**元空间实现**)。

##### String常用API

- int length()：返回字符串长度 
- char charAt(int index)：返回某索引处的字符串
- boolean isEmpty()：判断是不是空字符串 length==0
- String toLowerCase()：将所有字符转化为小写
- String toUpperCase()：将所有字符转化未大写
- String trim()：返回字符串的副本，将前后空白忽略
- boolean equals(Object obj)：比较两个字符串内容是否相同
- boolean equalsIgnoreCase(String anoterString)：与equals类似，忽略大小写比较
- String concat(String str)：将指定的字符串连接到此字符串的尾部，等价于“+”
- int compareTo(String anoterString)：比较两个字符串的大小
- String subString(int beginIndex)：返回一个新的字符串，此字符串从beginIndex开始截取的副本
- String subString(int beginIndex, int endIndex)：返回与一个新的字符串，此字符串从beginIndex到endIndex截取的副本，不包含endIndex

#### StringBuilder类和StringBuffer类

- StringBuilder与StringBuffer都是是字符可变序列，String是字符不可变序列，底层都是
- StringBuilder是线程不安全的，效率高
- StringBuffer是线程安全的，效率相对低
- StringBuilder与StringBuffer的底层是一个长度为16的数组
- 扩容机制：当长度超过底层数组容量，扩容为原长度*2+2，同时将原数组的数据复制到新的数组中

> 效率对比

**StringBuilder>StringBuffer>String**

### 时间API

#### JDK 8之前

1. java.lang.System类
2. java.util.Date
3. java.text.SimpleDateFormat
4. java.util.Calendar

#### JDK 8之后

1. LocalDate、LocalTime、LocalDateTime
2. Instant（瞬时类）
3. java.time.format.DateTimeFormat（格式化）

### Java比较器

> 针对对象比较大小使用比较器，基本数据类型使用运算符比较大小。

#### Comparable接口

> 重写compareTo(obj)
>

- 如果this大于obj，返回正整数
- 如果this小于obj，返回负整数
- 如果相等返回0

```java
public class ComparableTest {
    @Test
    public void test(){
        Good[] goods = new Good[4];
        goods[0] = new Good("apple", 100);
        goods[1] = new Good("xiaomi", 50);
        goods[2] = new Good("thinkpad", 60);
        goods[3] = new Good("dell", 80);
        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));
    }
}

class Good implements Comparable{
    String name;
    int price;

    public Good(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Good){ // 根据价格判断大小
            return this.price -((Good) o).price;
        }else {
            throw new ClassCastException("不是good类型");
        }
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
```



#### Comparator接口

> 重写compare(o1, o2)

- 如果返回正整数，o1大于o2
- 如果返回负整数，o1小于o2
- 如果返回0，相等

```java
public class ComparatorTest {
    @Test
    public void test(){
        Good2[] goods = new Good2[4];
        goods[0] = new Good2("apple", 100);
        goods[1] = new Good2("xiaomi", 50);
        goods[2] = new Good2("thinkpad", 60);
        goods[3] = new Good2("dell", 80);
        Arrays.sort(goods, new Comparator<Good2>() {
            @Override
            public int compare(Good2 o1, Good2 o2) {
                return o1.price-o2.price;
            }
        });
        System.out.println(Arrays.toString(goods));
    }

}
class Good2 {
    String name;
    int price;

    public Good2(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good2{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
```



### System类

> 代表系统，系统级的很多属性和控制方法都放置在该类的内部。

- native long currentTimeMillis()：返回当前的计算机时间
- void exit(int status)：退出程序
- void gc()：请求系统进行垃圾回收，至于是否回收取决于gc算法
- String getProperty(String key)：用于获取系统中key值为key的value值
  - java.version：java运行时环境版本信息
  - java.home：java的安装目录
  - os.name：操作系统的名称
  - os.version：操作系统的版本
  - user.name：用户的账户名
  - user.home：用户的主目录
  - user.dir：用户的当前工作目录

### Math类

> 提供一系列静态方法用于科学计算

- abs：绝对值
- acos,asin,atan,cos,sin,tan：三角函数
- sqrt：平方根
- pow(double, double b)：a的b次幂
- log：自然对数
- exp：e为底指数

### BigInterger与BigDecimal



## 枚举类与注解

### 枚举类

> 如何自定义枚举类

- JDK5之前，自定义枚举类
- JDK5之后，可以使用enum关键字定义枚举类

> 使用关键字enum定义枚举类

```java
public enum Season {
    // 提供当前枚举类的对象，用逗号“,”隔开，末尾对象用分号“;”
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");
    // 声明属性使用 private final修饰
    private final String name;
    private final String desc;
    // 构造器
    Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
    // get方法
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
```



> Enum类的主要方法

- values()：返回枚举类型的对象数组
- valueof(String str)：可以把一个字符串转为对应的枚举对象。要求字符串必须是枚举对象的名
- toString()：返回当前枚举类对象常量的名称

### 注解

> 概述

- JDK5.0开始，Java增加了对`Annotation`（注解）的支持，也就是元数据
- `Annotation`是代码的特殊标记，类加载，运行时呗读取，并执行相应的代码，可以不改变原逻辑，在源文件嵌入一些补充信息
- `Annotation`可以像修饰符一样使用，可用于修饰包，类，构造器，方法，成员变量，参数，局部变量的声明，这些信息保存在`Annotation`的“`name=value`”

> 常用注解

- `@Override`：限定重写父类方法，用于方法
- `@Deprecated`：用于表示所修饰的元素（类、方法等）已过时
- `@SuppressWarnings()`：抑制编译器警告

> 自定义注解

- 定义新的``Annotation`使用`@interface`关键字
- 自定义注解自动继承`java.lang.Annotation`接口
- `Annotation`的成员变量以无参数方法的形式声明。其中方法名和返回值定义了该成员的名字和类型。类型如下：
  - 八种基本数据类型
  - String类型
  - Class类型
  - enum类型
  - Annotation类型
  - 以上所有类型的数组
- 成员变量初始值可以设置，可以使用`default`关键字
- 如果定义了成员变量没有给默认值，那么使用时候必须赋值
- 如果没有成员定义的`Annotation`称为**标记**，包含成员的称为**元数据**

```java
public @interface MyAnnotation {
    String value();
}
```

> 元注解

对现有的注解进行解释说明，就是修饰注解的注解。

1. `@Retention()`：修饰一个注解，指定其生命周期。`@Retention()`包含一个`RetentionPolicy`类型的参数，必须赋值：

   1. `RetentionPolicy.SOURCE`：在源文件（.java文件）有效，编译器直接忽略
   2. `RetentionPolicy.CLASS`：在class文件中有效，jvm不会保留，**默认值**
   3. `RetentionPolicy.RUNTIME`：在运行时有效（运行时保留），当运行java程序时候，JVM将保留注释，程序通过反射获取该注释

2. `@Target()`：修饰一个注解，指定被修饰的注解的修饰范围，包含一个**value**的成员变量：

   | TYPE            | 用于描述类、接口（包括注解类型）或enum声明 |
   | --------------- | ------------------------------------------ |
   | FIELD           | 用于描述域                                 |
   | METHOD          | 用于描述方法                               |
   | PARAMETER       | 用于描述参数                               |
   | CONSTRUCTOR     | 用于描述构造器                             |
   | LOCAL_VARIABLE  | 用于描述局部变量                           |
   | ANNOTATION_TYPE | 用于描述注解                               |
   | PACKAGE         | 用于描述包                                 |
   | TYPE_PARAMETER  | 参数类型描述 JDK8新增                      |
   | TYPE_USE        | 描述任何类型JDK8新增                       |

   

3. `@Documented`：被该元注解修饰的`Annotation`将被javadoc工具提取成文档。默认情况，javadoc是不包括文档的。

4. `@Inherited`：被修饰注解将具有可继承性。如果类A使用了被`@Inherited`修饰的注解，则其子类将自动具有该注解。

> JDK8新增

`@Repeatable`：可重复性注解

