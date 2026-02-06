package romo.manuel.examen1_romomanuel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.text.format
import kotlin.text.toDoubleOrNull

/**
 * Alumno: Manuel Romo López
 * ID: 00000253080
 *
 * Fecha: 05/02/2026
 */

class PropinaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_propina)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Se obtiene el total de la cuenta y la cantidad de personas recibidas en el intent.
        val totalCuenta = intent.getDoubleExtra("TOTAL_CUENTA", 0.0)
        val cantidadPersonas = intent.getIntExtra("CANTIDAD_PERSONAS", 0)

        // Se verifica que las cantidades se hayan enviado correctamente.
        if(totalCuenta <= 0 || cantidadPersonas <= 0){
            Toast.makeText(this, "Error al cargar la información.", Toast.LENGTH_SHORT).show()
        }

        // Se inicializan las views del layout.
        val tvPropina = findViewById<TextView>(R.id.tvPropina)
        val etPropina = findViewById<EditText>(R.id.etPropina)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvTotalPropina = findViewById<TextView>(R.id.tvTotalPropina)
        val tvPropinaPersonaValor = findViewById<TextView>(R.id.tvPropinaPersonaValor)
        val tvPorPersonaTotalPropina = findViewById<TextView>(R.id.tvPorPersonaTotalPropina)
        val tvPorPersonaPropinaPersonaValor = findViewById<TextView>(R.id.tvPorPersonaPropinaPersonaValor)

        tvPropina.setText("Porcentaje de propina: ")
        btnCalcular.setText("Calcular")
        tvTotalPropina.text = ""
        tvPropinaPersonaValor.text = ""
        tvPorPersonaTotalPropina.text = ""
        tvPorPersonaPropinaPersonaValor.text = ""

        // Cálculo de pago por persona con total más propina.
        btnCalcular.setOnClickListener {

            // Se obtiene la propina ingresada.
            val propinaPorcentaje : Double? = etPropina.text.toString().trim().toDoubleOrNull()

            // Se suma la propina al total y se calcula lo que corresponde a cada persona, si la
            // propina es válida.
            if(propinaPorcentaje != null && propinaPorcentaje >= 0){

                // Se calcula el total con propina.
                val totalPropina :Double = totalCuenta * (propinaPorcentaje / 100)
                val totalConPropina :Double = totalCuenta + totalPropina

                // Se calcula lo que pagrá cada persona, y cuánto le corresponde de propina a cada una.
                val totalPorPersona :Double = totalConPropina / cantidadPersonas
                val totalPropinaPorPersona = totalPropina / cantidadPersonas

                // Actualización de views mostrando los resultados.
                tvTotalPropina.text = "Total: $ ${String.format("%.2f", totalPorPersona)}"
                tvPropinaPersonaValor.text = "Propina de $ ${String.format("%.2f", totalPropinaPorPersona)}"

                tvPorPersonaPropinaPersonaValor.text = "por persona"
                tvPorPersonaTotalPropina.text = "por persona"

            } else{
                // Se muestra un mensaje al usuario si el porcentaje tiene formato inválido.
                // Se limpian los resultados previos.
                Toast.makeText(this, "Debe ingresar un porcentaje válido.", Toast.LENGTH_SHORT).show()
                tvTotalPropina.text = ""
                tvPropinaPersonaValor.text = ""
                tvPorPersonaTotalPropina.text = ""
                tvPorPersonaPropinaPersonaValor.text = ""
            }

        }


    }
}