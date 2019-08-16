package com.xyz.jdk8characters.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 通过JDK8源码javadoc，可以知道这个注解有以下特点：
 1、该注解只能标记在"有且仅有一个抽象方法"的接口上。
 2、JDK8接口中的静态方法和默认方法，都不算是抽象方法。
 3、接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
 4、该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。
   加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，
   但是加上了@FunctionInterface，那么编译器会报错。
 * Created by hzhsg on 2018/7/13.
 */
public class Main {

    private static List<Employee> employeeList = Arrays.asList(
            new Employee("张三","北京",4800,26),
            new Employee("李四","上海",5000,22),
            new Employee("王五","纽约",8000,22),
            new Employee("赵六","长沙",3000,25),
            new Employee("Lucas","卢森堡",12000,21));

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    /**
     * 格式1,无参数,无返回值
     * 格式2，有一个参数，并且无返回值
     * 格式3，若只有一个参数，小括号可以省略不写
     * 格式4，有两个以上的参数，有返回值，并且Lambda体中有多条语句
     * 格式5，若Lambda体中只有一条语句，return和大括号都可以省略不写
     * 格式6，Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文进行类型推断出数据类型，既“类型推断”。
     */
    public static void test1() {
        Runnable x = new Runnable() {
            @Override
            public void run() {
                System.out.println("run-x");
            }
        };

        Runnable y = () -> System.out.println("run-y");

        x.run();

        y.run();
    }

    /**
     * 调用COllections.sort方法，通过定制排序比较两个Employee（先按年龄比较，年龄相同按姓名比）
     */
    public static void test2(){
        employeeList.forEach(System.out::println);
        System.out.println("=====================");
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee x, Employee y) {
                return Double.compare(x.getSalary(),y.getSalary());
            }
        });
        employeeList.forEach(System.out::println);
        System.out.println("=====================");
        Collections.sort(employeeList,(x,y)->{
            if(x.getAge()!=y.getAge()){
                return Integer.compare(x.getAge(),y.getAge());
            }else{
                return x.getName().compareTo(y.getName());
            }
        });
        employeeList.forEach(System.out::println);
    }

    /**
     * 声明函数式接口，接口中声明抽象方法，public String getvalue(String str();
     * 编写方法使用接口作为参数，讲一个字符串转换成大写，并作为方法的返回值。
     */
    @FunctionalInterface
    public interface MyFunction{
        String getValue(String str);
    }
    public String getValue(String str,MyFunction function){
        return function.getValue(str);
    }
    public String getValue2(String str, Function<String,String> fun){
        return fun.apply(str);
    }
    public static void test3(){
        System.out.println(new Main().getValue("hello lambda",x->x.toUpperCase()));
        System.out.println(new Main().getValue2("world lambda",x->x.toUpperCase()));
    }

    /**
     * 声明一个带两个泛型的函数式接口，泛型类型是<T,R>,T为参数，R为返回值。
     * 接口中声明对应抽象方法
     * 声明方法，使用接口作为参数，计算两个long型参数的和
     * 在计算两个long型参数的乘积
     */
    @FunctionalInterface
    public interface MyFun<T,R>{
        R method(T t1, T t2);
    }
    public <T,R> R call(T t1,T t2,MyFun<T,R> myFun){
        return myFun.method(t1,t2);
    }
    public static void test4(){
        long x = new Main().call(1000l, 3000l, (t1,t2)-> t1*t2);
        long y = new Main().call(1000l, 3000l, (t1,t2)-> t1-t2);
        System.out.println(x);
        System.out.println(y);
    }

    /**
     * Java8中的四大核心函数式接口
     *
     Consumer<T> :消费型接口
     void accept(T t);

     Supplier<T> :供给型接口
     T get();

     Function<T,R> :函数型接口
     R apply(T t);

     Predicate<T> :断言型接口
     boolean test(T t);
     */

    public <T> void consumer(T t, Consumer<T> consumer){
        //有参数，无返回值
        consumer.accept(t);
    }
    public <T> T supplier(Supplier<T> supplier){
        //无参数，有返回值
        return supplier.get();
    }

    public <T> boolean predicate(T t, Predicate<T> predicate){
        //有参数，有返回值boolean
        return predicate.test(t);
    }

    public <T,R> R function(T t,Function<T,R> function){
        //有参数，有返回值
        return function.apply(t);
    }
    public static void test5(){
        new Main().consumer("sk336",x-> System.out.println(x));
        String str = new Main().supplier(()->"lulyu");
        System.out.println(str);
        boolean b = new Main().predicate("Skj",x->x.length()>4);
        System.out.println(b);
        int h = new Main().function(-6,y->Math.abs(y));
        int h2 = new Main().function(-9,Math::abs);
        System.out.println(h);
        System.out.println(h2);
    }
}
