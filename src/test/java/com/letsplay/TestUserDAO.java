package com.letsplay;

import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.EmailException;
import com.letsplay.exception.UsernameException;
import com.letsplay.session.ConnectionFactory;
import com.letsplay.model.dao.UserDAO;
import com.letsplay.model.domain.Role;
import com.letsplay.model.domain.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestUserDAO {
    //verifica la corretta creazione di un utente
    @Test
    public void testSignUp() {
        int result;
        User user = new User("johnny","johnny@libero.it","mypass","John", "Doe", Role.CUSTOMER);
        try {
            result = new UserDAO().signUp(user);
            assertEquals(0,result);
        } catch (SQLException e) {
            fail("Unexpected SQLException: " + e.getMessage() + "!");
        } catch (EmailException e) {
            fail("Email already exists!");
        } catch (DatabaseException e) {
            fail("Database error!");
        } catch (UsernameException e) {
            fail("Username already exists!");
        }
    }

    //viene eseguito dopo l'inserimento in tabella, in modo da eliminare l'utente
    @AfterAll
    public static void removeUser() {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM user WHERE email = 'johnny@libero.it'";
            stmt.executeUpdate(sql);
        } catch (SQLException ignore){}
    }
}
