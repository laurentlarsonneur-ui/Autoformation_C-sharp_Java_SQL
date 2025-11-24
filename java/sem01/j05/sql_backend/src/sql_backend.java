// Penser à ajouter le driver mssql-jdbc-13.2.1.jre11.jar
// dans Project Structure/Library/New Library/Java

// Structure du code
// Program / UI  →  Services  →  DAO  →  SQL Server

import java.util.Scanner;
import model.Capteur;
import dao.CapteurDAO;

public class sql_backend {
    private static char menu(Scanner scanner) {
        System.out.println("\nChoose one option :");
        System.out.println(" c = Create");
        System.out.println(" r = Read");
        System.out.println(" u = Update");
        System.out.println(" d = Delete");
        System.out.println("\n q = Quit");
        return (scanner.nextLine().charAt(0));
    }

    private static void Create(Scanner scanner) {
        Capteur cap = new Capteur();
        cap.setId(0);
        System.out.println("Enter name:");
        cap.setNom(scanner.nextLine());
        System.out.println("Enter type:");
        cap.setType(scanner.nextLine());
        System.out.println("Enter unit:");
        cap.setUnite(scanner.nextLine());
        //dao.Insert(cap);
    }

/*    private static public void Change(Scanner scanner) {
        System.out.println("Which Id should be modified?");
        int MonId = Convert.ToInt32(scanner.nextLine());
        System.out.println("Searched index:" + MonId);
        //Capteur cap = dao.findById(MonId);
        cap.display();

        System.out.println("Enter name (hit enter to keep existing):");
        string s = scanner.nextLine();
        if (s != '') cap.nom = s;
        System.out.println("Enter type (hit enter to keep existing):");
        s = scanner.nextLine();
        if (s != '') cap.type = s;
        System.out.println("Enter unit (hit enter to keep existing):");
        s = scanner.nextLine();
        if (s != '') cap.unite = s;

        cap.display();
        //dao.Update(cap);
    }
*/
    private static void Eliminate(Scanner scanner) {
        System.out.println("Which Id should be deleted?");
        int monId = Integer.parseInt(scanner.nextLine());
        System.out.println("Searched index:" + monId);
        //capteur cap = dao.findById(monId);
        //cap.display();

        System.out.println("Confirm deletion (y/n):");
        String s = scanner.nextLine();
        //if (s == 'y') dao.Delete(MonId);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choix;

        System.out.println("*** SQL backend test ***");
        do {
            choix = menu(sc);
            switch (choix) {
                case 'c':
                    Create(sc);
                    break;
                case 'r':
                    //var liste = dao.FindAll();
                    //foreach (Capteur cap in liste) cap.display();
                    break;
                case 'u':
                    //Change();
                    break;
                case 'd':
                    Eliminate(sc);
                    break;
                case 'q':
                    System.out.println("End of program");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println(choix);
        } while (choix != 'q');
    }
}