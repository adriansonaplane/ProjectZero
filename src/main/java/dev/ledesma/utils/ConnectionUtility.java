package dev.ledesma.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    static Logger logger = Logger.getLogger(ConnectionUtility.class.getName());

    public static Connection createConnection() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://ledesma-sql-server.postgres.database.azure.com:5432/projectzero?user=adrianledesma&password=uvSaPZ6K8QdXByX&ssl=false");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.fatal("Connection could not be Established!", e);
            return null;
        }


    }
}
