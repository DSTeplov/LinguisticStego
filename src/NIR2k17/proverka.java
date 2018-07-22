package NIR2k17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class proverka {
    public ArrayList<Pairs> prover(ArrayList<Pairs> mainslovo, ArrayList<Pairs> isom) {
        ArrayList<PairofStrings> pairofStrings = new ArrayList<>();
        ArrayList<Pairs> result = new ArrayList<>();
        boolean flag = true;
        for (Node node : mainslovo.get(0).predl) {
            if(node.parent != 0) pairofStrings.add(new PairofStrings(node.slovo_form, mainslovo.get(0).predl.get(node.parent - 1).slovo_form));//mainslovo.get(0).predl.get(mainslovo.get(0).root).slovo_form));
        }
        for (Pairs pair : isom) {
            flag = true;
            for (Node node : pair.predl) {
                for (PairofStrings pairStrings : pairofStrings) {
                    if (node.slovo_form.equals(pairStrings.first)) {
                        if (!pair.predl.get(node.parent - 1).slovo_form.equals(pairStrings.second)) {
                            flag = false;
                            break;
                        }
                    }

                }
                if(!flag) break;
            }
            if(flag) result.add(pair);
        }
        return result;
    }
}