package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("integration")
class DddLibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void moduleTest() {
        ApplicationModules.of(DddLibraryApplication.class).verify();
    }


}
