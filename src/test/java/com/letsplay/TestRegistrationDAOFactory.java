package com.letsplay;

import com.letsplay.model.dao.RegistrationDAO;
import com.letsplay.model.dao.RegistrationDAOFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class TestRegistrationDAOFactory {
    //verifica che la factory crei un'istanza di RegistrationDAO senza generare eccezioni
    @Test
    public void testCreateReservationDAO() {
        try {
            RegistrationDAOFactory factory = new RegistrationDAOFactory();
            RegistrationDAO registrationDAO = factory.createRegistrationDAO();
            assertInstanceOf(RegistrationDAO.class, registrationDAO, "The created object is not an instance of RegistrationDAO!");
        } catch (IOException e) {
            fail("Exception caught: " + e.getMessage() + "!");
        }
    }
}
