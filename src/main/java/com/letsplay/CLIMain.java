package com.letsplay;

import com.letsplay.controller.graphic_controller.CLI.CLIHome;
import com.letsplay.exception.InvalidInputException;

public class CLIMain {
    public static void main(String[] args) throws InvalidInputException {
        CLIHome applicationController = new CLIHome();
        applicationController.start();
    }
}
