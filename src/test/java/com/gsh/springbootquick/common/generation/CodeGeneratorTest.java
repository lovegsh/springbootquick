package com.gsh.springbootquick.common.generation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CodeGeneratorTest {

    public static final String BASE_PATH = "com/gsh/springbootquick/system";
    @Resource
    private CodeGenerator codeGenerator;

    @Test
    void generateEntity() {
        String path = "src/main/java/com/gsh/springbootquick/system/entity";
        String entityPackage = "com.gsh.springbootquick.system.entity";
        codeGenerator.generateEntity(path, entityPackage, "system_user", "User");
    }

    @Test
    void generatePermission() {
        codeGenerator.generate(BASE_PATH, "system_permission", "Permission");
    }

    @Test
    void generateRole() {
        codeGenerator.generate(BASE_PATH, "system_role", "Role");
    }
}
