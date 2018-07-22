package NIR2k17;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    int number; // Номер слова в предложении.
    String slovo_form; // Словоформа
    String sinto; //Синт. отношение для определения однор сказуем при считыв нагенеренных предл
    int parent; // Номер родителя
    boolean visited;
    String code;
    ArrayList <Node> sons;

    Node(int number, String slovo_form, int parent, String sinto)
    {
        this.number = number;
        this.slovo_form = slovo_form;
        this.parent = parent;
        this.sinto = sinto;
        this.sons = new ArrayList<>();
        this.code = "";
    }
    public void addSons(Node sonNode)
        {
            this.sons.add(sonNode);
        }

}
