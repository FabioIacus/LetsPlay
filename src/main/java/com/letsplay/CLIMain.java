package com.letsplay;

import com.letsplay.controller.graphic_controller.CLI.CLIHome;

import java.io.IOException;

public class CLIMain {
    public static void main(String[] args) throws IOException {
        CLIHome applicationController = new CLIHome();
        applicationController.start();
    }
}
