package NIR2k17;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Generate {
    private String[] tmp = new String[20];
    private int count = -1;
    private boolean bol;

    void generation(String stroka) throws IOException {
        tmp = stroka.split(" ");
        Arrays.sort(tmp);

        String posl = "";

        for (int i = 0; i < tmp.length; i++) {
            posl += i;
        }
        FileWriter writer = new FileWriter("/home/dmitry/TestChangeWC/installation/input.txt", false); // true - дозапись false перезапись.
        writer.write(stroka + ".\n");
        writer.close();

        perm1(posl);
    }

    public void perm1(String s) throws IOException {
        perm1("", s);
    }

    private void perm1(String prefix, String s) throws IOException {
        int n = s.length();
        if (n == 0) {
            count++;
            bol = count != 0;
            FileWriter writer = new FileWriter("/home/dmitry/TestChangeWC/installation/input.txt", true);
            // true - дозапись false перезапись.
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int a = Integer.parseInt(String.valueOf(c));
                if (i == 0) {
                  writer.write(tmp[a] + " ");
                }
                else if(i == prefix.length() - 1){
                    writer.write(tmp[a] + ".");
                }
                else writer.write(tmp[a] + " ");
            }
            writer.append('\n');
            writer.flush();
        } else {
            for (int i = 0; i < n; i++)
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
        }
    }

}
