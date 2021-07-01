package lottoPropositions;

import entity.Number;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class NumbersFromWeights {
    public Set<Integer> get(Collection<Number> numbers){
        int first = 0;
        double firstWeight = 0.0;
        int second = 0;
        double secondWeight = 0.0;
        int third = 0;
        double thirdWeight = 0.0;
        int fourth = 0;
        double fourthWeight = 0.0;
        int fifth = 0;
        double fifthWeight = 0.0;
        int sixth = 0;
        double sixthWeight = 0.0;
        for (Number number : numbers) {
            double weight = number.getWeight();
            if (weight > firstWeight) {
                sixthWeight = fifthWeight;
                sixth = fifth;
                fifthWeight = fourthWeight;
                fifth = fourth;
                fourthWeight = thirdWeight;
                fourth = third;
                thirdWeight = secondWeight;
                third = second;
                secondWeight = firstWeight;
                second = first;
                firstWeight = weight;
                first = number.getValue();
            } else if (weight > secondWeight) {
                sixthWeight = fifthWeight;
                sixth = fifth;
                fifthWeight = fourthWeight;
                fifth = fourth;
                fourthWeight = thirdWeight;
                fourth = third;
                thirdWeight = secondWeight;
                third = second;
                secondWeight = weight;
                second = number.getValue();
            } else if (weight > thirdWeight) {
                sixthWeight = fifthWeight;
                sixth = fifth;
                fifthWeight = fourthWeight;
                fifth = fourth;
                fourthWeight = thirdWeight;
                fourth = third;
                thirdWeight = weight;
                third = number.getValue();
            } else if (weight > fourthWeight) {
                sixthWeight = fifthWeight;
                sixth = fifth;
                fifthWeight = fourthWeight;
                fifth = fourth;
                fourthWeight = weight;
                fourth = number.getValue();
            } else if (weight > fifthWeight) {
                sixthWeight = fifthWeight;
                sixth = fifth;
                fifthWeight = weight;
                fifth = number.getValue();
            } else if (weight > sixthWeight) {
                sixthWeight = weight;
                sixth = number.getValue();
            }
        }
        Set<Integer> numbersList = new TreeSet<>();
        numbersList.add(first);
        numbersList.add(second);
        numbersList.add(third);
        numbersList.add(fourth);
        numbersList.add(fifth);
        numbersList.add(sixth);
        return numbersList;
    }
}
