package com.letsplay.notification;

import com.letsplay.exception.EmailException;
import com.letsplay.exception.FileException;
import com.letsplay.model.domain.Registration;

import java.io.*;

public class NotificationSystem implements Observer {
    private Registration registration;
    private static final String EMAIL_FILE = "EmailFile.txt";
    public NotificationSystem(Registration registration) {
        checkFile();
        this.registration = registration;
    }
    public void update() {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(EMAIL_FILE)))) {
            checkFile();
            if (registration.getStatus().getId().equals("Pending")) {
                printWriter.println("Email sent from " + registration.getCustomerEmail() + " to " + registration.getManagerEmail() + ":");
                printWriter.println("The request to register the " + registration.getTeam() + " team in the " + registration.getTournament() + " tournament has been sent!");
            } else {
                printWriter.println("Email sent from " + registration.getManagerEmail() + " to " + registration.getCustomerEmail() + ":");
                printWriter.printf("The registration for the %s tournament with the %s team, has been %s with the following message: %s",
                    registration.getTournament(),
                    registration.getTeam(),
                    registration.getStatus().getId(),
                    registration.getMessage());
            }
    } catch (FileException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkFile() throws FileException {
        File f = new File(EMAIL_FILE);

        if(!f.exists())
            throw new FileException("File doesn't exists!");

        if(!f.canWrite())
            throw new FileException("File read only!");
    }
}
