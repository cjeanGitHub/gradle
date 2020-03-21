/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LearnBase64
 * Author:   14172
 * Date:     2020/2/6 20:08
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjean.new_type.eigth.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author 14172
 * @create 2020/2/6
 * @since 1.0.0
 */
public class LearnBase64 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //使用基本编码
        String str = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (基本) :" +str);

        //解码
        byte[] decode = Base64.getDecoder().decode(str);
        System.out.println("原始字符串: " +new String(decode));

        //使用url方式编码、解码
        String strUrl = Base64.getUrlEncoder().encodeToString("runoob?java8-url".getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (strUrl) :" +strUrl);

        byte[] decode1 = Base64.getUrlDecoder().decode(strUrl);
        System.out.println("原始字符串(strUrl): " +new String(decode1));

        //使用mime方式编码 解码
        String strMime = Base64.getMimeEncoder().encodeToString("runoob?java8-MIME".getBytes("utf-8"));
        System.out.println("Base64 编码字符串 (strMime) :" +strMime);

        byte[] decode2 = Base64.getMimeDecoder().decode(strMime);
        System.out.println("原始字符串(strMime): " +new String(decode2));

        UUID uuid = UUID.randomUUID();
        System.out.println("UUID: "+uuid.toString());
    }
}
