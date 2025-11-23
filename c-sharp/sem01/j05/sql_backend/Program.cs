using Microsoft.Data.SqlClient;
using sql_backend.DAO;
using sql_backend.Model;
using System;
// Ne pas oublier d'ajouter ce package NuGet à la solution ou au projet
// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server

class Program
{
    static string Menu()
    {
        Console.WriteLine("\nChoose one option :");
        Console.WriteLine(" c = Create");
        Console.WriteLine(" r = Read");
        Console.WriteLine(" u = Update");
        Console.WriteLine(" d = Delete");
        Console.WriteLine("\n q = Quit");
        return Console.ReadLine();
    }
    static void Main()
    {
        Console.WriteLine("*** SQL backend test ***");
        string connectionString = "Server=localhost;Database=SupervisionDB;encrypt=false;trustServerCertificate=true;user=sa;password=gyuezo+fk5;";
        var dao = new CapteurDAO(connectionString);
        string choix;

        do
        {
            choix = Menu();
            switch (choix)
            {
                case "c":
                    dao.Create();
                    break;
                case "r":
                    var liste = dao.FindAll();
                    foreach (Capteur cap in liste) cap.display();
                    break;
                case "u":
                    dao.Update();
                    break;
                case "d":
                    dao.Delete();
                    break;
                case "q":
                    Console.WriteLine("End of program");
                    break;
                default:
                    Console.WriteLine("Invalid choice");
                    break;
            }
        } while (choix != "q");
    }
}
