package com.letsplay.model.dao;

import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.RequestStatus;
import com.letsplay.model.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mysql.cj.Session;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class RegistrationDAOFileSystem implements RegistrationDAO {
    private static final String FILE_NAME = "DBFile.csv";
    private File fd;
    private static final int INDEX_CUSTOMER_EMAIL = 1;
    private static final int INDEX_TEAM = 2;
    private static final int INDEX_NUM_PLAYERS = 3;
    private static final int INDEX_CAPTAIN = 4;
    private static final int INDEX_MANAGER_EMAIL = 5;
    private static final int INDEX_STATUS = 6;
    private static final int INDEX_MESSAGE = 7;
    private static final int INDEX_TOURNAMENT = 8;

    public RegistrationDAOFileSystem() throws IOException {
        this.fd = new File(FILE_NAME);

        if(!fd.exists()){
            throw new IOException("File: " + FILE_NAME + " does not exists");
        }
    }

    @Override
    public void registerRequest(Registration registration) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new BufferedReader(new FileReader(fd)));
        try {
            List<String[]> csvBody = reader.readAll();
            for(int i=0; i<csvBody.size(); i++){
                String[] strArray = csvBody.get(i);
                if (    strArray[INDEX_CUSTOMER_EMAIL].equalsIgnoreCase(registration.getCustomerEmail())
                        && strArray[INDEX_TEAM].equalsIgnoreCase(registration.getTeam())
                        && strArray[INDEX_NUM_PLAYERS].equalsIgnoreCase(String.valueOf(registration.getNumPlayers()))
                        && strArray[INDEX_CAPTAIN].equalsIgnoreCase(registration.getCaptain())
                        && strArray[INDEX_MANAGER_EMAIL].equalsIgnoreCase(registration.getManagerEmail())
                        && strArray[INDEX_TOURNAMENT].equalsIgnoreCase(registration.getTournament())
                    ) {
                    reader.close();
                }
            }
        }
        finally {
            reader.close();
        }

        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));
        try {
            String[] registrationRecord = new String[9];
            registrationRecord[INDEX_CUSTOMER_EMAIL] = registration.getCustomerEmail();
            registrationRecord[INDEX_TEAM] = registration.getTeam();
            registrationRecord[INDEX_NUM_PLAYERS] = String.valueOf(registration.getNumPlayers());
            registrationRecord[INDEX_CAPTAIN] = registration.getCaptain();
            registrationRecord[INDEX_MANAGER_EMAIL] = registration.getManagerEmail();
            registrationRecord[INDEX_STATUS] = "Pending";
            registrationRecord[INDEX_TOURNAMENT] = registration.getTournament();
            csvWriter.writeNext(registrationRecord);
            csvWriter.flush();
        }
        finally {
            csvWriter.close();
        }
    }

    @Override
    public List<Registration> findTourToAcceptOrDecline(User user) {
        return List.of();
    }

    @Override
    public void changeStatus(Registration reservation) {

    }

    @Override
    public List<Registration> showResponses(User user) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        try {
            String[] registrationRecord;
            List<Registration> registrationList = new ArrayList<>();

            while ((registrationRecord = csvReader.readNext()) != null) {
                String customerEmail = registrationRecord[INDEX_CUSTOMER_EMAIL];
                if (Objects.equals(customerEmail, SessionManager.getInstance().getCurrentUser().getEmail())) {
                    String team = registrationRecord[INDEX_TEAM];
                    int numPlayers = Integer.parseInt(registrationRecord[INDEX_NUM_PLAYERS]);
                    String captain = registrationRecord[INDEX_CAPTAIN];
                    String managerEmail = registrationRecord[INDEX_MANAGER_EMAIL];
                    RequestStatus status = RequestStatus.valueOf(registrationRecord[INDEX_STATUS].toUpperCase());
                    String message = registrationRecord[INDEX_MESSAGE];
                    String tournament = registrationRecord[INDEX_TOURNAMENT];

                    Registration registration = new Registration(
                            customerEmail,
                            team,
                            numPlayers,
                            captain,
                            managerEmail,
                            status,
                            message,
                            tournament
                    );

                    registrationList.add(registration);
                }
            }
            return registrationList;
        } finally {
            csvReader.close();
        }
    }
}
