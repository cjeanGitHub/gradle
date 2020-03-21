/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Lean_BiConsumer
 * Author:   14172
 * Date:     2020/2/5 18:14
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type.eigth.Functional_Interface.TestFunctionMethod;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author 14172
 * @create 2020/2/5
 * @since 1.0.0
 */
public class Lean_BiFunction {
    public static void main(String[] args) {
        Lean_BiFunction test = new Lean_BiFunction();
        test.testAccept("你好","世界");
    }

    private void testAccept(Object o,Object o2){
        //代表了一个接受两个输入参数的操作，并且不返回任何结果
        BiFunction biFunction = new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                return o+":"+o2;
            }
        };
        Object apply = biFunction.apply(o, o2);
        System.out.println("====="+apply+"====");
        System.out.println("=========");

        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("ob:"+o);
            }
        };
    }
}
