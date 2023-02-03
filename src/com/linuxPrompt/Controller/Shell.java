package com.linuxPrompt.Controller;

import com.linuxPrompt.Model.bashHistory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Shell {
    static bashHistory history = bashHistory.getInstance();

    private static HashMap<String,Integer> shellVariables = new HashMap<>();

    private static boolean shellStatus = true;

    public static boolean shellStatus() {
        return shellStatus;
    }

    public static void setShellVariables(){
        shellVariables.put("cd",2);
        shellVariables.put("ls",1);
        shellVariables.put("pwd",1);
        shellVariables.put("mkdir",50);
        shellVariables.put("rmdir",2);
        shellVariables.put("exit",1);
    }

    public static HashMap<String, Integer> getShellVariables() {
        return shellVariables;
    }

    public static void changeDirectory(String directoryName) throws IOException {
        String path = history.getFilePath();
        File file = null;
        if(!directoryName.equals("..")) {
           if(path.equals("")){
               file = new File(directoryName+":\\");
               if(file.isDirectory() && file.exists()){
                   history.setFilePath(file.getCanonicalPath());
               }
               else{
                   System.out.println("bash : "+directoryName+" : no such directory");
               }
           }
           else{
               file = new File(path + "\\"+directoryName);
               if(file.isDirectory() && file.exists()){
                   history.setFilePath(file.getCanonicalPath());
               }
               else{
                   System.out.println("bash : "+directoryName+" : no such directory");
               }
           }
        }
        else{
            moveToPreviousDirectory();
        }
    }

    public static void listFiles(){
        String path = history.getFilePath();
        File directory = new File(path);
        File[] listOfFilesAndFolders = directory.listFiles();
        for(File fileOrFolder : listOfFilesAndFolders){
            System.out.println(fileOrFolder.getName());
        }
    }
    public static void printWorkingDirectory(){
        System.out.println(history.getFilePath());
    }

    public static void makeDirectory(List<String> directoryNames){
        for(String name : directoryNames) {
            File directory = new File(history.getFilePath() + "\\" + name);
            if (!directory.exists()) {
                System.out.println(name +": directory successfully created");
                directory.mkdir();
            } else {
                System.out.println("bash : not in a directory. Choose a directory");
            }
        }
    }

    public static void removeDirectory(String directoryName){
        File directory = new File(history.getFilePath()+"\\"+directoryName);
        if(directory.exists()){
            directory.delete();
        }
    }
    public static void exit(){
        shellStatus = false;
    }

    private static void moveToPreviousDirectory() throws IOException {
        String path = history.getFilePath();
        if(!path.equals("")) {
            File file = new File(path + "/..");
            history.setFilePath(file.getCanonicalPath());
            if (path.equals(history.getFilePath())) {
                history.setFilePath("");
            }
        }
    }
}
