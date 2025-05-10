package com.mobile.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var ce: Button
    private lateinit var erase: Button
    private lateinit var mMore: Button
    private lateinit var mMinus: Button
    private lateinit var ms: Button
    private lateinit var secondDisplay: Button
    private lateinit var pi: Button
    private lateinit var e: Button
    private lateinit var xSquare: Button
    private lateinit var oneBaseX: Button
    private lateinit var barX: Button
    private lateinit var exp: Button
    private lateinit var mod: Button
    private lateinit var squareRoot: Button
    private lateinit var parentesesLeft: Button
    private lateinit var parentesesRight: Button
    private lateinit var nExclamacao: Button
    private lateinit var divide: Button
    private lateinit var xPowerY: Button
    private lateinit var multiply: Button
    private lateinit var tenPowerX: Button
    private lateinit var minus: Button
    private lateinit var log: Button
    private lateinit var plus: Button
    private lateinit var ln: Button
    private lateinit var plusMinus: Button
    private lateinit var dot: Button
    private lateinit var equal: Button
    private lateinit var display: TextView
    private lateinit var displayRef: TextView

    private lateinit var numberButtons: Array<Button>

    private var currentDisplayText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ce = findViewById(R.id.ce)
        erase = findViewById(R.id.erase)
        mMore = findViewById(R.id.MMore)
        mMinus = findViewById(R.id.MMinus)
        ms = findViewById(R.id.Ms)
        secondDisplay = findViewById(R.id.secondDisplay)
        pi = findViewById(R.id.PI)
        e = findViewById(R.id.e)
        xSquare = findViewById(R.id.xSquare)
        oneBaseX = findViewById(R.id.oneBaseX)
        barX = findViewById(R.id.barX)
        exp = findViewById(R.id.exp)
        mod = findViewById(R.id.mod)
        squareRoot = findViewById(R.id.squareRoot)
        parentesesLeft = findViewById(R.id.parentesesLeft)
        parentesesRight = findViewById(R.id.parentesesRight)
        nExclamacao = findViewById(R.id.nExclamacao)
        divide = findViewById(R.id.divide)
        xPowerY = findViewById(R.id.xPowerY)
        multiply = findViewById(R.id.multiply)
        tenPowerX = findViewById(R.id.tenPowerX)
        minus = findViewById(R.id.minus)
        log = findViewById(R.id.log)
        plus = findViewById(R.id.plus)
        ln = findViewById(R.id.ln)
        plusMinus = findViewById(R.id.plusMinus)
        dot = findViewById(R.id.dot)
        equal = findViewById(R.id.equal)
        display = findViewById(R.id.display)
        displayRef = findViewById(R.id.displayRef)

        numberButtons = arrayOf(
            findViewById(R.id.zero),
            findViewById(R.id.one),
            findViewById(R.id.two),
            findViewById(R.id.three),
            findViewById(R.id.four),
            findViewById(R.id.five),
            findViewById(R.id.six),
            findViewById(R.id.seven),
            findViewById(R.id.eight),
            findViewById(R.id.nine)
        )

        setUpListenners()

//        val expressao = "8*e"
//        val resultado = ExpressionBuilder(expressao).build().evaluate()
//        displayRef.text =  resultado.toString();

    }

    private fun setUpListenners() {
        for ((index, button) in numberButtons.withIndex()) {
            button.setOnClickListener {
                setNumero(display, index.toString());
            }
        }

        ce.setOnClickListener{
            display.text = "";
            currentDisplayText = "";
            displayRef.text = "";
        }

        erase.setOnClickListener{
            display.text = display.text.dropLast(1)
        }


    }

    @SuppressLint("SetTextI18n")
    private fun setNumero(display: TextView?, s: String) {

        if (display == null) {
            return;
        }


        if(display.text.equals("0")){
            display.text = s;
            displayRef.text = s;
//            currentDisplayText = display.text as String;
        }else if(display.text.length > 11) {
            return
        }else{
            display.text = display.text.toString() + s
            displayRef.text = displayRef.text.toString() + s;
//            currentDisplayText = display.text as String;
        }
    }
}

