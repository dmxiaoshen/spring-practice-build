package com.xyz.designpatterns.structure.decorator;

/**
 * Created by hzhsg on 2018/5/4.
 * 装饰者模式
 * 装饰者模式：若要扩展功能，装饰者提供了比集成更有弹性的替代方案，动态地将责任附加到对象上。
 * 先简单描述下装饰者模式发挥作用的地方，当我们设计好了一个类，我们需要给这个类添加一些辅助的功能，
 * 并且不希望改变这个类的代码，这时候就是装饰者模式大展雄威的时候了。
 * 这里还体现了一个原则：类应该对扩展开放，对修改关闭。
 */
public class Main {

    public static void main(String[] args) {
        testDecorator();
    }

    public static void testDecorator(){
        Coffee plan1 = new MilkCoffeeDecorator(new MochaCoffee());
        Coffee plan2 = new MilkCoffeeDecorator(new SugarCoffeeDecorator(new LatteCoffee()));

        plan1.show();
        plan2.show();
    }
}
