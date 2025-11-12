/* See Mini-projet : gestion de formes géométriques
🎯 Objectif : Créer un petit programme orienté objet avec plusieurs classes et interactions entre elles.

📋 Étapes
1.  Créer une classe `Shape` (abstraite) avec `Area()` et `Perimeter()`.
2.  Créer des classes filles : `Rectangle`, `Circle`, `Triangle`.
3.  Créer une classe `Drawing` contenant une liste de `Shape`.
4.  Ajouter `AddShape(Shape s)` et `DisplayAll()`.
5.  Dans `Main()`, instancier plusieurs formes et les afficher.
6.  **Bonus :** calculer l’aire totale et trier les formes.
 */

using System;
using System.Collections.Generic;
using System.Drawing;
using System.Runtime.CompilerServices;

public class Shape
{
    public virtual double Area() => 0;
    public virtual double Perimeter() => 0;
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
    public override double Perimeter() => 2 * (Width + Height);
    public override void Display()
    {
        Console.WriteLine($"Rectangle {Width}x{Height} - Aire = {Area()} - Périmètre = {Perimeter()}");
    }
}

public class Circle : Shape
{
    public double Radius { get; set; }

    public Circle(double radius)
    {
        Radius = radius;
    }

    public override double Area() => double.Pi * Radius * Radius;
    public override double Perimeter() => 2 * double.Pi * Radius;
    public override void Display()
    {
        Console.WriteLine($"Cercle r={Radius} - Aire = {Area()} - Périmètre = {Perimeter()}");
    }
}

public class Triangle : Shape
{
    public double Side { get; set; }

    public Triangle(double side)
    {
        Side = side;
    }

    public override double Area() => 0.5 * double.Sqrt(3) * Side * Side ;
    public override double Perimeter() => 3 * Side;
    public override void Display()
    {
        Console.WriteLine($"Triangle s={Side} - Aire = {Area()} - Périmètre = {Perimeter()}");
    }
}
public class Drawing
{
    public List<Shape> my_list = new List<Shape>();
    public Drawing()
    {
    }
    public void AddShape(Shape my_shape)
    {
        my_list.Add(my_shape);
    }
    public virtual void Display()
    {
        Console.WriteLine("Dessin");
    }
    public virtual void DisplayAll()
    {
        double total = 0;

        foreach (Shape my_shape in my_list)
        {
            my_shape.Display();
            total += my_shape.Area();
        }
        Console.WriteLine("Aire totale = " + total);
    }
}

class Program
{
    static void Main()
    {
        Drawing my_drawing = new Drawing();
        my_drawing.AddShape(new Rectangle(4, 5));
        my_drawing.AddShape(new Circle(3));
        my_drawing.AddShape(new Triangle(3));
        my_drawing.DisplayAll();
    }
}
