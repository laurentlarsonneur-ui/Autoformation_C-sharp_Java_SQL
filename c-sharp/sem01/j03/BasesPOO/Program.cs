using System;

public class Point
{
    private double x;
    private double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double DistanceTo(Point other)
    {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.Sqrt(dx * dx + dy * dy);
    }

    public void Display()
    {
        Console.WriteLine($"Point({x}, {y})");
    }
}

class Program
{
    static void Main()
    {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 7);
        p1.Display();
        Console.WriteLine($"Distance = {p1.DistanceTo(p2)}");
    }
}
