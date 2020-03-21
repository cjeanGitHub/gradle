package com.cjean.new_type.eigth.stream;

import javax.jws.Oneway;
import java.util.*;
import java.util.stream.Collectors;

public class LearnStream {
    static class Apple{
        private String name;
        private int age;
        private boolean isbad;

        public Apple(String name, int age, boolean isbad) {
            this.name = name;
            this.age = age;
            this.isbad = isbad;
        }
    }
    public static void main(String[] args) {

        testFilter();
//        testForEach();
//        testMap();
//        testLimit();
//        testSorted();
//        testParalleStream();
//        compareDefaultStreamAndParallerStream();
//        testCollectors();
//        testSummaryStatistics();


//        String f = "true";
//        Object f1 = false;
//
//        boolean ff =  Boolean.valueOf(f);
//        boolean ff1 = (boolean)f1;
//        System.out.println("ff: "+ff);
//        System.out.println("ff1: "+ff1);
//        Object o1 = 0;
//
//        System.out.println(0==((int) o1));


    }

    //些产生统计结果的收集器也非常有用。
    // 它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果
    static void testSummaryStatistics(){
        List<Integer> numbers = Arrays.asList(
                3, 2, 7, 5
        );
        IntSummaryStatistics intSummaryStatistics = numbers
                .stream()
                .mapToInt(
                        x -> x
                )
                .summaryStatistics();

        double average = intSummaryStatistics.getAverage();
        System.out.println("average: "+average);
        long count = intSummaryStatistics.getCount();
        System.out.println("count: "+count);
        int max = intSummaryStatistics.getMax();
        System.out.println("max: "+max);
        int min = intSummaryStatistics.getMin();
        System.out.println("min: "+min);
        long sum = intSummaryStatistics.getSum();
        System.out.println("sum: "+sum);
        String s = intSummaryStatistics.toString();
        System.out.println("s: "+s);
        System.out.println("**********");
        intSummaryStatistics.accept(222);
        s = intSummaryStatistics.toString();
        System.out.println("s.accept: "+s);


    }

//Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
    static void testCollectors(){
        List<String> ints = Arrays.asList(
                "abc", "", "bc", "efg", "abcd","", "jkl"
        );
        System.out.println("打印大于200的数字");
        ints
                .stream()
                .filter(
                        string -> !string.isEmpty()
                )
                .forEach(System.out::println);
        System.out.println("*********");
        ints.forEach(System.out::println);
        System.out.println("将list转换为字符串");
        String collect = ints
                .stream()
                .collect(Collectors.joining(",")).toString();
        System.out.println("将list转换为字符串: "+collect);


    }

