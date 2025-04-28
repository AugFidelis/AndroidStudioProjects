package br.com.puc.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IMCActivity : AppCompatActivity() {

    lateinit var btnCalcularImc: Button
    lateinit var etPeso: EditText
    lateinit var etAltura: EditText
    lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)

        // referencias e instanciar as VIEWS
        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        btnCalcularImc = findViewById(R.id.btnCalcularIMC)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcularImc.setOnClickListener{
            //calcular o IMC
            val imc = etPeso.text.toString().toDouble() /
            Math.pow(etAltura.text.toString().toDouble(),2.0)
            tvResultado.setText("Seu imc é $imc")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}