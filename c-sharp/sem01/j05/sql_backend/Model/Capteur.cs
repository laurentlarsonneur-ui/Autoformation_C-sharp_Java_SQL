using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
// Représente un objet métier (juste des données).

namespace sql_backend.Model
{
    public class Capteur
    {
        public int Id { get; set; }
        public string Nom { get; set; }
        public string Type { get; set; }
        public string Unite { get; set; }
        public void display() {
            Console.WriteLine($"Id: {Id} ; Nom: {Nom} ; Type: {Type} ; Unite: {Unite}");
            }
    }

}
