package com.letsplay.model.dao;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.model.domain.User;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;


public class RegistrationDAOFactory {
    public RegistrationDAO createRegistrationDAO() throws IOException {
        try (InputStream input = new FileInputStream("src/main/java/com/letsplay/properties/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String reservationDaoType = properties.getProperty("REGISTRATION_DAO_TYPE");

            return switch (reservationDaoType) {
                case "JDBC" -> new RegistrationDAOJDBC();
                case "CSV" -> new RegistrationDAOFileSystem();
                default -> throw new IOException("Configuration file error");
            };
        }
    }
}
