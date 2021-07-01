package creators;

import java.util.ArrayList;
import java.util.List;

public class AllCombinationCreator {
    public List<List<Integer>> getCombinationNumbers(List<Integer> numbersList) {
        List<List<Integer>> combinationNumbers = new ArrayList<>();
        for (int i = 0; i < numbersList.size(); i++) {
            combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i))));
            if (numbersList.size() - i > 1) {
                combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i), numbersList.get(i + 1))));
                if (numbersList.size() - i > 2) {
                    combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i)
                            , numbersList.get(i + 1)
                            , numbersList.get(i + 2))));
                    if (numbersList.size() - i > 3) {
                        combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i)
                                , numbersList.get(i + 1)
                                , numbersList.get(i + 2)
                                , numbersList.get(i + 3))));
                        if (numbersList.size() - i > 4) {
                            combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i)
                                    , numbersList.get(i + 1)
                                    , numbersList.get(i + 2)
                                    , numbersList.get(i + 3)
                                    , numbersList.get(i + 4))));
                            if (numbersList.size() - i > 5) {
                                combinationNumbers.add(new ArrayList<>(List.of(numbersList.get(i)
                                        , numbersList.get(i + 1)
                                        , numbersList.get(i + 2)
                                        , numbersList.get(i + 3)
                                        , numbersList.get(i + 4)
                                        , numbersList.get(i + 5))));
                            }
                        }
                    }
                }
            }
        }
        return combinationNumbers;
    }
}
