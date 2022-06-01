package compMath;

import compMath.dotStuff.Dot;
import compMath.dotStuff.DotStorage;
import compMath.polynomialStuff.LagrangePolynomial;
import compMath.polynomialStuff.NewtonConst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {

        DotStorage dotStorage;
        try {
            dotStorage = readDots();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        LagrangePolynomial lagrangePolynomial = new LagrangePolynomial(dotStorage);

        NewtonConst newtonConst = new NewtonConst(dotStorage);

        System.out.println("Введите x: ");
        double x = Double.parseDouble(reader.readLine());

        double valueByLagrangePolynomial = lagrangePolynomial.countValue(x);
        double valueByNewtonConst = newtonConst.countValue(x);

        System.out.println("Value by lagrange polynomial: " + valueByLagrangePolynomial);

        System.out.println("Value by newton const polynomial: " + valueByNewtonConst );
        newtonConst.printFinDiffs();

        Graphic graphic = new Graphic();
        graphic.setData(dotStorage, List.of(lagrangePolynomial, newtonConst));
        graphic.run();
    }

    private static DotStorage readDots() throws IOException {

        System.out.println("Введите имя файла или 0 для ввода с клавиатуры");
        String input = reader.readLine();

        if (input.equals("0")) {
            return parseDotStorage(reader);
        } else {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(input))) {
                return parseDotStorage(fileReader);
            }
        }
    }


    private static DotStorage parseDotStorage(BufferedReader reader) throws IOException {
        List<Dot> dotList = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) {
                break;
            }

            String[] elements = line.trim().split("(\\s++)");
            if (elements.length != 2) {
                throw new IllegalArgumentException("Должно быть только два числа");
            } else {
                try {
                    double x = Double.parseDouble(elements[0]);
                    double y = Double.parseDouble(elements[1]);
                    dotList.add(new Dot(x, y));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Failed to parse x or y");
                }

            }
        }

        return new DotStorage(dotList);

    }

}