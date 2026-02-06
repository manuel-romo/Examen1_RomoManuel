package romo.manuel.examen1_romomanuel

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import kotlin.text.format
import kotlin.text.toDoubleOrNull

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

        val totalCuenta = intent.getDoubleExtra("TOTAL_CUENTA", 0.0)
        val cantidadPersonas = intent.getDoubleExtra("CANTIDAD_PERSONAS", 0.0)

        val tvPropina = findViewById<TextView>(R.id.tvPropina)
        val etPropina = findViewById<TextView>(R.id.etPropina)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvTotalPropina = findViewById<TextView>(R.id.tvTotalPropina)
        val tvPropinaPersona = findViewById<TextView>(R.id.tvPropinaPersona)
        val tvPropinaPersonaValor = findViewById<TextView>(R.id.tvPropinaPersonaValor)


        tvPropina.setText("Porcentaje de propina: ")
        btnCalcular.setText("Calcular")
        tvPropinaPersona.text = ""

        btnCalcular.setOnClickListener {


            val propinaPorcentaje : Double? = etPropina.text.toString().toDoubleOrNull()

            if(propinaPorcentaje != null && propinaPorcentaje >= 0){
                if(totalCuenta != null && cantidadPersonas != null && etPropina != null && propinaPorcentaje != null){

                    val propina :Double = totalCuenta * (propinaPorcentaje / 100)
                    val totalSinPropina :Double = totalCuenta - propina

                    val totalPorPersona :Double = totalSinPropina.div(cantidadPersonas)
                    val totalPropinaPorPersona = propina.div(cantidadPersonas)

                    val totalPorPersonaFormato = String.format("%.2f", totalPorPersona)
                    val totalPropinaPorPersonaFormato = String.format("%.2f", totalPropinaPorPersona)

                    tvTotalPropina.text = "$ ${totalPorPersonaFormato}"
                    tvPropinaPersonaValor.text = "$ ${totalPropinaPorPersonaFormato}"

                    tvPropinaPersona.text = "Propina a pagar: "

                } else{
                    tvTotalPropina.text = "$ 0.00"
                }
            }

        }

    }
}