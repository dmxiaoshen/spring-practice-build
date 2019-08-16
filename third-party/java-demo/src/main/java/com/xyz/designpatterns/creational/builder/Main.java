package com.xyz.designpatterns.creational.builder;

/**
 * Created by hzhsg on 2018/5/2.
 * 建造者模式
 * 应用实例：
 * 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。
 * 2、JAVA 中的 StringBuilder。
 * 优点
 *易于解耦
 *将产品本身与产品创建过程进行解耦，可以使用相同的创建过程来得到不同的产品。也就说细节依赖抽象。
 *易于精确控制对象的创建
 *将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰
 *易于拓展
 *增加新的具体建造者无需修改原有类库的代码，易于拓展，符合“开闭原则“。
 *每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者，用户使用不同的具体建造者即可得到不同的产品对象。
 * 缺点
 *建造者模式所创建的产品一般具有较多的共同点，其组成部分相似；如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
 *如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大。
 */
public class Main {
    public static void main(String[] args) {
        testBuilder();
    }

    /**
     * 普通的工厂方法模式中，同一类产品中不同实例对象的建造过程
     * （SonyPhoneFactory，ApplePhoneFactory中作记号的代码），十分相似。那是不是可以抽出来，从而做到代码复用。
     * 再看看结合了建造者模式的工厂类，就会明白建造者模式就是把建造过程抽到了导演类中（PhoneDirector中作记号的代码）。之所以能够这样，多亏了前面的多个Builder类。
     * 又有疑问了，为了复用重复代码，却要新增那么多类，是不是有点得不偿失？
     * 我猜，建造者模式抽取建造过程，不是为了减少代码量，而是为了把建造顺序统一到一个地方！
     * 以后一旦要修改建造顺序，也只需要修改导演类即可！
     * 至于 抽象工厂模式-建造者模式，由于原理相似，就不哆嗦了。
     * 小结：当一类产品中不同实例对象的建造过程相似，且未来很有可能要修改建造顺序的话，那建造者模式可以处理这种情况。
     * 总结：从 普通的工厂方法模式 到 结合建造者模式的工厂方法模式，对产品用户（Store类）没有影响。
     * 所以对于产品用户来说，是否用建造者模式是不知情的，因为产品用户只关心产品的获取。
     * 所以正如开头所说，工厂模式用于处理 如何获取实例对象 问题，建造者模式用于处理 如何建造实例对象 问题。
     */
    public static void testBuilder(){
        PhoneDirector director = new PhoneDirector();

        Phone iphone = director.constructPhone(new ApplePhoneBuilder());
        iphone.show();
        Phone iphone2 = director.constructPhone(new ApplePhoneBuilder());
        iphone2.show();

        HuaweiPhoneBuilder huaweiPhoneBuilder = new HuaweiPhoneBuilder();
        Phone huawei = director.constructPhone(huaweiPhoneBuilder);
        huawei.show();
        Phone huawei2 = director.constructPhone(huaweiPhoneBuilder);
        huawei2.show();

        System.out.println("-------------------");
        System.out.println(iphone);
        System.out.println(iphone2);
        System.out.println(huawei);
        System.out.println(huawei2);
    }
}
