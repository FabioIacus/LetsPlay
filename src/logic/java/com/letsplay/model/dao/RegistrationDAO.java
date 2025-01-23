package com.letsplay.model.dao;

import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface RegistrationDAO {
    void registerRequest(Registration registration) throws SQLException, FileNotFoundException;
    List<Registration> findTourToAcceptOrDecline(User user);
    void changeStatus(Registration reservation);
    List<Registration> findTourStatus(User user);
}
