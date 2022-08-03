package dev.ledesma.tests.utility;

import dev.ledesma.utils.ConnectionUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ConnectionUtilityTest {
    @Test
    void createConnection() {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()){
            System.out.format("%s=%s%n", envName, env.get(envName));
        }

        Assertions.assertNotNull(ConnectionUtility.createConnection());
    }
}