    //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
    static void testParalleStream(){
        List<String> list = Arrays.asList(
                "abc", "", "bc", "efg", "abcd","", "jkl"
        );
        List<String> collect = list
                .parallelStream()
                .filter(
                        s ->
                        {
                            return !(s.isEmpty());
                        }
                )
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
    static void testSorted(){
        List<Integer> integers = Arrays.asList(3, 2, 7, 22, 7, 121, 5);
        // sorted()：默认是正序排列
        System.out.println("====sorted():默认是正序排列=====");
        List<Integer> collectDefaluteSoted = integers.stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        collectDefaluteSoted.forEach(System.out::println);
        System.out.println("====Comparator.reverseOrder()=====");
        //Comparator.reverseOrder() 倒序
        List<Integer> collectSorted = integers.stream()
                .sorted(
                        Comparator.reverseOrder()
                )
                .collect(Collectors.toList());
        collectSorted.forEach(System.out::println);
        System.out.println("====Comparator.comparing=====");
        integers
                .stream()
                .sorted(
                        Comparator.comparing(Integer::intValue)
                )
                .forEach(System.out::println);
        System.out.println("====Comparator.comparing.reversed()=====");
        integers
                .stream()
                .sorted(
                        Comparator.comparing(Integer::intValue).reversed()
                )
                .forEach(System.out::println);



    }



    //limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
    static void testLimit(){
        List<Integer> integers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> operaIntegers = integers.stream()
                .limit(3)
                .map(
                       i->{
                           int square = i * i;
                           System.out.println("square: "+square);
                           return square;
                       }
                )
                .collect(Collectors.toList());

        System.out.println("=====打印integers operaIntegers↓=====");
        integers.forEach(System.out::println);
        System.out.println("==========");
        operaIntegers.forEach(System.out::println);
        System.out.println("====打印integers operaIntegers ↑======");

    }


    static void testMap2(){

        Apple apple1 = new Apple("1111", 111, true);
        Apple apple2 = new Apple("222", 22, false);
        Apple apple3 = new Apple("33", 33, true);


        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        map1.put("map1","map1");
        map1.put("map11",0);
        map1.put("map111","map111");
        map2.put("map1","map1");
        map2.put("map11",0);
        map2.put("map111","map111");
        map3.put("map1","map1");
        map3.put("map11",1);
        map3.put("map111","map111");

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.stream().forEach(System.out::println);
        System.out.println("==========");
        list.stream().map(mapS->{
            System.out.println(mapS.get("map11"));
//            mapS.put("map11",(Integer.parseInt(Objects.toString(mapS.get("map11"))) == 0?2:4));
            mapS.put("map11",((int)mapS.get("map11") == 0?2:4));

            return mapS;
        }).forEach(System.out::println);
    }

    //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
    static void testMap(){
        List<Integer> integers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //在map中进行逻辑处理
        List<Integer> collect = integers.stream()
                .map(
                        i -> {
                            int square = i*i;
                            System.out.println("square: "+square);
                            return square;
                        }
                )
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
        //增加filter
        List<Integer> collectAndFIlter = integers.stream()
                .map(
                        i -> {
                            int square = i*i;
                            System.out.println("square: "+square);
                            return square;
                        }
                )
                .filter(i->i%2==0).collect(Collectors.toList());

        collectAndFIlter.forEach(System.out::println);

    }

    // 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数
    static void testForEach(){
        Random random = new Random();
        random.ints()
                .limit(10)
                .forEach(System.out::println);
    }

    //filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
    static void testFilter(){
        List<String> strList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //利用 filter （条件判断） 获取非空集合
        List<String> stringsNoEmpty =
                strList.stream().filter(s -> !(s.isEmpty())).collect(Collectors.toList());

        stringsNoEmpty.forEach(System.out::println);

        // 获取非空字符串的数量
        long countNoEmpty = strList
                .stream()
                .filter(
                        s -> {
                            System.out.println("s: "+s+",isEmpty: "+s.isEmpty());
                            return !(s.isEmpty());
                        }
                )
                .count();
        System.out.println("非空字符的数量: "+countNoEmpty);
        //获取空字符的数量
        long countEmpty =
                strList
                        .stream()
                        .filter(
                                s ->
                                {
                                    return s.isEmpty();
                                }
                        )
                        .count();
        System.out.println("空字符的数量: "+countEmpty);

    }

    /**
     串行流执行，当前时间：1580962964107
     串行流执行完毕，当前时间：1580962964223
     116：串行流执行耗时
     ============
     并行流执行，当前时间：1580962964223
     并行流执行完毕，当前时间：1580962964265
     42：并行流执行耗时

     *****************
     字符串少的时候
     *****************
     串行流执行，当前时间：1580963607958
     串行流执行完毕，当前时间：1580963607959
     1：串行流执行耗时
     ============
     并行流执行，当前时间：1580963607961
     并行流执行完毕，当前时间：1580963607964
     3：并行流执行耗时

     */
    //对比串行和并行流的时间区别：并行流更快(数据多)，数据少时，
    // 并行反而慢（一般情况下），由于并行需要拆分任务
    static void compareDefaultStreamAndParallerStream(){
        List<String> list = Arrays.asList(
                "abc", "", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
                , "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl", "bc", "efg", "abcd","", "jkl"
        );
        long begin=new Date().getTime();
        System.out.println("串行流执行，当前时间："+begin );
        list
                .stream()
                .filter(
                        s -> {
                            return !(s.isEmpty());
                        }
                )
                .collect(Collectors.toList());
        long end=new Date().getTime();
        System.out.println("串行流执行完毕，当前时间："+end );
        System.out.println(end-begin+"：串行流执行耗时");
        System.out.println("============");
        begin=new Date().getTime();
        System.out.println("并行流执行，当前时间："+begin );
        list
                .parallelStream()
                .filter(
                        s -> {
                            return !(s.isEmpty());
                        }
                )
                .collect(Collectors.toList());
        end=new Date().getTime();
        System.out.println("并行流执行完毕，当前时间："+end );
        System.out.println(end-begin+"：并行流执行耗时");

        System.out.println("*****************");
        System.out.println("字符串少的时候");
        System.out.println("*****************");

        List<String> list2 = Arrays.asList(
                "abc", "", "bc", "efg", "abcd","", "jkl", "bc");
        begin=new Date().getTime();
        System.out.println("串行流执行，当前时间："+begin );
        list2
                .stream()
                .filter(
                        s -> {
                            return !(s.isEmpty());
                        }
                )
                .collect(Collectors.toList());
        end=new Date().getTime();
        System.out.println("串行流执行完毕，当前时间："+end );
        System.out.println(end-begin+"：串行流执行耗时");
        System.out.println("============");
        begin=new Date().getTime();
        System.out.println("并行流执行，当前时间："+begin );
        list2
                .parallelStream()
                .filter(
                        s -> {
                            return !(s.isEmpty());
                        }
                )
                .collect(Collectors.toList());
        end=new Date().getTime();
        System.out.println("并行流执行完毕，当前时间："+end );
        System.out.println(end-begin+"：并行流执行耗时");
    }
}
