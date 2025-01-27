package com.letsplay.pattern;

import com.letsplay.exception.EmailException;
import com.letsplay.model.domain.Registration;

import java.io.*;

public class NotificationSystem implements Observer {
    private Registration registration;
    private static final String EMAIL_FILE = "EmailFile.txt";
    public NotificationSystem(Registration registration) throws EmailException {
        checkFile();
        this.registration = registration;
    }
    public void update() {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(EMAIL_FILE)))) {
            checkFile();
            //String state = "";
            if (registration.getStatus().getId().equals("pending")) {
                printWriter.println("Email sent from: " + registration.getCustomerEmail() + " to: " + registration.getManagerEmail());
            } else {
            /*printWriter.printf("The reservation for %s, date: %s, time: %s, people: %s, price: %s€ has been %s",
                    reservation.getTourName(),
                    reservation.getDate(),
                    reservation.getTime(),
                    reservation.getPeople(),
                    reservation.getPrice(),
                    state);*/
            }
    } catch (EmailException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkFile() throws EmailException {
        File f = new File(EMAIL_FILE);

        if(!f.exists())
            throw new EmailException("File doesn't exists!");

        if(!f.canWrite())
            throw new EmailException("File read only!");
    }
}
