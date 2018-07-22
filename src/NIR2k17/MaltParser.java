package NIR2k17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaltParser {
    void maltscan(String fileDirSh, String fileDirStrInput, String fileDirStrOutput) throws IOException {

        String s = null;

        try {
            String[] command = {"/bin/sh", fileDirSh, fileDirStrInput, fileDirStrOutput};
            Process p = Runtime.getRuntime().exec(command);
            //p.waitFor();

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                   InputStreamReader(p.getErrorStream()));

            // read the output from the command
           /*
           System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            */

            // read any errors from the attempted command

            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                if(s.length() > 0 && s.charAt(0) == 'F') return;
            }

            System.out.println("Отработало!");
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
             System.exit(-1);
        }
    }
}
