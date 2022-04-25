import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

public class Main {

    public static final Character[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws IOException {

        double learningRate = 0.2;

        ArrayList<String> files = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("src/Languages"))) {
                    paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> files.add(path.toString()));
        }



        for(int l = 0; l < files.size(); l++) {
            files.set(l, files.get(l).replace("\\", "/"));
        }

        HashSet<String> langs = new HashSet<>();
        String[] pt;

        for(String s : files) {
            pt = s.split("/");
            langs.add(pt[2]);
        }

        ArrayList<Perceptron> perceptrons = new ArrayList<>();

        for(String s : langs) {
            perceptrons.add(new Perceptron(s));
        }

        ArrayList<Entry> entries = new ArrayList<>();
        String[] parts;
        for(String s : files) {
            parts = s.split("/");
            entries.add(new Entry(s, parts[2]));
        }

       //Collections.shuffle(entries);

        for(Perceptron perceptron : perceptrons) {
            for (int k = 0; k < files.size() * 2; k++) {
                for (Entry e : entries) {
                    if (calculatePlacement(e, perceptron) > 0 && !(e.language.equals(perceptron.language))) {
                        for (Character c : characters) {
                            perceptron.letters.replace(c, correctWeight(perceptron.letters.get(c), learningRate, e.letters.get(c), -1.0));
                        }
                    } else if (calculatePlacement(e, perceptron) <= 0 && e.language.equals(perceptron.language)) {
                        for (Character c : characters) {
                            perceptron.letters.replace(c, correctWeight(perceptron.letters.get(c), learningRate, e.letters.get(c), 1.0));
                        }
                    }
                }
            }
        }

        for(Perceptron p : perceptrons) {
            p.normalize();
        }


        Entry entry = new Entry("src/TextToFind.txt");
        entry.normalize();

        ArrayList<Perceptron> ar = new ArrayList<>();

        for(Perceptron p : perceptrons) {
            double placement = calculatePlacement(entry, p);
            if (placement > 0){
                p.placement = placement;
                ar.add(p);
            }
        }

        Double max = ar.get(0).placement;

        for(Perceptron p : perceptrons) {
            if(p.placement > max) {
                max = p.placement;
            }
        }

        Perceptron resultPerceptron = new Perceptron("NoLanguageYet");

        for(Perceptron p : perceptrons) {
            if (p.placement == max) {
                resultPerceptron = p;
                break;
            }
        }

        System.out.println("The language is: " + resultPerceptron.language);

    }

    public static double calculatePlacement(Entry e, Perceptron p) {

        double result = 0;

        for(Character c : characters) {
            result = result + e.letters.get(c) * p.letters.get(c);
        }
        return result;

    }

    public static double correctWeight(double weight, double learningRate,
                                       double placement, double higherOrLower) {
        double correctedWeight = weight + learningRate*higherOrLower*placement;

        return correctedWeight;
    }



}
