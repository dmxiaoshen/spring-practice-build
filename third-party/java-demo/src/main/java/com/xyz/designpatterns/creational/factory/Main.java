package com.xyz.designpatterns.creational.factory;

/**
 * Created by hzhsg on 2018/5/2.
 * 无论是简单工厂模式，工厂方法模式，还是抽象工厂模式，他们都属于工厂模式，
 * 在形式和特点上也是极为相似的，他们的最终目的都是为了解耦。
 * 在使用时，我们不必去在意这个模式到底工厂方法模式还是抽象工厂模式，
 * 因为他们之间的演变常常是令人琢磨不透的。经常你会发现，
 * 明明使用的工厂方法模式，当新需求来临，稍加修改，
 * 加入了一个新方法后，由于类中的产品构成了不同等级结构中的产品族，
 * 它就变成抽象工厂模式了；而对于抽象工厂模式，当减少一个方法使的提供的产品不再构成产品族之后，
 * 它就演变成了工厂方法模式。
 * 所以，在使用工厂模式时，只需要关心降低耦合度的目的是否达到了。
 */
public class Main {

    public static void main(String[] args) {
        //testStaticFactory();
        //testSimpleFactory();
        //testFactoryMethod();
        testAbstractFactory();
    }

    /**
     *静态工厂方法具有三大优势——具名、环保、多子。
     *如果一个类提供了静态工厂方法，那么也就不需要考虑对这个类进行工厂模式了。
     *我们在创建类时，切忌第一反应就是提供公有构造器，要优先考虑静态工厂方法。
     *静态工厂方法最主要的特点是：
     *每次被调用的时候，不一定要创建一个新的对象。利用这一特点，静态工厂方法可用来创建以下类的实例。
     *<1> 单例类：只有惟一的实例的类。
     *<2>枚举类：实例的数量有限的类。
     *<3>具有实例缓存的类：能把已经创建的实例暂且存放在缓存中的类。
     *<4>具有实例缓存的不可变类：不可变类的实例一旦创建，其属性值就不会被改变。
     */
    public static void testStaticFactory(){
        Square square = Square.getInstance();
        square.draw();
    }

    /**
     * 简单工厂
     * 工厂类负责创建的对象比较少，客户只需要传入工厂类参数，对于如何创建对象（逻辑）不关心。
     * 简单工厂模式很容易违反高内聚低耦合的原则，因此一般只在很简单的情况下使用。
     */
    public static void testSimpleFactory(){
        SimpleShapeFactory.getShape("circle").draw();
        SimpleShapeFactory.getShape("rectangle").draw();
        SimpleShapeFactory.getShape("triangle").draw();
    }

    /**
     * 工厂方法
     * 定义一个创建对象的工厂接口，让子类决定实例化哪一个类，将实际创建工作推迟到子类当中
     * 创建对象的接口，让子类决定具体实例化的对象，把简单的内部逻辑判断移到了客户端。
     * 工厂方法模式克服了简单工厂所违背的开闭原则的缺点，又保持了封装对象创建过程的优点。
     * 扩展性高，想要增加一个产品，只要扩展一个工厂类就可以。
     */
    public static void testFactoryMethod(){
        new CircleFactory().getShape().draw();
        new RectangleFactory().getShape().draw();
        new TriangleFactory().getShape().draw();
    }

    /**
     * 抽象工厂是围绕一个超级工厂创建其他工厂，该超级工厂又称为其他工厂的工厂。
     * 提供一个创建一系列相关或相互依赖对象的接口，而无需指定他们具体的类
     * 当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
     */
    public static void testAbstractFactory(){
        CircleGroupFactory circleGroupFactory = new CircleGroupFactory();
        circleGroupFactory.createShape().draw();
        circleGroupFactory.createButton().press();
        RectangleGroupFactory rectangleGroupFactory = new RectangleGroupFactory();
        rectangleGroupFactory.createShape().draw();
        rectangleGroupFactory.createButton().press();
        TriangleGroupFactory triangleGroupFactory = new TriangleGroupFactory();
        triangleGroupFactory.createShape().draw();
        triangleGroupFactory.createButton().press();
    }
}
