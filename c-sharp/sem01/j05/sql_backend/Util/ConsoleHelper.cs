using System;
using System.Linq;

///----------------------------------------------------
// Classe utilitaire pour simplifier les saisies console en C#
// Gère automatiquement les validations et les erreurs

// ## Caractères ##
//  • LireCaractere() - Lit un caractère
//  • LireChoix('C', 'R', 'U', 'D') - Valide automatiquement les options

// ## Chaînes ##
//  • LireChaine() - Lit une chaîne non vide
//  • LireChaineOptionnelle() - Peut être vide

// ## Nombres ##
//  • LireEntier() - Lit un entier avec validation
//  • LireEntier(1, 100) - Avec plage min/max
//  • LireDecimal() - Lit un nombre décimal

// ## Booléens ##
//  • LireBoolean() - Accepte O/N, Oui/Non, Y/N, etc.

// ## Utilitaires ##
//  • Pause() - Attend Entrée
//  • ClearScreen() - Efface l'écran
//  • AfficherCouleur() - Affiche un message avec une couleur spécifique
//  • AfficherSucces() - Affiche un message de succès en vert
//  • AfficherErreur() - Affiche un message d'erreur en rouge
//  • AfficherAvertissement() - Affiche un message d'avertissement en jaune
//  • AfficherInfo() - Affiche un message d'information en cyan
//----------------------------------------------------

namespace sql_backend.Util
{
    public class ConsoleHelper
{
    // ========== LECTURE DE CARACTÈRES ==========
    /// Lit un caractère (retourne '\0' si entrée vide)
    public char LireCaractere()
    {
        string input = Console.ReadLine()?.Trim().ToUpper() ?? string.Empty;
        return string.IsNullOrEmpty(input) ? '\0' : input[0];
    }

    /// Lit un caractère avec message personnalisé
    public char LireCaractere(string message)
    {
        Console.Write(message);
        return LireCaractere();
    }

    /// Lit un caractère parmi des options valides (redemande si invalide)
    /// Exemple : LireChoix('C', 'R', 'U', 'D', 'Q')
    public char LireChoix(params char[] optionsValides)
    {
        while (true)
        {
            char choix = LireCaractere();

            if (choix == '\0')
            {
                Console.Write("Entrée vide ! Réessayez : ");
                continue;
            }

            if (optionsValides.Contains(choix))
            {
                return choix;
            }

            Console.Write($"Choix invalide ! Options : {OptionsToString(optionsValides)} : ");
        }
    }

    /// Lit un caractère parmi des options avec message personnalisé
    public char LireChoix(string message, params char[] optionsValides)
    {
        Console.Write(message);
        return LireChoix(optionsValides);
    }

    // ========== LECTURE DE CHAÎNES ==========
    /// Lit une chaîne non vide (redemande si vide)
    public string LireChaine()
    {
        while (true)
        {
            string input = Console.ReadLine()?.Trim() ?? string.Empty;

            if (!string.IsNullOrEmpty(input))
            {
                return input;
            }

            Console.Write("Entrée vide ! Réessayez : ");
        }
    }

    /// Lit une chaîne non vide avec message personnalisé
    public string LireChaine(string message)
    {
        Console.Write(message);
        return LireChaine();
    }

    /// Lit une chaîne (peut être vide)
    public string LireChaineOptionnelle()
    {
        return Console.ReadLine()?.Trim() ?? string.Empty;
    }

    /// Lit une chaîne optionnelle avec message
    public string LireChaineOptionnelle(string message)
    {
        Console.Write(message);
        return LireChaineOptionnelle();
    }

    // ========== LECTURE DE NOMBRES ==========
    /// Lit un entier (redemande si invalide)
    public int LireEntier()
    {
        while (true)
        {
            string input = Console.ReadLine()?.Trim() ?? string.Empty;

            if (int.TryParse(input, out int resultat))
            {
                return resultat;
            }

            Console.Write("Nombre invalide ! Réessayez : ");
        }
    }

    /// Lit un entier avec message personnalisé
    public int LireEntier(string message)
    {
        Console.Write(message);
        return LireEntier();
    }

    /// Lit un entier dans une plage donnée
    public int LireEntier(int min, int max)
    {
        while (true)
        {
            int valeur = LireEntier();

            if (valeur >= min && valeur <= max)
            {
                return valeur;
            }

            Console.Write($"Valeur hors limites [{min}-{max}] ! Réessayez : ");
        }
    }

    /// Lit un entier dans une plage avec message
    public int LireEntier(string message, int min, int max)
    {
        Console.Write(message);
        return LireEntier(min, max);
    }

    /// Lit un nombre décimal (double)
    public double LireDecimal()
    {
        while (true)
        {
            string input = Console.ReadLine()?.Trim().Replace(',', '.') ?? string.Empty;

            if (double.TryParse(input, System.Globalization.NumberStyles.Any,
                System.Globalization.CultureInfo.InvariantCulture, out double resultat))
            {
                return resultat;
            }

            Console.Write("Nombre invalide ! Réessayez : ");
        }
    }

