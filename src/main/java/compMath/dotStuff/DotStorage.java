package compMath.dotStuff;

import java.util.List;

public class DotStorage {

    private final List<Dot> DOTS;

    public DotStorage(List<Dot> DOTS) {
        this.DOTS = DOTS;
    }

    public int size() {
        return DOTS.size();
    }

    public Dot getDot(int index) {
        return DOTS.get(index);
    }

    public double getMaxAbsX() {
        double maxAbs = 0;

        for (int i = 0; i < DOTS.size(); i++) {
            if (maxAbs < getDot(i).getX()) {
                maxAbs = getDot(i).getX();
            }
        }

        return maxAbs;
    }

    public double getMaxAbsY() {
        double maxAbs = 0;
        for (int i = 0; i < DOTS.size(); i++) {
            if (maxAbs < getDot(i).getY()) {
                maxAbs = getDot(i).getY();
            }
        }
        return maxAbs;
    }



}