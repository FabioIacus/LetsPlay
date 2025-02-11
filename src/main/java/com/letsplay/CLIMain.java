package com.letsplay;

import com.letsplay.graphicscontroller.cli.CLIHomePage;

public class CLIMain {
    public static void main(String[] args) {
        System.out.println("--- Welcome to Let's Play! ---");
        CLIHomePage cliHomePage = new CLIHomePage();
        cliHomePage.start();
    }
}
 