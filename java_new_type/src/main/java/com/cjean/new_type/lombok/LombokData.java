package com.cjean.new_type.lombok;

import lombok.Data;

//@Data注解在类上，会为类的所有属性自动生成
//      setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
@Data
public class LombokData {
    public int id;
    private String name;
    final public int age = 18;
}
