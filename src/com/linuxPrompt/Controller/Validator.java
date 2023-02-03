package com.linuxPrompt.Controller;
import java.util.Arrays;
import java.util.List;

public class Validator {
    public static List<String> spiltCommands(String command){
        String[] commands = command.trim().split("\\s+");
        List<String> listOfCommands = Arrays.asList(commands);
        return listOfCommands;
    }

    public static boolean isValidCommand(List<String> commands){
        String command = commands.get(0);
        if(Shell.getShellVariables().containsKey(command)){
            int allowedArguments = Shell.getShellVariables().get(command);
            if((commands.size() > 1) && (commands.size() <= allowedArguments)) {
                return true;
            }
            else if(commands.size() == 1){
                System.out.print("");
            }
            else {
                System.out.println("bash : "+command+" : too many arguments");
            }
        }
        else{
            System.out.println("bash : "+command+" : command not found");
        }
        return false;
    }

    public static String removeSlash(String argument) {
        return argument.replace("/"," ");
    }
}
