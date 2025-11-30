// Penser à ajouter le driver mssql-jdbc-13.2.1.jre11.jar
// dans Project Structure/Library/New Library/Java

// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server
import java.util.Scanner;
import dao.CapteurDAO;
import model.Capteur;
import util.ConsoleHelper;

public class sql_backend {
    private static char menu(ConsoleHelper console) {
        System.out.println("\nChoose one option :");
        System.out.println(" c = Create");
        System.out.println(" r = Read");
        System.out.println(" u = Update");
        System.out.println(" d = Delete");
        System.out.println("\n q = Quit");
        char c = console.lireChoix('C', 'R', 'U', 'D', 'Q');
        return c;
    }

    private static void Create(ConsoleHelper console, CapteurDAO dao_prm) {
            Capteur cap = new Capteur();
            cap.setId(0);
            cap.setNom(console.lireChaine("Enter name:"));
            cap.setType(console.lireChaine("Enter type:"));
            cap.setUnite(console.lireChaine("Enter unit:"));
            dao_prm.Insert(cap);
        }

    private static void Change(ConsoleHelper console, CapteurDAO dao_prm) {
        int MonId = console.lireEntier("Which Id should be modified?");
        System.out.println("Searched index:" + MonId);
        Capteur cap = dao_prm.findById(MonId);
        cap.display();
        String s = console.lireChaineOptionnelle("Enter name (hit enter to keep existing):");
        if (!s.isEmpty()) cap.setNom(s);
        s = console.lireChaineOptionnelle("Enter type (hit enter to keep existing):");
        if (!s.isEmpty()) cap.setType(s);
        s = console.lireChaineOptionnelle("Enter unit (hit enter to keep existing):");
        if (!s.isEmpty()) cap.setUnite(s);
        cap.display();
        dao_prm.Update(cap);
        System.out.println("--- fin de Change ---");
        }

    private static void Eliminate(ConsoleHelper console, CapteurDAO dao_prm) {
        int MonId = console.lireEntier("Which Id should be deleted?");
        System.out.println("Searched index:" + MonId);
        //capteur cap = dao.findById(monId);
        //cap.display();

        Boolean b = console.lireBoolean("Confirm deletion (y/n):");
        if (b) dao_prm.Delete(MonId);
    }

    public static void main(String[] args) {
        ConsoleHelper console = new ConsoleHelper();
        Scanner sc = new Scanner(System.in);

        String cs = "jdbc:sqlserver://localhost:1440;" + "databaseName=SupervisionDB;"
            + "encrypt=false;" + "TrustServerCertificate=true;"
            + "user=sa;" + "password=gyuezo+fk5;";

        var dao = new CapteurDAO(cs);
        char choix;

        System.out.println("*** SQL backend test ***");
        do {
            choix = menu(console);
            switch (choix) {
                case 'C': // Create
                    Create(console, dao);
                    break;
                case 'R': // Read
                    var liste = dao.FindAll();
                    liste.forEach(cap -> cap.display());
                    break;
                case 'U': // Update
                    Change(console, dao);
                    break;
                case 'D': // Delete
                    Eliminate(console, dao);
                    break;
                case 'Q':
                    System.out.println("End of program");
                    break;
                default:
                    System.out.println("Invalid choice");
                    System.out.println(choix);
                    break;
            }
        } while (choix != 'Q');
        sc.close();
    }
}