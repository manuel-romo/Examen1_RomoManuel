package romo.manuel.examen1_romomanuel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java
import kotlin.text.format
import kotlin.text.toDoubleOrNull


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        val tvTotalCuenta = findViewById<TextView>(R.id.tvTotalCuenta)
        val etValorTotalCuenta = findViewById<EditText>(R.id.etValorTotalCuenta)
        val tvTotalPersonas = findViewById<TextView>(R.id.tvTotalPersonas)
        val etValorTotalPersonas = findViewById<EditText>(R.id.etValorTotalPersonas)
        val btnTotalSinPropina = findViewById<Button>(R.id.btnTotalSinPropina)
        val btnTotalConPropina = findViewById<Button>(R.id.btnTotalConPropina)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvPorPersona = findViewById<TextView>(R.id.tvPorPersona)

        tvTitulo.text = "Divide y paga"
        tvTotalCuenta.text = "Total de la cuenta"
        tvTotalPersonas.text = "Total de personas"
        btnTotalSinPropina.text = "Total sin propina"
        btnTotalConPropina.text = "Total con propina"
        tvTotal.text = "$ 0.00"
        tvPorPersona.text = "por persona"

        btnTotalSinPropina.setOnClickListener {

            val cantidadIngresada = etValorTotalCuenta.text.toString()
            val personasIngresadas = etValorTotalPersonas.text.toString()

            val cantidadIngresadaDouble :Double? = cantidadIngresada.toDoubleOrNull()
            val personasIngresadasDouble :Double? = personasIngresadas.toDoubleOrNull()

            if(cantidadIngresadaDouble != null && personasIngresadasDouble != null){
                val totalPorPersona :Double = cantidadIngresadaDouble / personasIngresadasDouble

                val totalPorPersonaFormato = String.format("%.2f", totalPorPersona)
                tvTotal.text = "$ ${totalPorPersonaFormato}"
            } else{
                tvTotal.text = "$ 0.00"
            }


        }

        btnTotalConPropina.setOnClickListener {

            val totalCuenta = etValorTotalCuenta.text.toString()
            val cantidadPersonas = etValorTotalPersonas.text.toString()


            val totalCuentaDouble :Double? = totalCuenta.toDoubleOrNull()
            val cantidadPersonasDouble :Double? = cantidadPersonas.toDoubleOrNull()


            if(totalCuentaDouble != null && cantidadPersonasDouble != null  && totalCuentaDouble > 0  && cantidadPersonasDouble > 0){
                val totalPorPersona :Double = totalCuentaDouble / cantidadPersonasDouble
                val intent = Intent(this, PropinaActivity::class.java)

                intent.putExtra("TOTAL_CUENTA", totalPorPersona)
                intent.putExtra("CANTIDAD_PERSONAS", cantidadPersonasDouble)
                startActivity(intent)


            } else{
                tvTotal.text = "$ 0.00"
            }

        }


    }

}
