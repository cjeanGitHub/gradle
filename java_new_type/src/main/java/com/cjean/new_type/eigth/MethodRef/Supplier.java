package com.cjean.new_type.eigth.MethodRef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
 
class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

class TestList{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        list.add("baidu");
        list.add("Tengxun");
        list.add("xunlei");
        list.add("google");
        list.forEach(System.out::println);

        System.out.println("===========");

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}

class Test{
    public static void main(String[] args) {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        cars.forEach(Car::repair);
        cars.forEach(car::follow);
    }
}