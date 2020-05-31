package sample.Logic;

public class Cells {
    private ArrowType arrowType;
    private Point point;
    private int area;
    public ArrowType getArrowType() {
        return arrowType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
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

    public Cells(ArrowType arrowType, Point point,int area) {
        this.arrowType = arrowType;
        this.point = point;
        this.area = area;
    }
}
