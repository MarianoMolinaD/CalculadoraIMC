package com.portafoliowebmariano.calculadoraimc
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var cvMen: CardView
    private lateinit var cvWomen: CardView
    private lateinit var tvAltura: TextView
    private lateinit var tvPeso: TextView
    private lateinit var tvEdad: TextView
    private lateinit var btnSumarPeso: AppCompatButton
    private lateinit var btnRestarPeso: AppCompatButton
    private lateinit var btnCalcular: AppCompatButton
    private lateinit var sbAltura: RangeSlider

    var WomanIsSelected: Boolean = false
    var MenIsSelected: Boolean = false

    companion object {
        const val IMC_KEY = "IMC_RESULT"
        const val GENDER_KEY = "IMC_GENDER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_imc)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundCard)
        }

        initComponent()
        initListener()

    }

    private fun initComponent() {
        cvMen = findViewById(R.id.cvMen)
        cvWomen = findViewById(R.id.cvWomen)
        tvAltura = findViewById(R.id.tvAltura)
        tvPeso = findViewById(R.id.tvPeso)
        btnSumarPeso = findViewById(R.id.btnSumarPeso)
        btnRestarPeso = findViewById(R.id.btnRestarPeso)
        btnCalcular = findViewById(R.id.btnCalcular)
        sbAltura = findViewById(R.id.sbAltura)

    }

    private fun initListener() {
        btnSumarPeso.setOnClickListener {
            val Actual = tvPeso.text.toString().toInt()
            val Resultado = Actual + 1
            tvPeso.text = Resultado.toString()
        }
        btnRestarPeso.setOnClickListener {
            val Actual = tvPeso.text.toString().toInt()
            val Resultado = Actual - 1
            tvPeso.text = Resultado.toString()
        }
        sbAltura.addOnChangeListener { _, value, _ ->
            var df = DecimalFormat("#.##")
            val resultado = df.format(value)
            tvAltura.text = resultado
        }
        cvMen.setOnClickListener {
            MenIsSelected = true
            WomanIsSelected = false
            isSelected()
        }
        cvWomen.setOnClickListener {
            WomanIsSelected = true
            MenIsSelected = false
            isSelected()
        }
        btnCalcular.setOnClickListener {
            calcularIMC()
        }

    }

    private fun isSelected() {
        if (MenIsSelected) {
            cvMen.setCardBackgroundColor(resources.getColor(R.color.BackgroundCardSelected))
            cvWomen.setCardBackgroundColor(resources.getColor(R.color.BackgroundCard))
            WomanIsSelected = false
        } else {
            cvWomen.setCardBackgroundColor(resources.getColor(R.color.BackgroundCardSelected))
            cvMen.setCardBackgroundColor(resources.getColor(R.color.BackgroundCard))
            MenIsSelected = false
        }
    }

    private fun calcularIMC() {

        if(WomanIsSelected || MenIsSelected){
            var Peso = tvPeso.text.toString().toDouble()
            var Estatura = tvAltura.text.toString().toDouble()
            var EstaturaMetros = Estatura/ 100
            var MetrosCuadrados = EstaturaMetros * EstaturaMetros
            var MasaCorporal = Peso / MetrosCuadrados
            var Genero : Double
            if(MenIsSelected){
                Genero = 0.1

            }else{
                Genero = 0.2
            }

            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra(IMC_KEY, MasaCorporal)
            intent.putExtra(GENDER_KEY,Genero)

            //Abrir activity resultado
            startActivity(intent)

        }else{
            showSnackbar(

                findViewById(android.R.id.content),
                "Debe seleccionar el Genero"
            )
        }

    }
    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }


}