package com.xyz.designpatterns.structure.flyweight;

/**
 * Created by hzhsg on 2018/5/7.
 * 享元模式
 * 享元模式可以再次创建对象 也可以取缓存对象
 * 单例模式则是严格控制单个进程中只有一个实例对象
 * 享元模式可以通过自己实现对外部的单例 也可以在需要的使用创建更多的对象
 * 单例模式是自身控制 需要增加不属于该对象本身的逻辑
 * 两者都可以实现节省对象创建的时间 ThreadPool 线程池 与数据库连接池 都有使用享元模式
 * 应用实例：
 * 1、JAVA 中的 String，如果有则返回，如果没有则创建一个字符串保存在字符串缓存池里面。
 * 2、数据库的数据池。
 */
public class Main {

    public static void main(String[] args) {
            testFlyweight();
    }

    /**
     * 优点
     1、享元模式的优点在于它能够极大的减少系统中对象的个数。

     2、享元模式由于使用了外部状态，外部状态相对独立，不会影响到内部状态，所以享元模式使得享元对象能够在不同的环境被共享。

     缺点
     1、由于享元模式需要区分外部状态和内部状态，使得应用程序在某种程度上来说更加复杂化了。

     2、为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。

     五、 模式适用场景
     1、如果一个系统中存在大量的相同或者相似的对象，由于这类对象的大量使用，会造成系统内存的耗费，可以使用享元模式来减少系统中对象的数量。

     2、对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。


     六、 模式总结
     1、享元模式可以极大地减少系统中对象的数量。但是它可能会引起系统的逻辑更加复杂化。

     2、享元模式的核心在于享元工厂，它主要用来确保合理地共享享元对象。

     3、内部状态为不变共享部分，存储于享元享元对象内部，而外部状态是可变部分，它应当油客户端来负责。
     */
    public static void testFlyweight(){
        Club club1 = ClubFactory.get("文艺社");
        club1.show();
        Club club2 = ClubFactory.get("舞蹈社");
        club2.show();
        Club club3 = ClubFactory.get("足球社");
        club3.show();
        Club club4 = ClubFactory.get("舞蹈社");
        club4.show();
        System.out.println("总共创建了"+ClubFactory.getSize()+"个社团");
    }
}
