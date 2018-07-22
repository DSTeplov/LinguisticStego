package NIR2k17;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scan {

    private String[] stroki;
    private String secret;
    private String fileStart;

    Scan(String fileStart){
        stroki = new String[200];
        this.fileStart = fileStart;
    }
    String scanSecret() throws IOException {
        FileReader fr = new FileReader(fileStart);
        Scanner sc = new Scanner(fr);

        secret = sc.nextLine();
        fr.close();

        return secret;
    }

    String[] scanStart() throws IOException
    {
        FileReader fr = new FileReader(fileStart);
        Scanner sc = new Scanner(fr);

        stroki = sc.nextLine().split("[.!?]\\s*");
        fr.close();
        return stroki;
    }
}
