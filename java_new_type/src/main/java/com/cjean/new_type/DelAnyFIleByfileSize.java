/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: DelAnyFIleByfileSize
 * Author:   14172
 * Date:     2020/3/21 8:31
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * @author 14172
 * @create 2020/3/21
 * @since 1.0.0
 *
 *
 * Java 判断两个文件是否相同
 *     使用Java 如何判断两个文件是否相同呢？我的做法是
 *
 * （1）先比较两个文件内容的长度；
 *
 * （2）在长度相同的情况下，再比较两个文件的MD5值。
 * <dependency>
 *             <groupId>commons-codec</groupId>
 *             <artifactId>commons-codec</artifactId>
 *             <version>1.11</version>
 *         </dependency>
 *
 *
 *         /**
 *      * 验证两个文件字节流是否相等
 *      * @return boolean true 相等
 *      * @throws IOException
 *
 *private static boolean isSameFiles(byte[]fileByte1,byte[]fileByte2){
        *String firstFileMd5=DigestUtils.md5Hex(fileByte1);
        *String secondFileMd5=DigestUtils.md5Hex(fileByte2);
        *if(firstFileMd5.equals(secondFileMd5)){
        *System.out.println("---- equals ------ md5 "+firstFileMd5);
        *return true;
        *}else{
        *System.out.println(firstFileMd5+" is firstFileMd5 ++ unequal ++ secondFileMd5 = "+secondFileMd5);
        *return false;
        *}
        *}
 */
public class DelAnyFIleByfileSize {
    //首先获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，将文件大小放入list

    private static List addFIlePathAndSizeInList(List<String> folderPaths) {
        System.out.println("获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，将文件大小放入list");
        List pathAndSizeList = new ArrayList<Object>();
        HashMap<String, String> pathAndSizHashMap = new HashMap<>();
        Set<String> sizeList = new LinkedHashSet<>();
        folderPaths.stream()
                .map(
                        folderPath -> {
                            File f = new File(folderPath);
                            File[] fs = f.listFiles();
                            if (fs == null) {
                                return null;
                            }

                            Stream<File> fileStream = Arrays.stream(fs);
                            fileStream
                                    .map(file -> {
                                        pathAndSizHashMap.put(file.getAbsolutePath(), "" + file.length());
                                        sizeList.add("" + file.length());
                                        return file;
                                    })
                                    .collect(Collectors.toList()).toArray();
                            return folderPath;
                        }
                )
                .collect(Collectors.toList());
        pathAndSizeList.add(pathAndSizHashMap);
        pathAndSizeList.add(sizeList);

        System.out.println("获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，将文件大小放入list，完成");
        return pathAndSizeList;
    }

    //传根据文件大小删除指定文件
    private static void delTheFilesByFIleSizes(Set<String> sizeList, HashMap<String, String> pathAndSizHashMap) {
        System.out.println("传根据文件大小删除指定文件");
        HashMap<String, String> needDelPathAndSizHashMap = new HashMap<>();
        sizeList.stream()
                .map(size -> {
                    int sameSizCount = 0;

                    for (Map.Entry<String, String> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            sameSizCount++;
                            if (sameSizCount > 1) needDelPathAndSizHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }

                    return size;
                })
                .collect(Collectors.toList());

        for (String delFilePth : needDelPathAndSizHashMap.keySet()) {
            File file = new File(delFilePth);

            if (file.isFile()) {
                file.delete();
            }
        }

        System.out.println("传根据文件大小删除指定文件，完成");
    }

    // 根据文件大小查询文件所在的位置并生成记录文件
    private static void findSameSizeFilePathAndCreatNote(Set<String> sizeList, HashMap<String, String> pathAndSizHashMap) {
        System.out.println("根据文件大小查询文件所在的位置并生成记录文件");
        Map<String, String> sameSizePathAndSizHashMap = new HashMap<>();
        Set<String> SamplsizeList = new LinkedHashSet<>();
        sizeList.stream()
                .map(size -> {
                    int sameSizCount = 0;

                    for (Map.Entry<String, String> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            sameSizCount++;
                            if (sameSizCount > 1) SamplsizeList.add(size);
                        }
                    }

                    return size;
                })
                .collect(Collectors.toList());

        SamplsizeList.stream()
                .map(size -> {
                    for (Map.Entry<String, String> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            sameSizePathAndSizHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }

                    return size;
                })
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

//        Map<String, String> sortedMap = new TreeMap(new Comparator<String>() {
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);  //按升序排列 ,改为按降序排列return o2.compareTo(o1);
//            }
//        });
//
//        sortedMap.putAll(sameSizePathAndSizHashMap);

        // 按key排序 升序 comparingByKey，按vale comparingByValue
//        Map<String, String> sorted = sameSizePathAndSizHashMap
//                .entrySet()
//                .stream()
//                .sorted(comparingByKey())
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));

        // 按key排序 升序 comparingByKey，按vale comparingByValue
        Map<String, String> sorted = sameSizePathAndSizHashMap
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        for (String key : sorted.keySet()) {
            StringBuilder colKey = new StringBuilder(key);
            if (70 > key.length()) {
                for (int i=0; i<(70 - key.length());i++) {
                    colKey = colKey.append(" ");
                }
            }
            String chengeKey = colKey.toString();

            stringBuilder.append(chengeKey+"--> "+sorted.get(key));
            stringBuilder.append("\n");
        }
        CreateNoteByStr(stringBuilder.toString());

        System.out.println("根据文件大小查询文件所在的位置并生成记录文件，完成");
    }

    private static void CreateNoteByStr(String noteStr) {

        System.out.println("开始写入文件！");
        String fileName = "note.txt";
        String localTipsPath = "F:\\test";
        File dectionary = new File(localTipsPath);
        if (!dectionary.exists()) {
            dectionary.mkdirs();
        }
        String filePath = localTipsPath + "\\" + fileName;
        File messageFile = new File(filePath);
        if (messageFile.exists()) {
            System.out.println("文件【" + filePath + "】已经存在！");
        }
        FileOutputStream fos = null;
        try {
            messageFile.createNewFile();
            fos = new FileOutputStream(messageFile, false);
            fos.write(noteStr.toString().getBytes());
//            fos.write(noteStr.toString().getBytes("GBK"));
            System.out.println("写入文件成功！");
        } catch (IOException e) {
            System.out.println("写入文件失败！");
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("关闭文件失败！");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        List<String> folderPaths = new ArrayList<>();
        folderPaths.add("E:\\my");
        folderPaths.add("C:\\my");
        folderPaths.add("D:\\my");
//        folderPaths.add("I:\\java达内入门\\12\\迅雷下载");
//        folderPaths.add("I:\\java达内入门\\yirenzhixia");
//        folderPaths.add("I:\\java达内入门\\一人之下2");

//        delTheFilesByFIleSizes(
//                (Set<String>) addFIlePathAndSizeInList(folderPaths).get(1)
//                , (HashMap<String, String>) addFIlePathAndSizeInList(folderPaths).get(0));

        findSameSizeFilePathAndCreatNote(
                (Set<String>) addFIlePathAndSizeInList(folderPaths).get(1)
                , (HashMap<String, String>) addFIlePathAndSizeInList(folderPaths).get(0));
    }


}
