package com.example.task1

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ambil referensi dari XML
        val button = findViewById<Button>(R.id.button1)
        val inputNama = findViewById<EditText>(R.id.inputName)
        val pilihan = findViewById<Spinner>(R.id.spinner) // Menggunakan Spinner yang sudah ada
        val hasilText = findViewById<TextView>(R.id.hasil)
        val hasilKamu = findViewById<TextView>(R.id.hasilKamu)
        val hasilMusuh = findViewById<TextView>(R.id.hasilMusuh)

        // Mapping pilihan ke angka (Gunakan nilai dari Spinner yang sudah dibuat)
        val pilihanMap = mapOf(
            "Gunting" to 1,
            "Batu" to 2,
            "Kertas" to 3
        )
        val pilihanList = pilihanMap.keys.toList()

        // Buat adapter untuk Spinner (Mengisi opsi ke Spinner yang sudah ada)

        val adapter = ArrayAdapter(this, R.layout.spinner_item, pilihanList)
        pilihan.adapter = adapter


        // Ketika tombol diklik
        button.setOnClickListener {
            val namaUser = inputNama.text.toString().ifEmpty { "Kamu" } // Default jika nama kosong

            // Ambil pilihan user dari Spinner yang sudah ada
            val userChoiceString = pilihan.selectedItem.toString()
            val userChoice = pilihanMap[userChoiceString] ?: 0

            // Pilihan lawan (acak 1-3)
            val lawan = (1..3).random()

            // Tentukan pemenang
            val hasil = when {
                userChoice == lawan -> "$namaUser Seri!"
                (userChoice == 1 && lawan == 3) || // Gunting menang lawan Kertas
                        (userChoice == 2 && lawan == 1) || // Batu menang lawan Gunting
                        (userChoice == 3 && lawan == 2) -> // Kertas menang lawan Batu
                    "$namaUser menang!"
                else -> "$namaUser kalah!"
            }

            hasilKamu.text = "$namaUser Memilih $userChoiceString"
            hasilMusuh.text = "Pilihan lawan: ${pilihanList[lawan - 1]}"
            // Tampilkan hasil
            hasilText.text = "$hasil"
        }
    }
}
