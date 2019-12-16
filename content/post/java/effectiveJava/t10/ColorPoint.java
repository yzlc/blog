package effectiveJava.t10;

import java.awt.*;

public class ColorPoint extends Point{
    //private final Point point;//使用组合代替继承 正确实现equals
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        //point = new Point(x, y);
        this.color = color;
    }

    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        return super.equals(o) && ((ColorPoint) o).color == color;
    }

    // Broken - violates transitive
    /*@Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        // If o is a normal Point, do a color-blind comparison
        if (!(o instanceof ColorPoint))
            return o.equals(this);
        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((ColorPoint) o).color == color;
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }*/

    public static void main(String[] args) {
        // Broken - violates symmetry!
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(p.equals(cp));//true
        System.out.println(cp.equals(p));//false

        // Broken - violates transitive
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        System.out.println(p1.equals(p2));//true
        System.out.println(p2.equals(p3));//true
        System.out.println(p1.equals(p3));//false
    }
}
