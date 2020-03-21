/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Lean_Predicate
 * Author:   14172
 * Date:     2020/2/6 10:00
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type.eigth.Functional_Interface.TestFunctionMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 14172
 * @create 2020/2/6
 * @since 1.0.0
 */
public class Lean_Predicate {
    public static void main(String[] args) {
//        testEval();
        testIsEqual();
    }

    static void testIsEqual(){
        Predicate predicate = x -> x.equals("hello");
        boolean  rs1= predicate.test("hello");
        boolean  rs2= predicate.test("world");
        System.out.println("rs1: "+rs1);//true
        System.out.println("rs2: "+rs2);//false
        Predicate<Object> p1 = Predicate.isEqual("世界");
        boolean r1 = p1.test("世界");
        boolean r2 = p1.test("世界你好");
        System.out.println("r1: "+r1);//true
        System.out.println("r2: "+r2);//false

    }

    static void testEval(){
        //创建 测试案例
        List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //编写 打印全部数字判断函数
        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true
        eval(num, n->true);

        System.out.println("===========");

        //编写 打印偶数的判断函数
        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true
        eval(num, n->n%2==0);

        System.out.println("===========");

        //编写 打印奇数的判断函数
        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 不是 奇数 test 方法返回 true
        eval(num, n->n%2!=0);
    }

    //
    /**
     * 功能描述: <br>
     *     构建逻辑调用方法
     *    Predicate <T>接口: 是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果
     * @return: V
     * @since: 1.0.0
     * @Date: 2020/2/6 10:06
     */
    private static void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n:list){
            //执行判断函数：
            boolean flag = predicate.test(n);
            if (flag) System.out.println("number: "+n);
        }
    }

}
