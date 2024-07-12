package com.gsh.springbootquick.common.generation;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author GSH
 * @create 2023/3/9 9:02
 * 代表生成的实体类中的一个字段
 */
@Data
@AllArgsConstructor
public class GseinField {
    private String name;
    private GseinFieldType type;
    private String comment;

}
