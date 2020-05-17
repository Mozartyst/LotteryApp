package lottoPropositions;

import java.util.TreeSet;

public class Proposition {
    Integer index;
    TreeSet<Integer> propositionList = new TreeSet<>();

    public Proposition(Integer index) {
        this.index = index;
        createProposition();
    }

    public TreeSet<Integer> getPropositionList() {
        return propositionList;
    }

    private void createProposition() {
        this.propositionList.addAll(propositionList);
    }
}

