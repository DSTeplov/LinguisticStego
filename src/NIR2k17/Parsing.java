package NIR2k17;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Parsing {
    //Переделать в метод
    Pairs parse(String FileDir, ArrayList<Pairs> nabor) throws IOException {

        String[] str = new String[10];
        ArrayList<Node> predl = new ArrayList<>();
        Pairs firstpredl = new Pairs();

        int root = -1;
        boolean flag = false;

        FileReader fr = new FileReader(FileDir);
        Scanner sc = new Scanner(fr);
        while (sc.hasNextLine()) {
            str = sc.nextLine().split("\\s+");
            if (str[0].equals("")) continue;
            if (str[1].equals(".")) {
                for (Node node : predl) {
                    if (node.parent != 0) {
                        predl.get(node.parent - 1).addSons(node);
                    } else root = node.number - 1;//Узнаем индекс корня
                }
                if (flag) {
                    Pairs pair = new Pairs(predl, root);
                    nabor.add(pair);//!
                } else {
                    firstpredl = new Pairs(predl, root);
                    flag = true;
                }
                predl = new ArrayList<>();
                root = -1;
            } else
                //Распарсили предложение в лист Node'ов.
                predl.add(new Node(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[6]), str[7]));
        }
        return firstpredl;
    }
}
