using System;

public class Shape
{
    public virtual double Area() => 0;
    public virtual void Display() => Console.WriteLine("Forme générique");
}

public class Rectangle : Shape
{
    public double Width { get; set; }
    public double Height { get; set; }

    public Rectangle(double width, double height)
    {
        Width = width;
        Height = height;
    }

    public override double Area() => Width * Height;

    public override void Display()
    {
        Console.WriteLine($"Rectangle {Width}x{Height} - Aire = {Area()}");
    }
}

class Program
{
    static void Main()
    {
        Shape s = new Rectangle(4, 5);
        s.Display();
    }
}