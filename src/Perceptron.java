import java.util.ArrayList;
import java.util.HashMap;

public class Perceptron {

    public HashMap<Character, Double> letters = new HashMap<>();

    final Character[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    String language;

    double placement;

    public Perceptron(String language){
        this.language = language;
        int i = 2;
        for(Character c : characters) {
            if(i % 2 == 0) {
                letters.put(c, 1.0);
            } else {
                letters.put(c, 0.5);
            }
            i++;
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
