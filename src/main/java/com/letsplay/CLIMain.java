package com.letsplay;

import com.letsplay.graphic_controller.CLI.CLIHome;
import com.letsplay.exception.Exception1;

public class CLIMain {
    public static void main(String[] args) throws Exception1 {
        CLIHome CLIHome = new CLIHome();
        CLIHome.start();
    }
}
