package romo.manuel.examen1_romomanuel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.format
import kotlin.text.toDoubleOrNull

/**
 * Alumno: Manuel Romo López
 * ID: 00000253080
 *
 * Fecha: 05/02/2026
 */

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

        // Inicialización de views.
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

        // Acciones realizadas para obtener el total a pagar y lo que le corresponde a cada persona.
        btnTotalSinPropina.setOnClickListener {

            // Se obtienen los valores numéricos de lo ingresado por el usuario.
            val totalCuentaString = etValorTotalCuenta.text.toString().trim()
            val cantidadPersonasString = etValorTotalPersonas.text.toString().trim()

            val totalCuentaDouble: Double? = totalCuentaString.toDoubleOrNull()
            val cantidadPersonasInt: Int? = cantidadPersonasString.toIntOrNull()

            // Si los datos son válidos, se obtiene y se muestra el total por persona.
            if(totalCuentaDouble != null && cantidadPersonasInt != null && totalCuentaDouble > 0 && cantidadPersonasInt > 0){

                val totalPorPersona :Double = totalCuentaDouble / cantidadPersonasInt

                tvTotal.text = "$ ${String.format("%.2f", totalPorPersona)}"

            } else{
                // Si algún dato no es válido, se actualiza el total y se muestra un mensaje informativo.
                tvTotal.text = "$ 0.00"
                mostrarMensajeTotalInvalido(totalCuentaDouble, cantidadPersonasInt)
            }
        }

        // Acciones que se realizan para cambiar a la activity que realiza cálculos considerando propina.
        btnTotalConPropina.setOnClickListener {

            // Se obtiene los valores numéricos de los datos del usuario.
            val totalCuentaString = etValorTotalCuenta.text.toString()
            val cantidadPersonasString = etValorTotalPersonas.text.toString()

            val totalCuentaDouble: Double? = totalCuentaString.toDoubleOrNull()
            val cantidadPersonasInt: Int? = cantidadPersonasString.toIntOrNull()

            // Si los datos son válidos, se manda el total de cuenta y la cantidad de personas a la siguiente
            // activity
            if(totalCuentaDouble != null && cantidadPersonasInt != null  && totalCuentaDouble > 0  && cantidadPersonasInt > 0){

                val intent = Intent(this, PropinaActivity::class.java)
                intent.putExtra("TOTAL_CUENTA", totalCuentaDouble)
                intent.putExtra("CANTIDAD_PERSONAS", cantidadPersonasInt)
                startActivity(intent)

            } else{
                // Si algún dato es inválido, se limpia el total previo y se muestra un mensaje informativo.
                tvTotal.text = "$ 0.00"
                mostrarMensajeTotalInvalido(totalCuentaDouble, cantidadPersonasInt)

            }

        }


    }

    // Función auxiliar para determinar el mensaje a mostrar, según qué datos sean inváldos.
    fun mostrarMensajeTotalInvalido(totalCuenta :Double?, cantidadPersonas :Int?){
        if((totalCuenta == null || totalCuenta <= 0) && (cantidadPersonas == null || cantidadPersonas <= 0 )){
            Toast.makeText(this, "Debe ingresar un total y una cantidad de personas válida.", Toast.LENGTH_SHORT).show()
        } else if(totalCuenta == null || totalCuenta <= 0){
            Toast.makeText(this, "Debe ingresar un total de cuenta válido.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Debe ingresar una cantidad de personas válida.", Toast.LENGTH_SHORT).show()
        }
    }

}
