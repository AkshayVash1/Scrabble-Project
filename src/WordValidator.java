package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordValidator {
    final Map<Integer, String> wordsMap = new HashMap<>();
    private static final String FILE = "C:\\Users\\Haghighi\\Desktop\\SYSC3110\\Scrabble Project\\Milestone 1\\Scrabble\\src\\words.txt";

    public WordValidator(ArrayList<String> wordsToValidate) {
        try {
            scanner();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            isWordsValid(wordsToValidate);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void scanner() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(FILE));
        int index = 0;
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            wordsMap.put(index, word);
            index++;
        }
        //System.out.println(wordsMap);
    }

    public boolean isWordsValid(ArrayList<String> wordsToValidate) throws FileNotFoundException {
        //Reading the word to be found from the user
        int count = 0;
        for (String word : wordsToValidate) {
            if (wordsMap.containsValue(word)) {
                count++;
            }
        }
        if (count == wordsToValidate.size()) {
            System.out.println("All words given are valid");
            return true;
        } else {
            System.out.println("Not all words given are valid");
            return false;
        }
    }
}