    /// Lit un nombre décimal avec message
    public double LireDecimal(string message)
    {
        Console.Write(message);
        return LireDecimal();
    }

    /// Lit un nombre décimal dans une plage
    public double LireDecimal(double min, double max)
    {
        while (true)
        {
            double valeur = LireDecimal();

            if (valeur >= min && valeur <= max)
            {
                return valeur;
            }

            Console.Write($"Valeur hors limites [{min}-{max}] ! Réessayez : ");
        }
    }

    /// Lit un nombre décimal dans une plage avec message
    public double LireDecimal(string message, double min, double max)
    {
        Console.Write(message);
        return LireDecimal(min, max);
    }

    // ========== LECTURE DE BOOLÉENS ==========
    /// Lit un booléen (O/N, Oui/Non, Y/N, Yes/No, True/False)
    public bool LireBoolean()
    {
        while (true)
        {
            string input = Console.ReadLine()?.Trim().ToUpper() ?? string.Empty;

            if (string.IsNullOrEmpty(input))
            {
                Console.Write("Entrée vide ! (O/N) : ");
                continue;
            }

            char c = input[0];

            if (c == 'O' || c == 'Y' || input == "OUI" ||
                input == "YES" || input == "TRUE")
            {
                return true;
            }

            if (c == 'N' || input == "NON" ||
                input == "NO" || input == "FALSE")
            {
                return false;
            }

            Console.Write("Réponse invalide ! (O/N) : ");
        }
    }

    /// Lit un booléen avec message personnalisé
    public bool LireBoolean(string message)
    {
        Console.Write(message);
        return LireBoolean();
    }

    // ========== LECTURE D'ÉNUMÉRATIONS ==========
    /// Lit une valeur d'énumération en affichant toutes les options
    public T LireEnum<T>() where T : struct, Enum
    {
        var valeurs = Enum.GetValues<T>();

        Console.WriteLine("Options disponibles :");
        for (int i = 0; i < valeurs.Length; i++)
        {
            Console.WriteLine($"{i + 1}. {valeurs[i]}");
        }

        while (true)
        {
            Console.Write("Votre choix (numéro ou nom) : ");
            string input = Console.ReadLine()?.Trim() ?? string.Empty;

            // Essayer de parser comme un nombre
            if (int.TryParse(input, out int index) && index >= 1 && index <= valeurs.Length)
            {
                return valeurs[index - 1];
            }

            // Essayer de parser comme un nom d'enum
            if (Enum.TryParse<T>(input, true, out T resultat))
            {
                return resultat;
            }

            Console.WriteLine("Choix invalide !");
        }
    }

    /// Lit une valeur d'énumération avec message personnalisé
    public T LireEnum<T>(string message) where T : struct, Enum
    {
        Console.WriteLine(message);
        return LireEnum<T>();
    }

    // ========== UTILITAIRES ==========
    /// Affiche un message et attend que l'utilisateur appuie sur une touche
    public void Pause()
    {
        Console.Write("\nAppuyez sur une touche pour continuer...");
        Console.ReadKey(true);
        Console.WriteLine();
    }

    /// Affiche un message personnalisé et attend une touche
    public void Pause(string message)
    {
        Console.Write(message);
        Console.ReadKey(true);
        Console.WriteLine();
    }

    /// Efface l'écran de la console
    public void ClearScreen()
    {
        Console.Clear();
    }

    /// Affiche un message avec une couleur spécifique
    public void AfficherCouleur(string message, ConsoleColor couleur)
    {
        ConsoleColor ancienneCouleur = Console.ForegroundColor;
        Console.ForegroundColor = couleur;
        Console.WriteLine(message);
        Console.ForegroundColor = ancienneCouleur;
    }

    /// Affiche un message de succès en vert
    public void AfficherSucces(string message)
    {
        AfficherCouleur($"✓ {message}", ConsoleColor.Green);
    }

    /// Affiche un message d'erreur en rouge
    public void AfficherErreur(string message)
    {
        AfficherCouleur($"✗ {message}", ConsoleColor.Red);
    }

    /// Affiche un message d'avertissement en jaune
    public void AfficherAvertissement(string message)
    {
        AfficherCouleur($"⚠ {message}", ConsoleColor.Yellow);
    }

    /// Affiche un message d'information en cyan
    public void AfficherInfo(string message)
    {
        AfficherCouleur($"ℹ {message}", ConsoleColor.Cyan);
    }

    // ========== MÉTHODES PRIVÉES ==========
    private string OptionsToString(char[] options)
    {
        if (options == null || options.Length == 0)
            return string.Empty;

        return string.Join("/", options);
    }
}
}

