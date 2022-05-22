package compMath.polynomialStuff;
import compMath.dotStuff.DotStorage;

import java.util.Arrays;
import java.util.List;


public class NewtonPolynomial implements Polynomial {

    private final DotStorage dotStorage;
    private final double[][] divDiffs;

    public NewtonPolynomial(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
        this.divDiffs = new double[dotStorage.size()][dotStorage.size()];
    }


    public double countValue(double x) {

        double sum = 0, dividedDifference, polynomial = 1;


        for (int i = 0; i < dotStorage.size(); i++) {
            dividedDifference = countDividedDifference(0, i);
            sum += dividedDifference * polynomial;
            polynomial *= (x - dotStorage.getDot(i).getX());
        }

        return sum;
    }

    @Override
    public String getName() {
        return "Полиномиал Ньютона";
    }

    private double countDividedDifference(int i, int diffDegree) {

        double result;

        if (diffDegree == 0) {
            result = dotStorage.getDot(i).getY();
            divDiffs[i][diffDegree] = result;
            return result;
        }

        double left = countDividedDifference(i + 1, diffDegree - 1);
        double right = countDividedDifference(i, diffDegree - 1);
        double bottom = dotStorage.getDot(i + diffDegree).getX() - dotStorage.getDot(i).getX();
        result = (left - right) / bottom;
        divDiffs[i][diffDegree] = result;

        return result;
    }

    public void printDivDiffs() {
        System.out.println("Divided differences");

        System.out.print("    ");
        for (int i = 0; i < divDiffs.length; i++) {
            System.out.printf(" k%d                    ", i);
        }
        System.out.println();

        for (int i = 0; i < divDiffs.length; i++) {
            System.out.printf("i%d |", i);
            for (int j = 0; j < divDiffs[i].length; j++) {
                if (divDiffs[i][j] != 0) System.out.printf("% 20.16f | ", divDiffs[i][j]);
            }
            System.out.println();
        }
    }

}