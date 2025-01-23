package com.letsplay.graphicscontroller.cli;

import com.letsplay.bean.RegistrationBean;
import com.letsplay.bean.TournamentBean;
import com.letsplay.controller.JoinTournamentController;
import com.letsplay.exception.DatabaseException;
import com.letsplay.exception.EmptyFieldsException;
import com.letsplay.exception.InputException;
import com.letsplay.model.dao.SessionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class CLITournamentSignUp extends AbstractCLI {
    public void start(TournamentBean tournamentBean) {
        try {
            RegistrationBean registrationBean = new RegistrationBean(
                    SessionManager.getInstance().getCurrentUser().getEmail(),
                    tournamentBean.getManagerEmail(),
                    tournamentBean.getName()
            );
            while (true) {
                int choice;
                try {
                    choice = showMenu();
                    switch (choice) {
                        case 1 -> insertTeamName(registrationBean);
                        case 2 -> insertNumMember(registrationBean, tournamentBean);
                        case 3 -> insertCaptain(registrationBean);
                        case 4 -> sendRequest(registrationBean);
                        case 5 -> goHome();
                        //case 6 -> goBack(specifiedTourBeans);
                        case 7 -> viewMessages();
                        case 8 -> viewProfile();
                        case 9 -> {
                            System.out.println("Exiting the application...");
                            System.exit(0);
                        }
                        default -> System.out.println("Unexpected error!");
                    }
                } catch (InputException | EmptyFieldsException | DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int showMenu() throws InputException {
        System.out.println("-- Registration Form --");
        System.out.println("What would you like to do?");
        System.out.println("1) Insert team name");
        System.out.println("2) Insert number of players");
        System.out.println("3) Insert captain name");
        System.out.println("4) Submit");
        System.out.println("Other options: ");
        System.out.println("5) Go home");
        System.out.println("6) Go back");
        System.out.println("7) View notifications");
        System.out.println("8) View profile");
        System.out.println("9) Exit program");

        return getChoice(1,9);
    }

    //inserisci nome del team
    private void insertTeamName(RegistrationBean registrationBean) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Team name: ");
        String teamName = reader.readLine();
        int err = 0;
        if (teamName.isEmpty() || teamName.isBlank() || teamName.length() > 15) {
            System.out.println("Invalid team name!");
            err = 1;
        }
        if (err == 0) {
            System.out.println("Team inserted successfully!");
            registrationBean.setTeam(teamName);
        }
    }
    //inserisci numero dei giocatori in squadra
    private void insertNumMember(RegistrationBean registrationBean, TournamentBean tournamentBean) throws IOException, InputException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String type = tournamentBean.getType();
            int err = 0;
            int min = switch (type) {
                case "Calcio a 5" -> 5;
                case "Calcio a 8" -> 8;
                case "Calcio a 11" -> 11;
                default -> 0;
            };
            System.out.print("Number of team players: ");
            int numTeamPlayers = Integer.parseInt(reader.readLine());
            if (numTeamPlayers < min) {
                System.out.println("The minimum number of players is " + min + "!");
                err = 1;
            }
            if (numTeamPlayers > 20) {
                System.out.println("The maximum number of players per team is 20!");
                err = 1;
            }
            if (err == 0) {
                System.out.println("Number of players inserted successfully!");
                registrationBean.setNumPlayers(numTeamPlayers);
            }
        } catch(NumberFormatException e) {
            throw new InputException("You must enter a valid number!");
        }
    }

    //inserisci nome del capitano della squadra
    private void insertCaptain(RegistrationBean registrationBean) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Captain name: ");
        String captainName = reader.readLine();
        int err = 0;
        if (captainName.isEmpty() || captainName.isBlank() || captainName.length() > 15) {
            System.out.println("Invalid captain name!");
            err = 1;
        }
        if (err == 0) {
            System.out.println("Captain inserted successfully!");
            registrationBean.setCaptain(captainName);
        }
    }

    private void sendRequest(RegistrationBean registrationBean) throws EmptyFieldsException, InputException, IOException, DatabaseException {
        if (registrationBean.getTeam() == null || registrationBean.getNumPlayers() == 0 || registrationBean.getCaptain() == null){
            throw new EmptyFieldsException("There are empty fields!");
        }
        try {
            new JoinTournamentController().signUpTeam(registrationBean);
            //messaggio di conferma dell'invio della richiesta
            System.out.println("Request sent successfully!");
            System.out.println("--------------------------------");
            new CLIHomeCustomer().start();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*private void goBack(List<SpecifiedTourBean> specifiedTourBeans) throws InvalidFormatException, SQLException {
        TourSearchBean bean = new TourSearchBean(specifiedTourBeans.getFirst().getCity());
        List<TourBean> listOfTours = new JoinTourController().findTourOfCity(bean);
        new CLISelectTourGraphicController().start(listOfTours);
    }*/


}
