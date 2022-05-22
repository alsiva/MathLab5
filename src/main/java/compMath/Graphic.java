package compMath;

import compMath.dotStuff.DotStorage;
import compMath.polynomialStuff.Polynomial;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;


public class Graphic extends Application implements Runnable {

    private static DotStorage dotStorage;
    private static List<Polynomial> polynomials;

    public void setData(DotStorage dotStorage, List<Polynomial> polynomials) {
        Graphic.dotStorage = dotStorage;
        Graphic.polynomials = polynomials;
    }

    @Override
    public void run() {
        launch();
    }

    @FXML
    public LineChart<Number, Number> lineChart;

    @Override
    public void start(Stage stage) throws Exception {
        lineChart = new LineChart<>(
            new NumberAxis(-dotStorage.getMaxAbsX() - 1, dotStorage.getMaxAbsX() + 1, 2),
            new NumberAxis(-dotStorage.getMaxAbsY() - 1, dotStorage.getMaxAbsY() + 1, 2)
        );
        lineChart.setCreateSymbols(true);
        Scene scene = new Scene(lineChart, 900, 600);


        for (Polynomial polynomial : polynomials) {
            drawPolynomial(polynomial);
        }
        drawDots(dotStorage);

        stage.setScene(scene);
        stage.show();
    }

    private void drawPolynomial(Polynomial polynomial) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(polynomial.getName());

        for (double point = dotStorage.getDot(0).getX(); point <= dotStorage.getDot(dotStorage.size() - 1).getX(); point += 0.01) {
            series.getData().add(new XYChart.Data<Number, Number>(point, polynomial.countValue(point)));
        }

        lineChart.getData().add(series);
    }

    private void drawDots(DotStorage dotStorage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();


        series.setName("Original function");
        for (int i = 0; i < dotStorage.size(); i++) {
            double x = dotStorage.getDot(i).getX();
            double y = dotStorage.getDot(i).getY();
            series.getData().add(new XYChart.Data<Number, Number>(x, y));
        }



        lineChart.getData().add(series);
    }

}
