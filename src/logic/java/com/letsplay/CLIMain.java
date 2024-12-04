package com.letsplay;

import com.letsplay.graphicscontroller.cli.CLIHome;
import com.letsplay.exception.InputException;

public class CLIMain {
    public static void main(String[] args) throws InputException {
        CLIHome cliHome = new CLIHome();
        cliHome.start();
    }
}
