package com.letsplay.model.dao;

import com.letsplay.exception.EmailException;
import com.letsplay.model.domain.Registration;
import com.letsplay.model.domain.RequestStatus;
import com.letsplay.model.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.letsplay.session.SessionManager;
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
    public void registerRequest(Registration registration) throws IOException, EmailException, CsvException {
        CSVReader reader = new CSVReader(new BufferedReader(new FileReader(fd)));
        try {
            //controllo se già esiste una riga nel file csv con gli stessi dati
            List<String[]> csvBody = reader.readAll();
            for (String[] strArray : csvBody) {
                if (strArray[INDEX_CUSTOMER_EMAIL].equalsIgnoreCase(registration.getCustomerEmail())
                        && strArray[INDEX_TOURNAMENT].equalsIgnoreCase(registration.getTournament())
                        && (strArray[INDEX_STATUS].equalsIgnoreCase("pending")
                        || strArray[INDEX_STATUS].equalsIgnoreCase("accepted"))) {
                    reader.close();
                    throw new EmailException("You have already sent a request for this tournament!");
                }
            }
        } finally {
            reader.close();
        }

        //se il controllo è andato bene, posso inserire la nuova riga
        CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));
        try {
            String[] registrationRecord = new String[9];
            registrationRecord[INDEX_CUSTOMER_EMAIL] = registration.getCustomerEmail();
            registrationRecord[INDEX_TEAM] = registration.getTeam();
            registrationRecord[INDEX_NUM_PLAYERS] = String.valueOf(registration.getNumPlayers());
            registrationRecord[INDEX_CAPTAIN] = registration.getCaptain();
            registrationRecord[INDEX_MANAGER_EMAIL] = registration.getManagerEmail();
            registrationRecord[INDEX_STATUS] = "Pending";
            registrationRecord[INDEX_MESSAGE] = "";
            registrationRecord[INDEX_TOURNAMENT] = registration.getTournament();
            csvWriter.writeNext(registrationRecord);
            csvWriter.flush();
        }
        finally {
            csvWriter.close();
        }
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
                            status
                    );
                    registration.setMessage(message);
                    registration.setTournament(tournament);

                    registrationList.add(registration);
                }
            }
            return registrationList;
        } finally {
            csvReader.close();
        }
    }

    @Override
    public List<Registration> showRequests(User user) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        try {
            String[] requestRecord;
            List<Registration> requestsList = new ArrayList<>();

            while ((requestRecord = csvReader.readNext()) != null) {
                String managerEmail = requestRecord[INDEX_MANAGER_EMAIL];
                if (Objects.equals(managerEmail, SessionManager.getInstance().getCurrentUser().getEmail()) && Objects.equals(requestRecord[INDEX_STATUS].toUpperCase(), "PENDING")) {
                    String team = requestRecord[INDEX_TEAM];
                    int numPlayers = Integer.parseInt(requestRecord[INDEX_NUM_PLAYERS]);
                    String captain = requestRecord[INDEX_CAPTAIN];
                    String customerEmail = requestRecord[INDEX_CUSTOMER_EMAIL];
                    RequestStatus status = RequestStatus.valueOf(requestRecord[INDEX_STATUS].toUpperCase());
                    String message = requestRecord[INDEX_MESSAGE];
                    String tournament = requestRecord[INDEX_TOURNAMENT];

                    Registration registration = new Registration(
                            customerEmail,
                            team,
                            numPlayers,
                            captain,
                            managerEmail,
                            status
                    );
                    registration.setMessage(message);
                    registration.setTournament(tournament);

                    requestsList.add(registration);
                }
            }
            return requestsList;
        } finally {
            csvReader.close();
        }
    }

    @Override
    public void acceptOrReject(Registration registration) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new BufferedReader(new FileReader(fd)));
        try {
            List<String[]> csvBody = reader.readAll();
            //cerco la riga corretta: devo avere la stessa email del customer, del manager ed il torneo passati come parametri
            //in più, per non andare ad accettare vecchie richieste con gli stessi dati, prendo solo quella che risulta pending
            //che sarà sicuramente unica
            for (String[] strArray : csvBody) {
                if (strArray[INDEX_CUSTOMER_EMAIL].equalsIgnoreCase(registration.getCustomerEmail())
                        && strArray[INDEX_MANAGER_EMAIL].equalsIgnoreCase(registration.getManagerEmail())
                        && strArray[INDEX_TOURNAMENT].equalsIgnoreCase(registration.getTournament())
                        && strArray[INDEX_STATUS].equalsIgnoreCase("pending")
                ) {
                    strArray[INDEX_STATUS] = String.valueOf(registration.getStatus().getId());
                    strArray[INDEX_MESSAGE] = registration.getMessage();
                }
            }

            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd)));

            try {
                csvWriter.writeAll(csvBody);
                csvWriter.flush();
            }
            finally {
                csvWriter.close();
            }
        }
        finally {
            reader.close();
        }
    }

}
