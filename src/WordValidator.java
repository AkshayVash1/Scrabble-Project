import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordValidator {
    final Map<Integer, String> wordsMap = new HashMap<>();
    private static final String FILE = "./words.txt";

    public WordValidator() {
        try {
            scanner();
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
            return true;
        } else {
            return false;
        }
    }
}





