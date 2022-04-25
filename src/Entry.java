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
                switch (word.charAt(i)){
                    case 'a':
                        this.letters.replace('a', letters.get('a') + 1);
                        break;
                    case 'b':
                        this.letters.replace('b', letters.get('b') + 1);
                        break;
                    case 'c':
                        this.letters.replace('c', letters.get('c') + 1);
                        break;
                    case 'd':
                        this.letters.replace('d', letters.get('d') + 1);
                        break;
                    case 'e':
                        this.letters.replace('e', letters.get('e') + 1);
                        break;
                    case 'f':
                        this.letters.replace('f', letters.get('f') + 1);
                        break;
                    case 'g':
                        this.letters.replace('g', letters.get('g') + 1);
                        break;
                    case 'h':
                        this.letters.replace('h', letters.get('h') + 1);
                        break;
                    case 'i':
                        this.letters.replace('i', letters.get('i') + 1);
                        break;
                    case 'j':
                        this.letters.replace('j', letters.get('j') + 1);
                        break;
                    case 'k':
                        this.letters.replace('k', letters.get('k') + 1);
                        break;
                    case 'l':
                        this.letters.replace('l', letters.get('l') + 1);
                        break;
                    case 'm':
                        this.letters.replace('m', letters.get('m') + 1);
                        break;
                    case 'n':
                        this.letters.replace('n', letters.get('n') + 1);
                        break;
                    case 'o':
                        this.letters.replace('o', letters.get('o') + 1);
                        break;
                    case 'p':
                        this.letters.replace('p', letters.get('p') + 1);
                        break;
                    case 'q':
                        this.letters.replace('q', letters.get('q') + 1);
                        break;
                    case 'r':
                        this.letters.replace('r', letters.get('r') + 1);
                        break;
                    case 's':
                        this.letters.replace('s', letters.get('s') + 1);
                        break;
                    case 't':
                        this.letters.replace('t', letters.get('t') + 1);
                        break;
                    case 'u':
                        this.letters.replace('u', letters.get('u') + 1);
                        break;
                    case 'v':
                        this.letters.replace('v', letters.get('v') + 1);
                        break;
                    case 'w':
                        this.letters.replace('w', letters.get('w') + 1);
                        break;
                    case 'x':
                        this.letters.replace('x', letters.get('x') + 1);
                        break;
                    case 'y':
                        this.letters.replace('y', letters.get('y') + 1);
                        break;
                    case 'z':
                        this.letters.replace('z', letters.get('z') + 1);
                        break;
                    default:
                        break;
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
