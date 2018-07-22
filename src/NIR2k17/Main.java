package NIR2k17;

import jdk.internal.util.xml.impl.Pair;

import java.io.*;
import java.util.*;
import java.math.*;


public class Main {

    private final static String FILE_RESULT = "/home/dmitry/IdeaProjects/NIR/src/NIR2k17/result.txt";

    private static void encode(ArrayList<Pairs> isom, String secret, String str) throws IOException {
        double logar =0;
        logar = Math.log(isom.size()) / Math.log(2);
        FileWriter writer = new FileWriter(FILE_RESULT, true);

        if(secret.length() > 0 && logar > 0)
        {
            System.out.println(str);
            String nextBits = "";
            int nextBitsInt = 0;
            if (secret.length() > logar)
            {
                nextBits = secret.substring(0, (int) logar);
                nextBitsInt = Integer.parseInt(nextBits, 2);
                System.out.println("Предложение :");
                for (Node node:isom.get(nextBitsInt).predl) {
                    if(node.slovo_form.equals(isom.get(nextBitsInt).predl.get(isom.get(nextBitsInt).predl.size() - 1).slovo_form))
                        writer.write(node.slovo_form);
                    else writer.write(node.slovo_form + " ");
                }
                writer.write(". ");
                secret = secret.substring((int)logar);
            }
            else
            {
                nextBits = secret;
                nextBitsInt = Integer.parseInt(nextBits, 2);
                System.out.println("Предложение для текущих бит:");
                for (Node node:isom.get(nextBitsInt).predl)
                    System.out.println(node.slovo_form + " ");
                secret = "";
            }
        }
        else
        {
            System.out.println(str);
            System.out.println("Нельзя закодировать!");
            return;
        }
        System.out.println("isom size = " + isom.size());

        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

        final String FILE_START = "/home/dmitry/IdeaProjects/NIR/src/NIR2k17/start.txt";
        final String FILE_SECRET = "/home/dmitry/IdeaProjects/NIR/src/NIR2k17/secret.txt";
        final String FILE_DIR_SH = "/home/dmitry/TestChangeWC/installation/russian-malt.sh";
        final String FILE_DIR_STR_INPUT = "/home/dmitry/TestChangeWC/installation/input.txt";
        final String FILE_DIR_STR_OUTPUT = "/home/dmitry/TestChangeWC/installation/output.txt";
        final String FILE_DIR_TMPMALTTEXT = "/home/dmitry/TestChangeWC/installation/tmpmalttext.parse";

        ArrayList <Pairs> nabor = new ArrayList<>();
        ArrayList <Pairs> isom;
        String[] stroki = new String[200];
        Pairs firstpredl;
        String secret;

        FileReader fr = new FileReader(FILE_SECRET);
        Scanner sc = new Scanner(fr);

        stroki = new Scan(FILE_START).scanStart();
        secret = new Scan(FILE_SECRET).scanSecret();

        for (String str:stroki){

            /**Генерация перестановок*/
            new Generate().generation(str);
            /**Запуск скрипта*/
            new MaltParser().maltscan(FILE_DIR_SH, FILE_DIR_STR_INPUT, FILE_DIR_STR_OUTPUT);
            /**Считывание 1-го предложения*/
            firstpredl = new Parsing().parse(FILE_DIR_TMPMALTTEXT, nabor);
            /**Получение изоморфных предложений*/
            isom = new Isomorphic(firstpredl.predl.get(firstpredl.root), nabor).isomorficIs();
            encode(isom, secret, str);
        }
    }
}