class Shape {
    public double area() { return 0; }
    public void display() {
        System.out.println("Forme générique");
    }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void display() {
        System.out.println("Rectangle " + width + "x" + height + " - Aire = " + area());
    }
}

public class Heritage {
    public static void main(String[] args) {
        Shape s = new Rectangle(4, 5);
        s.display();
    }
}