package com.letsplay.model.dao;

import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.User;

import java.util.List;

public interface RegistrationDAO {
    int register(Registration registration);
    List<Registration> findTourToAcceptOrDecline(User user);
    void changeStatus(Registration reservation);
    List<Registration> findTourStatus(User user);
}
