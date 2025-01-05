package com.letsplay.model.dao;

import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;

import java.util.List;

public class RegistrationDAOFileSystem implements RegistrationDAO {
    @Override
    public int register(Registration registration) {
        return 0;
    }

    @Override
    public List<Registration> findTourToAcceptOrDecline(User user) {
        return List.of();
    }

    @Override
    public void changeStatus(Registration reservation) {

    }

    @Override
    public List<Registration> findTourStatus(User user) {
        return List.of();
    }
}
