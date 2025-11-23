using Microsoft.Data.SqlClient;
using sql_backend.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
// DAO = Data Access Object
// Gère la communication avec SQL Server (CRUD).
// CRUD = Create (créer), Read (lire), Update (mettre à jour) et Delete (supprimer)

namespace sql_backend.DAO
{
    public class CapteurDAO
    {
        private string _connectionString;

        public CapteurDAO(string connectionString)
        {
            _connectionString = connectionString;
        }

        public void Create()
        {
            Capteur cap = new Capteur();
            cap.Id = 0;
            Console.WriteLine("Enter name:");
            cap.Nom = Console.ReadLine();
            Console.WriteLine("Enter type:");
            cap.Type = Console.ReadLine();
            Console.WriteLine("Enter unit:");
            cap.Unite = Console.ReadLine();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();
                string sql = "INSERT INTO Capteurs (nom, type, unite) VALUES ('"+ cap.Nom.Replace("\'", "\'\'") + "', '"+ cap.Type.Replace("\'", "\'\'") + "', '"+ cap.Unite.Replace("\'", "\'\'") + "')";
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.ExecuteNonQuery();
                Console.WriteLine("Connexion OK - SQL entry created");
            }
        }
        public List<Capteur> FindAll()
        {
            List<Capteur> mes_cap = new List<Capteur>(); // Liste vide de capteurs
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();

                string sql = "SELECT id, nom, type, unite FROM Capteurs";
                SqlCommand cmd = new SqlCommand(sql, conn);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Capteur cap = new Capteur();
                    cap.Id = Convert.ToInt32(reader["id"]);
                    cap.Nom = Convert.ToString(reader["nom"]);
                    cap.Type = Convert.ToString(reader["type"]);
                    cap.Unite = Convert.ToString(reader["unite"]);
                    mes_cap.Add(cap);
                }
            }
            return mes_cap;
        }
        public Capteur findById(int id)
        {
            Capteur cap = new Capteur();

            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();
                string sql = "SELECT * FROM Capteurs WHERE Id="+id;
                SqlCommand cmd = new SqlCommand(sql, conn);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    cap.Id = Convert.ToInt32(reader["id"]);
                    cap.Nom = Convert.ToString(reader["nom"]);
                    cap.Type = Convert.ToString(reader["type"]);
                    cap.Unite = Convert.ToString(reader["unite"]);
                }
            }
            return cap;
        }
        public void Update()
        {
            Console.WriteLine("Which Id should be modified?");
            int MonId = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Searched index:"+MonId);
            Capteur cap = findById(MonId);
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
            using (SqlConnection conn = new SqlConnection(_connectionString))
            {
                conn.Open();
                string sql = "UPDATE Capteurs SET nom = '" + cap.Nom.Replace("\'", "\'\'") 
                    + "', type = '" + cap.Type.Replace("\'", "\'\'") 
                    + "', unite = '" + cap.Unite.Replace("\'", "\'\'") 
                    + "'  WHERE id = " + cap.Id ;
                SqlCommand cmd = new SqlCommand(sql, conn);
                cmd.ExecuteNonQuery();
                Console.WriteLine("Connexion OK - SQL entry modified");
            }
        }
        public void Delete()
        {
            Console.WriteLine("Which Id should be deleted?");
            int MonId = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Searched index:" + MonId);
            Capteur cap = findById(MonId);
            cap.display();

            Console.WriteLine("Confirm deletion (y/n):");
            string s = Console.ReadLine();
            if (s == "y") {
                using (SqlConnection conn = new SqlConnection(_connectionString))
                {
                    conn.Open();
                    string sql = "DELETE FROM Capteurs WHERE id = " + cap.Id;
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.ExecuteNonQuery();
                    Console.WriteLine("Connexion OK - SQL entry deleted");
                }
            }
        }
    }

}
