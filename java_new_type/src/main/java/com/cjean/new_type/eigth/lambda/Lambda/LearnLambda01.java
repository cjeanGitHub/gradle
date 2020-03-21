/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LearnLambda
 * Author:   14172
 * Date:     2020/2/5 17:18
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type.eigth.lambda.Lambda;

/**
 * @author 14172
 * @create 2020/2/5
 * @since 1.0.0
 */
public class LearnLambda01 {

    public static void main(String[] args) {
        LearnLambda01 learn = new LearnLambda01();

        OperatorMath addOpera = (int a, int b)-> a + b;
        learn.operation(2, 3,addOpera);

        OperatorMath substractOpera = (a,b)->a - b;
        learn.operation(3,1,substractOpera);

        OperatorMath muiltOpera01 = (int a, int b) -> a * b;
        learn.operation(6,2,muiltOpera01);
        OperatorMath muiltOperaAndReturn =
                (int a, int b) ->
                {
                  System.out.println("muiltOperaAndReturn....");
                  return (a * b );
                };
        learn.operation(6,4,muiltOperaAndReturn);

        OperatorMath divisionOpera = (a, b)-> a/b;
        learn.operation(4,2,divisionOpera);

        IOperatorStr IOperatorStr = mes -> "你好？,"+mes;
        String res = IOperatorStr.operatorStr("世界");
        System.out.println(res);

    }

    interface OperatorMath{
        int opreatorNum(int a, int b);
    }

    interface IOperatorStr{
        String operatorStr(String mes);
    }

    private int operation(int a, int b, OperatorMath operatorMath){
        int result = operatorMath.opreatorNum(a,b);
        System.out.println("result: "+result);
        System.out.println("##########");
        System.out.println();
        return result;
    }


}
