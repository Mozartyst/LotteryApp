import creators.*;
import dataSupport.FileService;
import dataSupport.LotteryNumbers;
import dataSupport.IrishLotteryDownloader;
import dataSupport.MultiCombinationReducer;
import entity.CombinationNumbers;
import lottoPropositions.NumbersAfterMultiCombinations;
import lottoPropositions.Proposition;
import testy.Testy;
import threeHunter.CombinationGenerators;
import threeHunter.Find3Numbers;
import threeHunter.Finded3NumbersTester;
import threeHunter.KeyTripleList;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę wyboru:");
        System.out.println("1 - Zaktualizuj LotteryNumber");
        System.out.println("2 - Zapisz LotteryNumber dla Algorithm");
        System.out.println("3 - Kreatory");
        System.out.println("4 - Reducer dla pliku MultiCombination");
        System.out.println("5 - ");
        System.out.println("6 - ");
        System.out.println("7 - Proposition");
        System.out.println("8 - Testy");
        System.out.println("9 - NAMultiCombination");
        System.out.println("10 -");
        System.out.println("11 - Wojtka ThreeNumbers");

        int number = scanner.nextInt();
        if (number == 1) {
            FileService.saveFile(new IrishLotteryDownloader().getNumbers(2020, 2020), "LotteryNumbersFile");
        } else if (number == 2) {
            FileService.saveFile(new IrishLotteryDownloader().getNumbers(2016, 2019), "LotteryNumbersForAlgorithm");
        } else if (number == 3) {
            System.out.println("Wybierz kreator");
            System.out.println("1 - Kreator Liczb i dependencji");
            System.out.println("2 - Kreator Duetów");
            System.out.println("3 - Kreator Algorytmu");
            System.out.println("4 - Kreator Combination");
            System.out.println("5 - Kreator MultiCombination");
            System.out.println("6 - Kreator NAN");
            System.out.println("7 - Kreator NAP");
            System.out.println("8 - Kreator NAT");
            int creatorChoice = scanner.nextInt();
            if (creatorChoice == 1) {
                try {
                    new NumberCreator().createNumbers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    new DependenciesCreator().createDependencies();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (creatorChoice == 2) {
                try {
                    new DuetCreator().createDuets();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (creatorChoice == 3) {
                new AlgorithmCreator().createAlgorithm();
            } else if (creatorChoice == 4) {
                try {
                    new CombinationCreator().createAllCombinationNumbers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (number == 5) {
                Thread thread1 = new Thread(new ComboKeyGenerator());
                Thread thread2 = new Thread(new ComboKeyGenerator());
                Thread thread3 = new Thread(new ComboKeyGenerator());
                Thread thread4 = new Thread(new ComboKeyGenerator());
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
            } else if (creatorChoice == 6) {
                new NANCreator().createNAN();
            } else if (creatorChoice == 7) {
                new NAPCreator().createNAP();
            } else if (creatorChoice == 8) {
                new NATCreator().createNAT();
            }
        } else if (number == 4) {
            try {
                new MultiCombinationReducer().reduceMultiFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (number == 5) {
        } else if (number == 6) {
        } else if (number == 7) {
            System.out.println("Podaj dla jakiego indeksu:");
            int index = scanner.nextInt();
            System.out.println(new Proposition(index).forMultiCombination());
            if (index != 0) {
                System.out.println(FileService.loadFile("LotteryNumbersFile").get(index - 1));
            }
        } else if (number == 8) {
            System.out.println("Wybierz rodzaj testu:");
            System.out.println("1 - NAN");
            System.out.println("2 - NAP");
            System.out.println("3 - NAT");
            System.out.println("4 - Duet");
            System.out.println("5 - Slant");
            System.out.println("6 - Algorithm");
            System.out.println("7 - ");
            System.out.println("8 - Random");
            System.out.println("9 - ");
            System.out.println("10 -");
            System.out.println("11 - NAMultiCombination");
            int choice = scanner.nextInt();
            Testy testy = new Testy();
            if (choice == 1) {
                testy.skutecznoscNumbersAfterNumber();
            } else if (choice == 2) {
                testy.skutecznoscNumbersAfterPairs();
            } else if (choice == 3) {
                testy.skutecznoscNumbersAfterTriple();
            } else if (choice == 4) {
                testy.skutecznoscDuet();
            } else if (choice == 5) {
                testy.skutecznoscSlant();
            } else if (choice == 6) {
                testy.skutecznoscAlgorithm();
            } else if (choice == 7) {
            } else if (choice == 8) {
                System.out.println("Podaj randomowych 6 liczb:");
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    int random = scanner.nextInt();
                    arrayList.add(random);
                }
                testy.skutecznoscLiczbRandomowych(arrayList);
            } else if (choice == 9) {
            } else if (choice == 10) {
            } else if (choice == 11) {
                testy.skutecznoscMultiCombination();
            }
        } else if (number == 9) {
            System.out.println("Podaj jaki index:");
            int index = scanner.nextInt();
            TreeMap<Integer, Integer> lotteryNumbersFile = new NumbersAfterMultiCombinations(FileService.loadFile("LotteryNumbersFile")).getProposition(index);
            System.out.println(lotteryNumbersFile);
            if (index > 0) {
                System.out.println(FileService.loadFile("LotteryNumbersFile").get(index - 1));
            }
        } else if (number == 10) {

        } else if (number == 11) {
            System.out.println("Podaj zakres pierwszej trójki:");
            System.out.println("OD:");
            int a = scanner.nextInt();
            System.out.println("DO:");
            int b = scanner.nextInt();
            System.out.println("Podaj zakres drugiej trójki:");
            System.out.println("OD:");
            int c = scanner.nextInt();
            System.out.println("DO:");
            int d = scanner.nextInt();
            LotteryNumbers lotteryNumbersFromFile = new LotteryNumbers();
            ArrayList<ArrayList<Integer>> lotteryNumbers = lotteryNumbersFromFile.convertToInt((ArrayList<String>) lotteryNumbersFromFile.readFileAndAddToList("src/main/resources/wyniki-6Liczb"));
            Collections.reverse(lotteryNumbers);

            CombinationGenerators combinationGenerators = new CombinationGenerators();
            Find3Numbers find3Numbers = new Find3Numbers();
            Finded3NumbersTester finded3NumbersTester = new Finded3NumbersTester();

            TreeMap<CombinationNumbers, TreeSet<Integer>> zmapka1 = find3Numbers.get3NumbersIndexes(combinationGenerators.getCombinations(a, b), lotteryNumbers);
            TreeMap<CombinationNumbers, TreeSet<Integer>> zmapka2 = find3Numbers.get3NumbersIndexes(combinationGenerators.getCombinations(c, d), lotteryNumbers);

            TreeMap<KeyTripleList, Integer> mapaWynikowa = finded3NumbersTester.getStats(zmapka1, zmapka2);

            mapaWynikowa.forEach((x, y) -> {
                System.out.println(x + " " + y);
            });
        }
    }
}

