package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class DddLibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void moduleTest() {
        ApplicationModules.of(DddLibraryApplication.class).verify();
    }


}
