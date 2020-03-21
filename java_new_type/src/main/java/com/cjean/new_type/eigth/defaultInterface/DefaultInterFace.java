package com.cjean.new_type.eigth.defaultInterface;

public class DefaultInterFace {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.defaultPrint();
        System.out.println("======");
        ((Car) car).print();
    }
}
class Car implements Vehicle,
        StaticVehicle,
        FourWheeler
{
    public void print(){
        //调用 Vehicle 的默认方法
        Vehicle.super.defaultPrint();
        //调用 StaticVehicle 的静态方法
        StaticVehicle.staticPrint();
        //调用 FourWheeler 的默认方法
        FourWheeler.super.defaultFourWheelerPrint();
    }
}

//接口的默认方法
interface Vehicle{
    default void defaultPrint(){
        System.out.println("I am a Vehicle");
    }
}
//接口的静态方法
interface StaticVehicle{
    static void staticPrint(){
        System.out.println("I am a StaticVehicle");
    }
}
//接口的 默认方法
interface FourWheeler{
    default void defaultFourWheelerPrint(){
        System.out.println("I am a FourWheeler");
    }
}