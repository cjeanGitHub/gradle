package com.cjean.new_type.eigth.optonal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 Optional 类的引入很好的解决空指针异常。
 */

public class LearnOptional {

    public static void main(String[] args) {

//        testSumOptionalNum();
//        testFlatMap();



    }
    //经过flatmap方法后在第二个管道中把把上述多个列表的数据合并成一个列表数据
    static void testFlatMap(){
        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);
// Let's print all players before Java 8
        List<String> allPlayer = new ArrayList();

        for (List<String> list: playersInWorldCup2016 )
        {
            for (String name:list)
            {
                allPlayer.add(name);
            }
        }

        System.out.println("jdk8之前的结果：");
        System.out.println(allPlayer);
        System.out.println("+++++++++++++++++++");
// Now let's do this in Java 8 using FlatMap
        allPlayer = playersInWorldCup2016
                .stream()
                .flatMap(
                        plist->plist.stream()
                )
                .collect(Collectors.toList());
        allPlayer.forEach(s -> {
            System.out.print(s+",");
        });



    }

    static void testSumOptionalNum(){
        Integer a = null;
        Integer b = 1;

        Optional<Integer> c = Optional.empty();
        Optional<Integer> d = Optional.ofNullable(a);
        Optional<Integer> e = Optional.of(b);
//        Optional<Integer> f = Optional.of(a);//java.lang.NullPointerException

        int rs1 = sumOptionalNum(c, d);
        System.out.println("rs1: "+rs1);//20+10=30
        int rs2 = sumOptionalNum(c, e);
        System.out.println("rs2: "+rs2);//20+1=21
        int rs3 = sumOptionalNum(d, e);
        System.out.println("rs3: "+rs3);//20+1=21
    }

    static int sumOptionalNum(Optional<Integer> a, Optional<Integer> b){
        System.out.println("第一个参数是否存在： "+a.isPresent());
        System.out.println("第二个参数是否存在："+b.isPresent());
        Integer a1 = a.orElse(20);
        Integer b1 = b.orElse(10);
        System.out.println("b1-1:"+b1);
        if (b.isPresent()){
            b1 = b.get();
        }
        System.out.println("b1-2:"+b1);
        return (a1+b1);
    }
}

