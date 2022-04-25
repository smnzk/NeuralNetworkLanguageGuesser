import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Entry {

    public HashMap<Character, Double> letters = new HashMap<>();

    final Character[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    String language;



    public Entry(String path) throws IOException {
        for(Character c : characters) {
            letters.put(c, 0.0);
        }
        calculateLetters(path);

        double totalLetters = 0;
        for(Double value : letters.values()) {
            totalLetters = totalLetters + value;
        }

        for(Character c : characters) {
            letters.replace(c, letters.get(c) / totalLetters);
        }

    }

    public Entry(String path, String language) throws IOException {
        this.language = language;
        for(Character c : characters) {
            letters.put(c, 0.0);
        }
        calculateLetters(path);

        double totalLetters = 0;
        for(Double value : letters.values()) {
            totalLetters = totalLetters + value;
        }

        for(Character c : characters) {
            letters.replace(c, letters.get(c) / totalLetters);
        }

    }

    public void calculateLetters(String path) throws IOException {
        String content = Files.readString(Path.of(path));

        String[] words = content.split("\\s+");

        for(String word : words){
            word = word.toLowerCase();
            for(int i = 0; i < word.length(); i++) {
                try {
                    letters.replace(word.charAt(i), letters.get(word.charAt(i)) + 1);
                } catch (NullPointerException e) {
                    //ignoring this character
                }
            }
        }
    }

    public double calculateLength(){
        double total = 0;
        for(Double value : letters.values()) {
            total = total + Math.pow(value, 2);
        }
        total = Math.sqrt(total);
        return total;
    }

    public void normalize(){
        double length = this.calculateLength();
        for (Character c : characters) {
            this.letters.replace(c, this.letters.get(c)/length);
        }
    }

}
