package com.letsplay;

import com.letsplay.controller.graphic_controller.CLI.ApplicationController;
import com.letsplay.exception.Exception1;

public class CLIMain {
    public static void main(String[] args) throws Exception1 {
        ApplicationController applicationController = new ApplicationController();
        applicationController.start();
    }
}
