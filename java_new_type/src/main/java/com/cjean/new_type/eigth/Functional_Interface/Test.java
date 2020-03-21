/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Test
 * Author:   14172
 * Date:     2020/2/5 17:59
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type.eigth.Functional_Interface;

import java.util.function.Function;

/**
 * @author 14172
 * @create 2020/2/5
 * @since 1.0.0
 */
public class Test {
    public static void main(String[] args) {
//        testGreetService();//你好，世界
//        testFunctionApply();// 4
//        testFunctionCompsoe();// 12
//        testFunctionAndThen();// 10

//        testFunctionIdentity();//6



    }

    // 返回当前的使用方法
    static void testFunctionIdentity(){
        Function<Integer,Integer> function = x -> x * 2;
        Function<Integer,Integer> function1 = x -> x + 2;

        // 入参是 4 ，执行 apply 4 + 2 ，结果：6
        Integer res = (Integer) Function.identity().compose(function1).apply(4);
        System.out.println("res: "+res);
        System.out.println("============");
//andThen 入参会报错，由于andThen先执行本函数，但是本函数是一个空方法体的，所以会报错
//        Integer res1 = (Integer) Function.identity().andThen(function).apply(4);
//        System.out.println("res1: "+res1);
    }
    // 和compose相反
    static void testFunctionAndThen(){
        Function<Integer,Integer> function = x -> x * 2;
        Function<Integer,Integer> function1 = x -> x + 2;

// 入参是 4 ，先执行调用函数： function.apply：4 * 2 = 8，再执行入参函数： function1.apply 8 + 2 ，结果：10
        Integer res = function.andThen(function1).apply(4);
        System.out.println("res: "+res);
    }
    static void testFunctionCompsoe(){
        Function<Integer,Integer> function = x -> x * 2;
        Function<Integer,Integer> function1 = x -> x + 2;
// 入参是 4 ，先执行入参函数： function1.apply：4 + 2 = 6，再执行调用函数： function.apply 6 * 2 ，结果：12
        Integer res = function.compose(function1).apply(4);
        System.out.println("res: "+res);
    }

    static void testFunctionApply(){
        Function<Integer,Integer> opera = x -> x *2;
        Integer res = opera.apply(2);
        System.out.println("res: "+res);
    }

    static void testGreetService(){
        GreetService greetService = mes ->"你好，"+mes;
        String resp = greetService.sayMessage("世界");
        System.out.println("resp: "+resp);
    }
}
