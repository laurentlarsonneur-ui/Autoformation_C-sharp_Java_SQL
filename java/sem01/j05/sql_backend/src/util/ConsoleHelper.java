package util;
import java.util.Scanner;

/**
 * Classe utilitaire pour simplifier les saisies console en Java
 * Gère automatiquement les validations et les erreurs
 */

// ## Caractères ##
//  • lireCaractere() - Lit un caractère
//  • lireChoix('C', 'R', 'U', 'D') - Valide automatiquement les options
//
// ## Chaînes ##
//  • lireChaine() - Lit une chaîne non vide
//  • lireChaineOptionnelle() - Peut être vide
//
// ## Nombres ##
//  • lireEntier() - Lit un entier avec validation
//  • lireEntier(1, 100) - Avec plage min/max
//  • lireDecimal() - Lit un nombre décimal
//
// ## Booléens ##
//  • lireBoolean() - Accepte O/N, Oui/Non, Y/N, etc.
//
// ## Utilitaires ##
//  • pause() - Attend Entrée
//  • clearScreen() - Efface l'écran
//  • close() - Ferme le scanner


public class ConsoleHelper {
    private Scanner scanner;

    public ConsoleHelper() {
        this.scanner = new Scanner(System.in);
    }

    // ========== LECTURE DE CARACTÈRES ==========

    /**
     * Lit un caractère (retourne '\0' si entrée vide)
     */
    public char lireCaractere() {
        String input = scanner.nextLine().trim().toUpperCase();
        return input.isEmpty() ? '\0' : input.charAt(0);
    }

    /**
     * Lit un caractère avec message personnalisé
     */
    public char lireCaractere(String message) {
        System.out.print(message);
        return lireCaractere();
    }

    /**
     * Lit un caractère parmi des options valides (redemande si invalide)
     * Exemple : lireChoix('C', 'R', 'U', 'D', 'Q')
     */
    public char lireChoix(char... optionsValides) {
        while (true) {
            char choix = lireCaractere();

            if (choix == '\0') {
                System.out.print("Entrée vide ! Réessayez : ");
                continue;
            }

            for (char option : optionsValides) {
                if (choix == option) {
                    return choix;
                }
            }

            System.out.print("Choix invalide ! Options : " +
                    optionsToString(optionsValides) + " : ");
        }
    }

    /**
     * Lit un caractère parmi des options avec message personnalisé
     */
    public char lireChoix(String message, char... optionsValides) {
        System.out.print(message);
        return lireChoix(optionsValides);
    }

    // ========== LECTURE DE CHAÎNES ==========

    /**
     * Lit une chaîne non vide (redemande si vide)
     */
    public String lireChaine() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.print("Entrée vide ! Réessayez : ");
        }
    }

    /**
     * Lit une chaîne non vide avec message personnalisé
     */
    public String lireChaine(String message) {
        System.out.print(message);
        return lireChaine();
    }

    /**
     * Lit une chaîne (peut être vide)
     */
    public String lireChaineOptionnelle() {
        return scanner.nextLine().trim();
    }

    /**
     * Lit une chaîne optionnelle avec message
     */
    public String lireChaineOptionnelle(String message) {
        System.out.print(message);
        return lireChaineOptionnelle();
    }

    // ========== LECTURE DE NOMBRES ==========

    /**
     * Lit un entier (redemande si invalide)
     */
    public int lireEntier() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Nombre invalide ! Réessayez : ");
            }
        }
    }

    /**
     * Lit un entier avec message personnalisé
     */
    public int lireEntier(String message) {
        System.out.print(message);
        return lireEntier();
    }

    /**
     * Lit un entier dans une plage donnée
     */
    public int lireEntier(int min, int max) {
        while (true) {
            int valeur = lireEntier();

            if (valeur >= min && valeur <= max) {
                return valeur;
            }

            System.out.print("Valeur hors limites [" + min + "-" + max + "] ! Réessayez : ");
        }
    }

    /**
     * Lit un entier dans une plage avec message
     */
    public int lireEntier(String message, int min, int max) {
        System.out.print(message);
        return lireEntier(min, max);
    }

    /**
     * Lit un nombre décimal (double)
     */
    public double lireDecimal() {
        while (true) {
            try {
                String input = scanner.nextLine().trim().replace(',', '.');
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Nombre invalide ! Réessayez : ");
            }
        }
    }

    /**
     * Lit un nombre décimal avec message
     */
    public double lireDecimal(String message) {
        System.out.print(message);
        return lireDecimal();
    }

    // ========== LECTURE DE BOOLÉENS ==========

    /**
     * Lit un booléen (O/N, Oui/Non, Y/N, Yes/No, True/False)
     */
    public boolean lireBoolean() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                System.out.print("Entrée vide ! (O/N) : ");
                continue;
            }

            char c = input.charAt(0);

            if (c == 'O' || c == 'Y' || input.equals("OUI") ||
                    input.equals("YES") || input.equals("TRUE")) {
                return true;
            }

            if (c == 'N' || input.equals("NON") ||
                    input.equals("NO") || input.equals("FALSE")) {
                return false;
            }

            System.out.print("Réponse invalide ! (O/N) : ");
        }
    }

    /**
     * Lit un booléen avec message personnalisé
     */
    public boolean lireBoolean(String message) {
        System.out.print(message);
        return lireBoolean();
    }

    // ========== UTILITAIRES ==========

    /**
     * Affiche un message et attend que l'utilisateur appuie sur Entrée
     */
    public void pause() {
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    /**
     * Affiche un message personnalisé et attend Entrée
     */
    public void pause(String message) {
        System.out.print(message);
        scanner.nextLine();
    }

    /**
     * Efface l'écran (fonctionne sur la plupart des terminaux)
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Ferme le scanner (à appeler à la fin du programme)
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // ========== MÉTHODES PRIVÉES ==========

    private String optionsToString(char[] options) {
        if (options.length == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            sb.append(options[i]);
            if (i < options.length - 1) {
                sb.append("/");
            }
        }
        return sb.toString();
    }
}