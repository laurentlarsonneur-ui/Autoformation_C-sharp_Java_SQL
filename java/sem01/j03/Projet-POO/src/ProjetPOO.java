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

import java.util.LinkedList;

class Shape {
    public double area() { return 0; }
    public double perimeter() { return 0; }
    public void display() {
        System.out.println("Forme générique");
    }
}

class Rectangle extends Shape {
    private final double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public void display() {
        System.out.println(String.format("Rectangle %f x %f - Aire = %f - Périmètre = %f", width, height, area(), perimeter()));
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void display() {
        System.out.println(String.format("Circle r = %f - Aire = %f - Périmètre = %f", radius, area(), perimeter()));
    }
}

class Triangle extends Shape {
    private final double basis, height;

    public Triangle(double basis, double height) {
        this.basis = basis;
        this.height = height;
    }

    @Override
    public double area() {
        return basis * height / 2;
    }

    @Override
    public double perimeter() {
        return 2 * Math.sqrt(Math.pow(2, (basis/2)) + Math.pow(2, height)) + basis;
    }

    @Override
    public void display() {
        System.out.println(String.format("Triangle b = %f x h = %f - Aire = %f - Périmètre = %f", basis, height, area(), perimeter()));
    }
}

class Drawing {
    LinkedList<Shape> mesFormes = new LinkedList<>();

    public void AddShape(Shape my_shape)
    {
        mesFormes.addLast(my_shape);
    }

    public void display() {
        System.out.println("Dessin");
    }

    public void DisplayAll() {
        double total = 0;

        for(Shape forme : mesFormes){
            forme.display();
            total += forme.area();
        }

         System.out.println("Aire totale = " + total);
    }
}

public class ProjetPOO {
    public static void main(String[] args) {
        Drawing my_drawing = new Drawing();
        my_drawing.AddShape(new Rectangle(4, 5));
        my_drawing.AddShape(new Circle(3));
        my_drawing.AddShape(new Triangle(3,4));
        my_drawing.DisplayAll();
    }
}
