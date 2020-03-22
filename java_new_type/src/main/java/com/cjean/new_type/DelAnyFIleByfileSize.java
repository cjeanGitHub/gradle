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

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * @author 14172
 * @create 2020/3/21
 * @since 1.0.0
 */
public class DelAnyFIleByfileSize {

    /***
     * @Description:
     * 获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，
     * 并且对所有文件件逐一对比字节
     * 适合2G一下的文件，超过2G的大文件需要多线程去处理
     */
    private static void findSameFileAndCreatNote(List<String> folderPaths) {
        System.out.println("获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，并且对所有文件件逐一对比字节");
        System.out.println("文件对比开始");
        List<String> paths = new ArrayList<>();
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
                                        paths.add(file.getAbsolutePath());
                                        return file;
                                    })
                                    .collect(Collectors.toList()).toArray();
                            return folderPath;
                        }
                )
                .collect(Collectors.toList());

        StringBuilder stringBuilder1 = new StringBuilder();

        for (int index = 0; index < paths.size(); index++) {
            if (index == (paths.size())) {
                break;
            }
            for (int i = (index + 1); i < paths.size(); i++) {
                if (isSameFiles(toByteArray(paths.get(index)), toByteArray(paths.get(i)))) {
                    stringBuilder1.append("文件: " + paths.get(index) + " 与文件 " + paths.get(i) + "对比， 结果：相同");
                    stringBuilder1.append("\n");
                } else {
//                    stringBuilder1.append("文件: " + paths.get(index) + " 与文件 " + paths.get(i) + "对比， 结果：不相同");
//                    stringBuilder1.append("\n");
                }
            }
        }

        CreateNoteByStr(stringBuilder1.toString(), "result");
        System.out.println("文件对比结束");
    }


    //首先获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，将文件大小放入list

    private static List addFilePathAndSizeInList(List<String> folderPaths) {
        System.out.println("获取文件的位置，并将文件中的文件位置全路径和文件大小放入map，将文件大小放入list");
        List pathAndSizeList = new ArrayList<Object>();
        HashMap<String, Long> pathAndSizHashMap = new HashMap<>();
        Set<Long> sizeList = new LinkedHashSet<>();
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
                                        pathAndSizHashMap.put(file.getAbsolutePath(), file.length());
                                        sizeList.add(file.length());
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
    private static void delTheFilesByFIleSizes(Set<Long> sizeList, HashMap<String, Long> pathAndSizHashMap) {
        System.out.println("传根据文件大小删除指定文件");
        HashMap<String, Long> needDelPathAndSizHashMap = new HashMap<>();
        sizeList.stream()
                .map(size -> {
                    int sameSizCount = 0;

                    for (Map.Entry<String, Long> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            sameSizCount++;
                            if (sameSizCount > 1) {
                                needDelPathAndSizHashMap.put(entry.getKey(), entry.getValue());
                            }
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
    private static void findSameSizeFilePathAndCreatNote(Set<Long> sizeList, HashMap<String, Long> pathAndSizHashMap) {
        System.out.println("根据文件大小查询文件所在的位置并生成记录文件");
        Map<String, Long> sameSizePathAndSizHashMap = new HashMap<>();
        Set<Long> SamplsizeList = new LinkedHashSet<>();
        sizeList.stream()
                .map(size -> {
                    int sameSizCount = 0;

                    for (Map.Entry<String, Long> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            sameSizCount++;
                            if (sameSizCount > 1) {
                                SamplsizeList.add(size);
                            }
                        }
                    }

                    return size;
                })
                .collect(Collectors.toList());

        HashMap<Long, List<String>> sizePathsMap = new HashMap<>();

        SamplsizeList.stream()
                .map(size -> {
                    ArrayList<String> paths = new ArrayList<>();

                    for (Map.Entry<String, Long> entry : pathAndSizHashMap.entrySet()) {
                        if (size.equals(entry.getValue())) {
                            paths.add(entry.getKey());
                            sameSizePathAndSizHashMap.put(entry.getKey(), entry.getValue());

                        }
                    }
                    sizePathsMap.put(size, paths);

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

        // 文件大小的文件位置集合，根据md5判断文件的内容是不是一样
        //获取每个大小相同的文件路径，放入一个list中，根据下标使用冒泡排序的模式对比每个大小相同的文件内容是不是一样
//        sameSizePathAndSizHashMap


        // 按key排序 升序 comparingByKey，按vale comparingByValue
        Map<String, Long> sorted = sameSizePathAndSizHashMap
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        for (String key : sorted.keySet()) {
            StringBuilder colKey = new StringBuilder(key);
            if (70 > key.length()) {
                for (int i = 0; i < (70 - key.length()); i++) {
                    colKey = colKey.append(" ");
                }
            }
            String chengeKey = colKey.toString();

            stringBuilder.append(chengeKey + "--> " + sorted.get(key));
            stringBuilder.append("\n");
        }
        CreateNoteByStr(stringBuilder.toString(), "note");

        System.out.println("根据文件大小查询文件所在的位置并生成记录文件，完成");
    }

    private static void CreateNoteByStr(String noteStr, String resultFileName) {

        System.out.println("开始写入文件！");
        String fileName = resultFileName + ".txt";
        String localTipsPath = "D:\\createFolder\\test";
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

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 验证两个文件字节流是否相等
     *
     * @return boolean true 相等
     * @throws IOException
     */
    private static boolean isSameFiles(byte[] fileByte1, byte[] fileByte2) {
        String firstFileMd5 = DigestUtils.md5Hex(fileByte1);
        String secondFileMd5 = DigestUtils.md5Hex(fileByte2);
        if (firstFileMd5.equals(secondFileMd5)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> folderPaths = new ArrayList<>();
//        folderPaths.add("D:\\my1");
//        folderPaths.add("D:\\my2");
//        folderPaths.add("D:\\my3");
        folderPaths.add("F:\\java达内入门\\12\\迅雷下载");
        folderPaths.add("F:\\java达内入门\\yirenzhixia");
        folderPaths.add("F:\\java达内入门\\一人之下2");

//        delTheFilesByFIleSizes(
//                (Set<String>) addFilePathAndSizeInList(folderPaths).get(1)
//                , (HashMap<String, String>) addFilePathAndSizeInList(folderPaths).get(0));

        findSameSizeFilePathAndCreatNote(
                (Set<Long>) addFilePathAndSizeInList(folderPaths).get(1)
                , (HashMap<String, Long>) addFilePathAndSizeInList(folderPaths).get(0));

//        findSameFileAndCreatNote(folderPaths);//适合2G一下的文件，超过2G的大文件需要多线程去处理
    }


}
