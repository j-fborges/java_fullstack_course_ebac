import br.com.j_fborges.AverageScore;

import java.util.Random;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome, Lets score tests and calculate the average score!");

        AverageScore avrg = new AverageScore();

        avrg.setTestScores(avrg.generateRandomScore(), avrg.generateRandomScore(), avrg.generateRandomScore(), avrg.generateRandomScore());
        avrg.calculateAverageScore();
        avrg.evaluateAverageScore();
    }
}

