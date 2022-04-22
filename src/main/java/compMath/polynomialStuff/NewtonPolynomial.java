package compMath.polynomialStuff;

import compMath.dotStuff.DotStorage;

public class NewtonPolynomial implements Polynomial {

    private final DotStorage dotStorage;

    public NewtonPolynomial(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
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

    private double countDividedDifference(int i, int diffDegree) {

        if (diffDegree == 0) {
            return dotStorage.getDot(i).getY();
        }

        double left = countDividedDifference(i + 1, diffDegree - 1);
        double right = countDividedDifference(i, diffDegree - 1);
        double bottom = dotStorage.getDot(i + diffDegree).getX() - dotStorage.getDot(i).getX();

        return (left - right) / bottom;
    }

}