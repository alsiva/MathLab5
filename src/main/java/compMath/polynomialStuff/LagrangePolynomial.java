package compMath.polynomialStuff;

import compMath.dotStuff.DotStorage;

public class LagrangePolynomial implements Polynomial {

    private final DotStorage dotStorage;

    public LagrangePolynomial(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
    }

    public double countValue(double x, boolean print) {
        double value = 0;
        double top, bottom, yi;

        for (int i = 0; i < dotStorage.size(); i++) {
            yi = dotStorage.getDot(i).getY();
            top = countTop(x, i);
            bottom = countBottom(i);
            value += (yi * (top/bottom));
        }

        return value;
    }

    @Override
    public String getName() {
        return "Полиномиал Лагранжа";
    }

    private double countTop(double x, int index) {
        double value = 1;
        for (int i = 0; i < dotStorage.size(); i++) {
            if (index != i) {
                value *= (x - dotStorage.getDot(i).getX());
            }
        }
        return value;
    }

    private double countBottom(int index) {
        double value = 1;
        for (int i = 0; i < dotStorage.size(); i++) {
            if (index != i) {
                value *= (dotStorage.getDot(index).getX() - dotStorage.getDot(i).getX());
            }
        }
        return value;
    }


}