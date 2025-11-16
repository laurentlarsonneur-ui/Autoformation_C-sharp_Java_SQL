/* Mini-projet : Calculatrice
🎯 Objectif :  Créer une calculatrice console (C# et Java) avec gestion d’erreurs et boucle utilisateur.

Application console permettant :
* Choix d’une opération (addition, soustraction, multiplication, division)
* Saisie de deux nombres
* Affichage du résultat
* Gestion des erreurs (division par zéro, saisie invalide)
* Boucle pour continuer tant que l’utilisateur le souhaite
 */

using System;
using System.Collections.Generic;
using System.Linq.Expressions;

class Program
{
    static string Saisie(string msg, string type, string contrainte)
    {
        string s = "";
        int monint = 0;
        double mondouble = 0;
        bool bad = true;

        do
        {
            bad = true;
            try
            {
                Console.Write(msg);
                s = Console.ReadLine();
                switch (type)
                {
                    case "char":
                        // code block
                        if ((s.Length != 1) || (!contrainte.Contains(s))) throw new FormatException("Saisie incorrecte.");
                        break;
                    case "int":
                        // code block
                        monint = int.Parse(s);
                        break;
                    case "double":
                        // code block
                        mondouble = double.Parse(s);
                        break;
                    default:
                        // code block
                        break;
                }
                bad = false;
            }
            catch (FormatException) { Console.WriteLine("{0}: Mauvais format de saisie !", s); }
            catch (OverflowException) { Console.WriteLine("{0}: Dépassement de capacité !", s); }
        }
        while (bad);
        return (s);
    }
    static double Calculer(double a, char op, double b)
    {
        return op switch
        {
            '+' => a + b,
            '-' => a - b,
            '*' => a * b,
            '/' => b != 0 ? a / b : double.NaN,
            _ => double.NaN
        };
    }
    static void Main()
    {
        char o, rep;
        double n1, n2, r;
        bool autre = false;

        Console.WriteLine("Bonjour");
        do
        {
            autre = false;
            o = Saisie("\nChoisissez une opération (+, -, *, /) :", "char", "+-*/")[0];
            n1 = double.Parse(Saisie("Choisissez le nombre 1 :", "double", ""));
            n2 = double.Parse(Saisie("Choisissez le nombre 2 :", "double", ""));
            r = Calculer(n1, o, n2);
            Console.WriteLine("Le résultat est {0}\n", r);
            rep = Saisie("Voulez vous faire une autre opération (o/n) ?", "char", "onON")[0];
            if (rep.Equals('o')) autre = true;
        }
        while (autre);
        Console.WriteLine("\nAu revoir !");
    }
}
