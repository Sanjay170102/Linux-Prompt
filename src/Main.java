import com.linuxPrompt.Controller.Shell;
import com.linuxPrompt.View.myBash;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Shell.setShellVariables();
        while(Shell.shellStatus()){
            myBash.processCommand();
        }
    }
}