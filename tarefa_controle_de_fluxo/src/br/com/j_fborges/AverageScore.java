package br.com.j_fborges;

import java.util.Random;

public class AverageScore {

    private double testScore1 = 0;
    private double testScore2 = 0;
    private double testScore3 = 0;
    private double testScore4 = 0;

    private double averageScore = 0;

    public double getTestScore1() {
        return testScore1;
    }

    public void setTestScore1(double testScore1) {
        this.testScore1 = testScore1;
    }

    public double getTestScore2() {
        return testScore2;
    }

    public void setTestScore2(double testScore2) {
        this.testScore2 = testScore2;
    }

    public double getTestScore3() {
        return testScore3;
    }

    public void setTestScore3(double testScore3) {
        this.testScore3 = testScore3;
    }

    public double getTestScore4() {
        return testScore4;
    }

    public void setTestScore4(double testScore4) {
        this.testScore4 = testScore4;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Inserts testsScores for calculation
     *
     * @param testScore1_
     * @param testScore2_
     * @param testScore3_
     * @param testScore4_
     */

    public void setTestScores(double testScore1_, double testScore2_, double testScore3_, double testScore4_) {

        System.out.println("Scoring tests");
        setTestScore1(testScore1_);
        System.out.println("Student scored " + getTestScore1() + " on Test 1");
        setTestScore2(testScore2_);
        System.out.println("Student scored " + getTestScore2() + " on Test 2");
        setTestScore3(testScore3_);
        System.out.println("Student scored " + getTestScore3() + " on Test 3");
        setTestScore4(testScore4_);
        System.out.println("Student scored " + getTestScore4() + " on Test 4");
        System.out.println("Tests scored");
    }

    public void calculateAverageScore() {
        System.out.println("Calculate average score");
        double testScore1 = getTestScore1();
        double testScore2 = getTestScore2();
        double testScore3 = getTestScore3();
        double testScore4 = getTestScore4();

        setAverageScore((testScore2 + testScore1 + testScore3 + testScore4) / 4);
        System.out.println("The average score is " + getAverageScore());
    }

    public double generateRandomScore() {

        Random random = new Random();

        return random.nextDouble() * 10;
    }

    public void evaluateAverageScore(){
        double score = getAverageScore();

        if(score >= 7){
            System.out.println("Congratulations, you are approved!");
        } else if(score >= 5){
            System.out.println("You are on catch-up! You`ll have to ongo the make-up test.");
        } else {
            System.out.println("Sorry, You have failed. Try again.");
        }
    }
}
