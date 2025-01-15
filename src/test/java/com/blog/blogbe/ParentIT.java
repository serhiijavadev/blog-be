package com.blog.blogbe;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {FixedClockConfiguration.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ImportTestcontainers(RuntimeDependencies.class)
public abstract class ParentIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected EntityManager entityManager;

    protected final String JSON_DATA_PREFIX_PATH = "datasets/json/%s/";

    protected String getJsonDataPrefixPath() {
        return String.format(JSON_DATA_PREFIX_PATH, this.getClass().getSimpleName());
    }

    protected String readFromFile(final String path) throws IOException {
        return Files.readString(Paths.get("src/test/resources/" + path));
    }
}

