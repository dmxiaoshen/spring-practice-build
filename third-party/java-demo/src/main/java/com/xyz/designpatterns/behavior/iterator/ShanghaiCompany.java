package com.xyz.designpatterns.behavior.iterator;

/**
 * Created by hzhsg on 2018/5/7.
 */
public class ShanghaiCompany<T> implements Company<T>{
    private static final int MAX = 10;
    //java泛型数组很麻烦，因此强转下
    private T[] objectArray = (T[]) new Object[MAX];
    private int current = 0;

    public void addObject(T t){
        if(current<10){
            objectArray[current] = t;
            current++;
        }else{
            throw new RuntimeException("超出最大限额"+MAX);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ShanghaiIterator<T>();
    }

    /**
     * 将迭代器对象设置为聚集类的内部类，这样迭代器对象可以对聚集类有很好的访问，
     * 同时又限制了对外的操作。这种同时保证聚集对象的封装和迭代子功能的实现的方案叫做黑箱实现方案。
     * 而这种形式定义的迭代器，又被称为内部迭代器。
     */
    private class ShanghaiIterator<T> implements  Iterator<T>{

        private int pos = 0;

        @Override
        public boolean hasNext() {
            return !(pos> objectArray.length-1 || objectArray[pos]==null);
        }

        @Override
        public T next() {
           T t = (T) objectArray[pos];
           pos++;
           return t;
        }
    }
}
