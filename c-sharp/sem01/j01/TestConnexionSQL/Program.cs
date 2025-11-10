using System;
using Microsoft.Data.SqlClient;
// Ne pas oublier d'ajouter ce package NuGet à la solution ou au projet

class Program
{
    static void Main()
    {
        string connectionString = "Server=THINK-PC\\TEW_SQLEXPRESS;Database=master;Trusted_Connection=True;TrustServerCertificate=True;";

        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            try
            {
                conn.Open();
                Console.WriteLine("✅ Connexion réussie !");
                SqlCommand cmd = new SqlCommand("SELECT @@SERVERNAME", conn);
                string serverName = (string)cmd.ExecuteScalar();
                Console.WriteLine($"Serveur : {serverName}");
            }
            catch (Exception ex)
            {
                Console.WriteLine("❌ Erreur de connexion : " + ex.Message);
            }
        }
    }
}
