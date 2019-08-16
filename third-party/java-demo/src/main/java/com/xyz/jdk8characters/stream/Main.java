package com.xyz.jdk8characters.stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by hzhsg on 2018/7/13.
 */
public class Main {

    private  static List<Student> studentList = Arrays.asList(
            new Student("小红","女",15,98.5),
            new Student("小明","男",13,90.5),
            new Student("冬冬","女",16,88.5),
            new Student("丁丁","男",14,98.5),
            new Student("Lucy","女",17,60)
    );

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        test9();
    }

    public static void test1(){
        List<String> list = Arrays.asList("1","a","c");
        Stream<String> a = list.stream();//集合 .stream()方法
        System.out.println(a);
        a.forEach(System.out::println);
        Stream.of(1,2,3).forEach(System.out::println);//Stream.of()静态方法
        long[] array = new long[]{3l,4l,6l};
        Arrays.stream(array).forEach(System.out::println);//Arrays.stream()获取数组流
        Stream.iterate(0,x->x+2).limit(10).forEach(System.out::println);//流迭代
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);//流创建
    }

    /**
     * 年龄大于15   小明 冬冬 Lucy
     * 只取2个 小明 冬冬
     * 跳过1个 冬冬
     * 去重，需重写hashcode和equals方法
     * 输出
     */
    public static void test2(){
        studentList.stream()
                .filter(x->x.getAge()>15)
                .limit(2)
                .skip(1)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map -- 接受Lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，
     * 该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *
     * flatmap -- 接受一个函数做为参数，将流中的每个值都转换成另一个流，然后将所有流连接成一个流
     */
    public static void test3(){
        studentList.stream().map(x->x.getName()).forEach(System.out::println);
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream().flatMap(str->{
            List<Character> characterList = new ArrayList<>();
            for(char c : str.toCharArray()){
                characterList.add(c);
            }
            return characterList.stream();
        }).forEach(System.out::println);
    }

    /**
     * sorted -- 自然排序(Comparable)
     * sorted(Comparator com) -- 定制排序(Comparator)
     */
    public static void test4(){
        List<String> list = Arrays.asList("aaa","1bbb","afk");
        list.stream().sorted().forEach(System.out::println);
        studentList.stream().sorted((x,y)->{
           if(new BigDecimal(x.getScore()).compareTo(new BigDecimal(y.getScore()))==0){
                return x.getName().compareTo(y.getName());
           }else{
               return new BigDecimal(y.getScore()).compareTo(new BigDecimal(x.getScore()));
           }
        }).forEach(System.out::println);
    }

    /**
     * Stream的终止操作
     - allMatch – 检查是否匹配所有元素
     - anyMatch – 检查是否至少匹配一个元素
     - noneMatch – 检查是否没有匹配所有元素
     - findFirst – 返回第一个元素
     - count – 返回流中元素的总个数
     - max – 返回流中最大值
     - min – 返回流中最小值
     */
    public static void test5(){
        System.out.println(studentList.stream().allMatch(x->x.getAge()>13));
        System.out.println(studentList.stream().anyMatch(x->x.getAge()>17));
        System.out.println(studentList.stream().noneMatch(x->x.getAge()>17));
        System.out.println(studentList.parallelStream().findAny().get());
        System.out.println(studentList.stream().findAny().get());
        System.out.println(studentList.stream().findFirst().get());
        System.out.println(studentList.parallelStream().findFirst().get());
        System.out.println(studentList.stream().count());
        System.out.println(studentList.stream().map(e->e.getAge()).max(Integer::compareTo).get());
        System.out.println(studentList.stream().map(e->e.getScore()).min(Double::compareTo).get());
        System.out.println(studentList.stream().min(Comparator.comparing(e->e.getScore())).get().getScore());
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     *  -- 可以将流中的元素反复结合起来，得到一个值
     */
    public static void test6(){
        List<Integer> list = new ArrayList<>();
        list = Stream.iterate(1,x->x+1).limit(100).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
        //int sum = list.stream().reduce(0,(x,y)->x+y).intValue();
        int sum = list.stream().parallel().reduce(0,(x,y)->x+y).intValue();
        System.out.println(sum);
        double x = studentList.stream().map(e->e.getScore()).reduce(Double::sum).get();
        System.out.println(x);
    }

    /**
     *  收集
     * collect
     * -- 将流转换成其他的形式，接收一个Collector接口的实现，可以通过Collectors的实用类操作
     */
    public static void test7(){
        List<String> list = studentList.stream().map(e->e.getName()).collect(Collectors.toList());
        list.forEach(System.out::println);
        Set<Integer> set = studentList.stream().map(e->e.getAge()).collect(Collectors.toSet());
        set.forEach(System.out::println);
        LinkedList<Double> linkedList = studentList.stream().map(e->e.getScore()).collect(Collectors.toCollection(LinkedList::new));
        linkedList.forEach(System.out::println);
        System.out.println(studentList.stream().collect(Collectors.counting()).longValue());//总数
        System.out.println(studentList.stream().collect(Collectors.averagingDouble(e->e.getScore())).doubleValue());//平均数
        System.out.println(studentList.stream().collect(Collectors.summingDouble(e->e.getScore())).doubleValue());//分数总和
        System.out.println(studentList.stream().collect(Collectors.maxBy(Comparator.comparing(e->e.getScore()))).get());//分数最大
        System.out.println(studentList.stream().collect(Collectors.maxBy((x,y)->new BigDecimal(x.getScore()).compareTo(new BigDecimal(y.getScore())))).get());//分数最大
    }

    public static void test8(){
        //分组
        Map<String,List<Student>> map = studentList.stream().collect(Collectors.groupingBy(e->e.getSex()));
        System.out.println(map.size());//3女2男
        //多级分组
        Map<String,Map<String,List<Student>>> multiMap = studentList.stream().collect(Collectors.groupingBy(e->e.getSex(),Collectors.groupingBy(x->{
            if(x.getAge()>15){
                return "少年";
            }else{
                return "小孩";
            }
        })));
        System.out.println(multiMap.size());
        //分区
        Map<Boolean,List<Student>> mapP = studentList.stream().collect(Collectors.partitioningBy(e->e.getAge()>15));
        System.out.println(mapP.size());
        //统计
        System.out.println(studentList.stream().collect(Collectors.summarizingDouble(e->e.getScore())).getCount());
        System.out.println(studentList.stream().collect(Collectors.summarizingDouble(e->e.getScore())).getMax());
        System.out.println(studentList.stream().collect(Collectors.summarizingDouble(e->e.getScore())).getMin());
        System.out.println(studentList.stream().collect(Collectors.summarizingDouble(e->e.getScore())).getSum());
        System.out.println(studentList.stream().collect(Collectors.summarizingDouble(e->e.getScore())).getAverage());
        //拼接字符串
        System.out.println(studentList.stream().map(e->e.getName()).collect(Collectors.joining(",","prefix-","-suffix")));
    }

    /**
     * 并行流
     */
    public static void test9(){
        System.out.println(LongStream.rangeClosed(0,1000000L).parallel().reduce(0,Long::sum));
    }
}
