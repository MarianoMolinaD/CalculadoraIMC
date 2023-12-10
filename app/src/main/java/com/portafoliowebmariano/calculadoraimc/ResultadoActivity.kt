package com.portafoliowebmariano.calculadoraimc
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.portafoliowebmariano.calculadoraimc.IMCActivity.Companion.GENDER_KEY
import com.portafoliowebmariano.calculadoraimc.IMCActivity.Companion.IMC_KEY
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class ResultadoActivity : AppCompatActivity() {
    private lateinit var tvEstado: TextView
    private lateinit var tvResultado: TextView
    private lateinit var tvDescipcion: TextView
    private lateinit var btnRecualcular: AppCompatButton
    private lateinit var cvContenedor: CardView
    private lateinit var cvImg : CardView
    private lateinit var clFondo: ConstraintLayout
    private lateinit var ivGender : ImageView
    //Colores

    val colorBajo = R.color.BackgroundBajo
    val colorBajoT =  R.color.BackgroundTransparenteBajo

    val colorNormal = R.color.BackgroundNormal
    val colorNormalT = R.color.BackgroundTransparenteNormal

    var colorObecidad = R.color.BackgroundObecidad
    var colorObecidadT = R.color.BackgroundTransparenteObecidad

    var colorSobrePeso = R.color.BackgroundSobrePeso
    var colorSobrePesoT = R.color.BackgroundTransparenteSobrePeso

    var colorObecidadS = R.color.BackgroundObecidadSevera
    var colorObecidadST = R.color.BackgroundTransparenteObecidadSevera

    val imgMen = R.drawable.imcmen
    val imgWoman = R.drawable.imcwoman


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        val resultGender : Double = intent.extras?.getDouble(GENDER_KEY) ?: -1.0


        supportActionBar?.hide()
        setContentView(R.layout.activity_resultado)


        initComponent()
        initUI(result)
        initListener()
        MenWomen(resultGender)
    }

    private fun initComponent() {
        tvEstado = findViewById(R.id.tvEstado)
        tvResultado = findViewById(R.id.tvResultado)
        tvDescipcion = findViewById(R.id.tvDescripcion)
        btnRecualcular = findViewById(R.id.btnReCalcular)
        cvContenedor = findViewById(R.id.cvContenedor)
        cvImg = findViewById(R.id.cvImg)
        clFondo = findViewById(R.id.clFondo)
        ivGender = findViewById(R.id.imgImc)
    }

    private fun initListener() {
        btnRecualcular.setOnClickListener {
            onBackPressed()

        }
    }

    private fun initUI(result: Double) {
        var df = DecimalFormat("#.##")
        var masaCorporal = df.format(result)
        tvResultado.text = masaCorporal.toString()
        when (result) {
            in 0.00..18.50 -> {//Baajo peso
                tvEstado.text = "Peso Bajo"
                tvDescipcion.text = getString(R.string.Bajo)
                cvContenedor.setBackgroundResource(colorBajo)
                cvImg.setBackgroundResource(colorBajo)
                clFondo.setBackgroundResource(colorBajoT)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundTransparenteBajo)
                }


            }

            in 18.51..24.99 -> {//Peso normal
                tvEstado.text = "Peso Normal"
                tvDescipcion.text = getString(R.string.Normal)
                cvContenedor.setBackgroundResource(colorNormal)
                cvImg.setBackgroundResource(colorNormal)
                clFondo.setBackgroundResource(colorNormalT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundTransparenteNormal)
                }
            }

            in 25.00..29.99 -> { //Sobrepeso
                tvEstado.text = "Sobre Peso"
                tvDescipcion.text = getString(R.string.SobrePeso)
                cvContenedor.setBackgroundResource(colorSobrePeso)
                cvImg.setBackgroundResource(colorSobrePeso)
                clFondo.setBackgroundResource(colorSobrePesoT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundTransparenteSobrePeso)
                }

            }

            in 30.00..39.99 -> { //Obecidad
                tvEstado.text = "Obesidad"
                tvDescipcion.text = getString(R.string.Obecidad)
                cvContenedor.setBackgroundResource(colorObecidad)
                cvImg.setBackgroundResource(colorObecidad)
                clFondo.setBackgroundResource(colorObecidadT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundTransparenteObecidad)
                }

            }
            in 40.00..99.00 -> { //Obecidad Severa
                tvEstado.text = "Obesidad Severa"
                tvDescipcion.text = getString(R.string.ObecidadSevera)
                cvContenedor.setBackgroundResource(colorObecidadS)
                cvImg.setBackgroundResource(colorObecidadS)
                clFondo.setBackgroundResource(colorObecidadST)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.BackgroundTransparenteObecidad)
                }

            }

            else -> {//Error
                tvResultado.text = "Error"
                tvResultado.text = "Error"
                tvDescipcion.text = "Error"
            }
        }
    }
    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
    fun MenWomen(gender:Double){
        if(gender == 0.1){

            ivGender.setImageResource(imgMen)

        }else{

            ivGender.setImageResource(imgWoman)

        }
    }

}
