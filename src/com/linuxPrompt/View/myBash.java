package com.linuxPrompt.View;

import com.linuxPrompt.Controller.Shell;
import com.linuxPrompt.Controller.Validator;
import com.linuxPrompt.Model.bashHistory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class myBash {

    ///Font Colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";

    ///-----------
    static bashHistory history = bashHistory.getInstance();
    private static void bashTitle(){
        String path = (history.getFilePath()!= null)? history.getFilePath() : "";
        String title = GREEN+"sanjay@sanjay" +ANSI_RESET+PURPLE+ " myShell64 / "+ANSI_RESET+YELLOW +path+ ANSI_RESET;
        System.out.print(title+"\n $ ");
    }

   private static String getUserCommands(){
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        return command;
    }

    private static void selectOperation(List<String> commands) throws IOException {
        String command = commands.get(0);
        String argument;
        switch (command){
            case "cd" :
                argument = Validator.removeSlash(commands.get(1));
                Shell.changeDirectory(argument);
                break;
            case "ls" :
                Shell.listFiles();
                break;
            case "pwd" :
                Shell.printWorkingDirectory();
                break;
            case "mkdir" :
                Shell.makeDirectory(commands);
                break;
            case "rmdir" :
                Shell.removeDirectory(commands.get(1));
                break;
            case "exit" :
                Shell.exit();
                break;
        }
    }

    public static void processCommand(){
        bashTitle();
        String command = getUserCommands();
        List<String> commandList = Validator.spiltCommands(command);
        if(Validator.isValidCommand(commandList)){
            try {
                selectOperation(commandList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
