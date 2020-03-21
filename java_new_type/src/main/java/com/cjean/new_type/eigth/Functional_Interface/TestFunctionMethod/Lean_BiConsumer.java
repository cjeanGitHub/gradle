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

import java.util.function.BiConsumer;

/**
 * @author 14172
 * @create 2020/2/5
 * @since 1.0.0
 */
public class Lean_BiConsumer {
    public static void main(String[] args) {
        Lean_BiConsumer test = new Lean_BiConsumer();
        test.testAccept("你好","世界");
    }

    private void testAndThen(Object o,Object o2){
        //代表了一个接受两个输入参数的操作，并且不返回任何结果
    }

    private void testAccept(Object o,Object o2){
        //代表了一个接受两个输入参数的操作，并且不返回任何结果
        BiConsumer biConsumer = new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println("123:"+o+","+o2);
            }
        };
        biConsumer.accept(o,o2);
        System.out.println("=========");
        biConsumer.andThen(biConsumer).accept(o,"andThen");

    }
}
