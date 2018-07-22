package NIR2k17;

import java.util.*;

class Isomorphic {
    private Node node;
    private ArrayList<Pairs> nabor;

    Isomorphic(Node node, ArrayList<Pairs> nabor) {
        this.node = node;
        this.nabor = nabor;
    }

    ArrayList<Pairs> isomorficIs() {
        ArrayList<Pairs> isom = new ArrayList<Pairs>();

        dfs(node);

        for (Pairs pair : nabor) {
            dfs(pair.predl.get(pair.root));
            if (node.code.equals(pair.predl.get(pair.root).code)) {
                if (node.slovo_form.equals(pair.predl.get(pair.root).slovo_form)) {
                    isom.add(pair);
                }
            }

        }
        return isom;
    }

    private void dfs(Node node) {
        List<Node> sons = node.sons;
        Vector vector = new Vector();
        for (Node n : sons) {
            if (n != null && !n.visited) {
                dfs(n);
                n.visited = true;
                vector.add(n.code);
            }
        }
        if (sons.size() == 0)
            node.code += node.slovo_form + "10";
        else {
            Collections.sort(vector);
            for (int i = 0; i < vector.size(); i++)
                node.code += vector.get(i);
            node.code = node.slovo_form + "1" + node.code + "0";
        }
    }
}
