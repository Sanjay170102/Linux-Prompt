package com.linuxPrompt.Model;

public class bashHistory {

    private static bashHistory instance = null;

    public static bashHistory getInstance(){
        if(instance == null){
            instance = new bashHistory();
        }
        return instance;
    }
    private String filePath = "";

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
