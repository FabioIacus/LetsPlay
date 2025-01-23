package com.letsplay;

import com.letsplay.graphicscontroller.cli.CLIHomePage;
import com.letsplay.exception.InputException;

public class CLIMain {
    public static void main(String[] args) throws InputException {
        System.out.println("--- Welcome to Let's Play! ---");
        CLIHomePage cliHomePage = new CLIHomePage();
        cliHomePage.start();
    }
}
