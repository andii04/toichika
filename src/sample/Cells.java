package sample;

import java.awt.*;

public class Cells {
    private ArrowType arrowType;
    private Point point;
    public ArrowType getArrowType() {
        return arrowType;
    }

    public void setArrowType(ArrowType arrowType) {
        this.arrowType = arrowType;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Cells(ArrowType arrowType, Point point) {
        this.arrowType = arrowType;
        this.point = point;
    }
}
