package com.cjean.new_type.lombok;


public class LombokLearn {
    public static void main(String[] args) {
//        testData();//测试@Data
//        testArgsConstructor();//测试@ XX ArgsConstructor



    }
    static void testArgsConstructor(){
        //@NoArgsConstructor
        ArgsConstructor argsConstructor = new ArgsConstructor();
//        ArgsConstructor allArgsConstructor = new ArgsConstructor(2,"cjean");//报错
//        ArgsConstructor requiredArgsConstructor01 = new ArgsConstructor(2);//报错
//        ArgsConstructor requiredArgsConstructor02 = new ArgsConstructor("cjean");//报错

        //@RequiredArgsConstructor:@NonNull 可选择构造器参数  ，
        // 构造器的参数是被@NonNull注解过的，如果一个属性都没有被@NonNull 注解过，则效果等同@NoArgsConstructor
//        ArgsConstructor1 argsConstructor1 = new ArgsConstructor1();//报错，一个属性都没有被@NonNull 注解过，则不会报错
        ArgsConstructor1 allArgsConstructor1 = new ArgsConstructor1(2);//id @NonNull
//        ArgsConstructor1 requiredArgsConstructor101 = new ArgsConstructor1(2);//报错
//        ArgsConstructor1 requiredArgsConstructor102 = new ArgsConstructor1("cjean");//报错

        //@AllArgsConstructor : 构造器的参数是全部成员属性
//        ArgsConstructor2 argsConstructor2 = new ArgsConstructor2();//报错
        ArgsConstructor2 allArgsConstructor2 = new ArgsConstructor2(2,"cjean");
//        ArgsConstructor2 requiredArgsConstructor201 = new ArgsConstructor2(2);//报错
//        ArgsConstructor2 requiredArgsConstructor202 = new ArgsConstructor2("cjean");//报错
    }

    static void testData(){
        LombokData lombokData = new LombokData();
        lombokData.setId(1);
        lombokData.setName("cjean");
        System.out.println("Id: "+lombokData.getId());
        System.out.println("Name: "+lombokData.getName());
        System.out.println("Age: "+lombokData.getAge());
        System.out.println(lombokData.toString());

        System.out.println("======================");
        LombokData lombokData1 = lombokData;
        System.out.println("canEqual: "+lombokData1.canEqual(lombokData));
        System.out.println("equal: "+lombokData1.equals(lombokData));

        System.out.println("======================");
        LombokData lombokData3 = new LombokData();
        lombokData3.setId(1);
        lombokData3.setName("cjean");
        System.out.println("canEqual: "+lombokData3.canEqual(lombokData));
        System.out.println("equal: "+lombokData3.equals(lombokData));
    }
}
