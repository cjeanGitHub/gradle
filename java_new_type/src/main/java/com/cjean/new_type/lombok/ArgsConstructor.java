package com.cjean.new_type.lombok;

import lombok.*;

@NoArgsConstructor
public class ArgsConstructor {
    @NonNull
    public int id;
    private String name;
    final public int age = 18;
}
@RequiredArgsConstructor
@Data
class ArgsConstructor1 {
    @NonNull
    public int id;
    private String name;
    final public int age = 18;
}
@AllArgsConstructor
class ArgsConstructor2 {
    public int id;
    private String name;
    final public int age = 18;
}
