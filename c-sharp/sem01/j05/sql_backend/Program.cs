using Microsoft.Data.SqlClient;
using sql_backend.DAO;
using sql_backend.Model;
using System;
// Ne pas oublier d'ajouter ce package NuGet à la solution ou au projet
// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server

class Program
{
    static string connectionString = "Server=localhost,1440;Database=SupervisionDB;encrypt=false;trustServerCertificate=true;user=sa;password=gyuezo+fk5;";
    static CapteurDAO dao = new CapteurDAO(connectionString);
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
    static void Create()
    {
        Capteur cap = new Capteur();
        cap.Id = 0;
        Console.WriteLine("Enter name:");
        cap.Nom = Console.ReadLine();
        Console.WriteLine("Enter type:");
        cap.Type = Console.ReadLine();
        Console.WriteLine("Enter unit:");
        cap.Unite = Console.ReadLine();
        dao.Insert(cap);
    }
    static public void Change()
    {
        Console.WriteLine("Which Id should be modified?");
        int MonId = Convert.ToInt32(Console.ReadLine());
        Console.WriteLine("Searched index:" + MonId);
        Capteur cap = dao.findById(MonId);
        cap.display();

        Console.WriteLine("Enter name (hit enter to keep existing):");
        string s = Console.ReadLine();
        if (s != "") cap.Nom = s;
        Console.WriteLine("Enter type (hit enter to keep existing):");
        s = Console.ReadLine();
        if (s != "") cap.Type = s;
        Console.WriteLine("Enter unit (hit enter to keep existing):");
        s = Console.ReadLine();
        if (s != "") cap.Unite = s;

        cap.display();
        dao.Update(cap);
    }
    static void Eliminate()
    {
        Console.WriteLine("Which Id should be deleted?");
        int MonId = Convert.ToInt32(Console.ReadLine());
        Console.WriteLine("Searched index:" + MonId);
        Capteur cap = dao.findById(MonId);
        cap.display();

        Console.WriteLine("Confirm deletion (y/n):");
        string s = Console.ReadLine();
        if (s == "y") dao.Delete(MonId);
    }
    static void Main()
    {
        Console.WriteLine("*** SQL backend test ***");

        string choix;

        do
        {
            choix = Menu();
            switch (choix)
            {
                case "c":
                    Create();
                    break;
                case "r":
                    var liste = dao.FindAll();
                    foreach (Capteur cap in liste) cap.display();
                    break;
                case "u":
                    Change();
                    break;
                case "d":
                    Eliminate();
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
