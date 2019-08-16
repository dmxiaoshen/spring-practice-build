package com.xyz.designpatterns.structure.adapter;

/**
 * Created by hzhsg on 2018/4/28.
 * 对象适配，持有 src类，实现 dst 类接口，完成src->dst的适配。
 *（根据“合成复用原则”，在系统中尽量使用关联关系来替代继承关系，因此大部分结构型模式都是对象结构型模式。）
 * 所以它解决了类适配器必须继承src的局限性问题，也不再强求dst必须是接口。
 * 同样的它使用成本更低，更灵活。
 *（和装饰者模式初学时可能会弄混，这里要搞清，装饰者是对src的装饰，使用者毫无察觉到src已经被装饰了（使用者用法不变）。
 * 这里对象适配以后，使用者的用法还是变的。
 * 即，装饰者用法： setSrc->setSrc，对象适配器用法：setSrc->setAdapter.)
 */
public class VoltageAdapter2 implements V5 {

    private Voltage220V voltage220V;

    public VoltageAdapter2(Voltage220V voltage220V){
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5v() {
        int v = voltage220V.output220v();
        v = v/44;
        System.out.println("VoltageAdapter2适配后输出电压"+v+"v");
        return v;
    }
}
