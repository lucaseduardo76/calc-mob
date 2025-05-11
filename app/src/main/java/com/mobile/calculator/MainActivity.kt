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
import kotlin.math.exp


class MainActivity : AppCompatActivity() {

    private lateinit var ce: Button
    private lateinit var erase: Button
    private lateinit var mc: Button
    private lateinit var mr: Button
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
    private lateinit var nFatorial: Button
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

    private var isSimbolSet = false;
    private var isSpecialNumberActivate = false;
    private var expActive = false;
    private var expNumber = "";
    private var numberParentesesOpen = 0;
    private var numberOnMemory = 0f;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ce = findViewById(R.id.ce)
        erase = findViewById(R.id.erase)
        mMore = findViewById(R.id.MMore)
        mMinus = findViewById(R.id.MMinus)
        ms = findViewById(R.id.Ms)
        mc = findViewById(R.id.MC)
        mr = findViewById(R.id.MR)
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
        nFatorial = findViewById(R.id.nExclamacao)
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


    }

    @SuppressLint("SetTextI18n")
    private fun setUpListenners() {
        for ((index, button) in numberButtons.withIndex()) {
            button.setOnClickListener {
                setNumero(display, index.toString());
            }
        }

        ce.setOnClickListener {
            display.text = "0";
            displayRef.text = "";
        }

        erase.setOnClickListener {
            display.text = display.text.dropLast(1)

            if (display.text.isEmpty()) {
                isSimbolSet = true;
            }
        }


        plus.setOnClickListener {
            setSimbol("+", display, displayRef);
        }

        minus.setOnClickListener {
            setSimbol("-", display, displayRef);
        }

        multiply.setOnClickListener {
            setSimbol("*", display, displayRef);
        }

        divide.setOnClickListener {
            setSimbol("/", display, displayRef);
        }

        equal.setOnClickListener {

            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }

            if (display.text.isNotEmpty()) {
                displayRef.text = displayRef.text.toString() + display.text.toString();
            }




            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }


            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        pi.setOnClickListener{
            display.text = "3.1415926535897";
            if (displayRef.text.toString().endsWith("=")) {
                displayRef.text = "";
                isSimbolSet = false;
            }

            isSpecialNumberActivate = true;
        }

        e.setOnClickListener{
            display.text = "2.7182818284590";
            if (displayRef.text.toString().endsWith("=")) {
                displayRef.text = "";
                isSimbolSet = false;
            }

            isSpecialNumberActivate = true;
        }

        xSquare.setOnClickListener{

            setExpNumber();

            println(display.text.toString())
            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef(display.text.toString() + "^2" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        tenPowerX.setOnClickListener{

            setExpNumber();

            println(display.text.toString())
            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef( "10^${display.text.toString()}" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        log.setOnClickListener{

            setExpNumber();

            println(display.text.toString())
            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef( "log10(${display.text.toString()})" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        ln.setOnClickListener{

            setExpNumber();

            println(display.text.toString())
            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef( "log(${display.text.toString()})" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        oneBaseX.setOnClickListener {

            setExpNumber();

            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef("1/${display.text.toString()}" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        barX.setOnClickListener {

            setExpNumber();

            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef("abs(${display.text.toString()})" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }


        exp.setOnClickListener {

            setExpNumber()

            if (display.text.isNotEmpty()) {
                expNumber = display.text.toString();
                display.text = "${display.text.toString()} * 10^";
                expActive = true;
            }

        }


        mod.setOnClickListener {

            setExpNumber()

            if (display.text.isNotEmpty()) {
                expNumber = display.text.toString();
                display.text = "${display.text.toString()} % ";
                expActive = true;
            }

        }

        xPowerY.setOnClickListener {

            setExpNumber()

            if (display.text.isNotEmpty()) {
                expNumber = display.text.toString();
                display.text = "${display.text.toString()} ^ ";
                expActive = true;
            }

        }




        squareRoot.setOnClickListener {

            setExpNumber();

            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef("sqrt(${display.text.toString()})" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        parentesesLeft.setOnClickListener {
            pushToDisplayRef("${display.text}(")
            display.text = ""
            numberParentesesOpen++;
        }

        parentesesRight.setOnClickListener {

            if(numberParentesesOpen > 0){
                pushToDisplayRef("${display.text})")
                numberParentesesOpen--;
            }

        }

        nFatorial.setOnClickListener {

            setExpNumber();

            if(displayRef.text.endsWith("=")){
                displayRef.text = ""
            }


            if (display.text.isNotEmpty()) {
                pushToDisplayRef("fact(${display.text.toString()})" )
            }

            try {
                display.text = calculate()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            displayRef.text = displayRef.text.toString() + "=";
            isSimbolSet = false;

        }

        plusMinus.setOnClickListener {
            setExpNumber()
            val currentValue = display.text.toString().toFloatOrNull() ?: 0f
            display.text = (currentValue * -1).toString()
        }

        dot.setOnClickListener {
            val currentText = display.text.toString()


            if (currentText.toFloatOrNull()?.let { it == it.toInt().toFloat() } == true && !currentText.contains(".")) {
                display.text = "$currentText."
            }
        }


        mc.setOnClickListener {
            numberOnMemory = 0f;
        }

        mr.setOnClickListener {
            display.text = "${numberOnMemory}";
        }

        mMore.setOnClickListener {
            setExpNumber()

            val currentValue = display.text.toString().toFloatOrNull() ?: 0f
            numberOnMemory += currentValue
        }

        mMinus.setOnClickListener {
            setExpNumber()

            val currentValue = display.text.toString().toFloatOrNull() ?: 0f
            numberOnMemory -= currentValue
        }

        ms.setOnClickListener {
            setExpNumber()

            val currentValue = display.text.toString().toFloatOrNull() ?: 0f
            numberOnMemory = currentValue
        }
    }

    private fun setExpNumber() {
        if(expActive){
            display.text = expNumber;
            expActive = false;
        }
    }

    @SuppressLint("SetTextI18n")
    private fun pushToDisplayRef(newScipt: String) {
        displayRef.text = displayRef.text.toString() + " " + newScipt;
        cleanDisplay();
    }

    private fun cleanDisplay() {
        display.text = ""
    }

    private fun calculate(): CharSequence {
        if(numberParentesesOpen > 0){
            for (i in 1..numberParentesesOpen) {
                pushToDisplayRef(")")
            }
        }
        val expressao = displayRef.text.toString();

        val resultado = ExpressionBuilder(expressao)
            .function(criarFuncaoFatorial())
            .build()
            .evaluate()

        return resultado.toString();
    }

    @SuppressLint("SetTextI18n")
    private fun setSimbol(s: String, display: TextView, displayRef: TextView) {
        if (display.text.toString() == "0")
            return

        if (expActive) {
            return
        }

        if (displayRef.text.toString().endsWith("=")) {
            displayRef.text = display.text.toString() + " " + s
            display.text = ""
            isSimbolSet = true
            return
        }

        if (!isSimbolSet && display.text.isNotEmpty()) {
            displayRef.text = displayRef.text.toString() + " " + display.text.toString() + " " + s
        } else {
            displayRef.text = displayRef.text.dropLast(1).toString()
            displayRef.text = displayRef.text.toString() + s
        }

        if(isSpecialNumberActivate){
            isSpecialNumberActivate = false;
        }


        display.text = ""
        isSimbolSet = true
    }


    @SuppressLint("SetTextI18n")
    private fun setNumero(display: TextView?, s: String) {

        if (display == null) {
            return;
        }

        if (displayRef.text.toString().endsWith("=") || isSpecialNumberActivate) {
            display.text = "";
            displayRef.text = "";
            isSimbolSet = false;

            isSpecialNumberActivate = false;
        }

        if (display.text.equals("0")) {
            display.text = s;
        } else if (display.text.length > 22) {
            return
        } else {
            display.text = display.text.toString() + s
        }

        isSimbolSet = false;
    }

    private fun criarFuncaoFatorial(): net.objecthunter.exp4j.function.Function {
        return object : net.objecthunter.exp4j.function.Function("fact", 1) {
            override fun apply(vararg args: Double): Double {
                val n = args[0].toInt()

                require(n >= 0) { "Fatorial só é definido para inteiros não-negativos." }

                var resultado = 1L
                for (i in 2..n) {
                    resultado *= i
                }

                return resultado.toDouble()
            }
        }
    }
}


