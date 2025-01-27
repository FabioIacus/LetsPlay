package com.letsplay.model.dao;

import com.letsplay.exception.DAOException;
import com.letsplay.exception.DatabaseException;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegistrationDAO {
    void registerRequest(Registration registration) throws SQLException, IOException, CsvException;
    List<Registration> findTourToAcceptOrDecline(User user);
    void changeStatus(Registration reservation);
    List<Registration> showResponses(User user) throws DAOException, SQLException, DatabaseException, IOException, CsvValidationException;
}
