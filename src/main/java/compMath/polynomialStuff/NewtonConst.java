package compMath.polynomialStuff;
import compMath.dotStuff.DotStorage;


public class NewtonConst implements Polynomial{

    private final DotStorage dotStorage;
    private final double[][] finDiffs;

    public NewtonConst(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
        this.finDiffs = countFinDiffs();
    }

    private double[][] countFinDiffs() {
        double[][] diffs = new double[dotStorage.size()][dotStorage.size()];
        countFinDiff(0, dotStorage.size() - 1, diffs);
        return diffs;
    }

    private double countFinDiff(int i, int k, double[][] diffs) {
        if (k == 0) {
            diffs[i][k] = dotStorage.getDot(i).getY();
            return diffs[i][k];
        }
        diffs[i][k-1] = countFinDiff(i, k-1, diffs);
        diffs[i+1][k-1] = countFinDiff(i+1, k-1, diffs);
        diffs[i][k] = diffs[i+1][k-1] - diffs[i][k-1];
        return diffs[i+1][k-1] - diffs[i][k-1];

    }


    @Override
    public double countValue(double x) {
        double middle = (dotStorage.getDot(dotStorage.size()-1).getX() - dotStorage.getDot(0).getX());
        if (x < middle) {
            return interpolateForward(x);
        }
        return interpolateBackward(x);
    }

    private double interpolateForward(double x) {
        int i;//Это та строка с который мы будем брать конечные разности
        for (i = 0; i < dotStorage.size()-1; i++) {
            if (dotStorage.getDot(i).getX() < x && x < dotStorage.getDot(i+1).getX()) break;
        }

        double result = dotStorage.getDot(i).getY();
        double tMult = (x - dotStorage.getDot(i).getX()) / (dotStorage.getDot(1).getX() - dotStorage.getDot(0).getX());
        double fact = 1;
        for (int k = 1; k < finDiffs.length; k++){
            fact *= k;
            result += (tMult *  finDiffs[i][k]) / fact;
            tMult *= (tMult - k);

        }

        return result;
    }

    private double interpolateBackward(double x) {
        int i;
        for (i = 0; i < dotStorage.size()-1; i++) {
            if (dotStorage.getDot(i).getX() < x && x < dotStorage.getDot(i+1).getX()) break;
        }
        int n = i+1;

        double result = dotStorage.getDot(n).getY();
        double tMult = (x - dotStorage.getDot(n).getX()) / (dotStorage.getDot(1).getX() - dotStorage.getDot(0).getX());
        double fact = 1;
        for (int k = 1; k < finDiffs.length; k++) {
            fact *= k;
            result += (tMult * finDiffs[n-k][k]) / fact;
            tMult *= (tMult + k);
        }

        return result;
    }

    @Override
    public String getName() {
        return "Полиномиал Ньютона с конечными разностями";
    }

    public void printFinDiffs() {
        System.out.println("Finite differences");

        System.out.print("    ");
        for (int i = 0; i < finDiffs.length; i++) {
            System.out.printf(" k%d                    ", i);
        }
        System.out.println();

        for (int i = 0; i <= finDiffs.length - 1; i++) {
            System.out.printf("i%d |", i);
            for (int j = 0; j <= finDiffs[i].length - 1; j++) {
                if (finDiffs[i][j] != 0) System.out.printf("% 20.16f | ", finDiffs[i][j]);
            }
            System.out.println();
        }
    }
}
