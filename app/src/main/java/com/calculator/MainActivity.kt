package com.calculator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val n1 = findViewById<TextView>(R.id.n1)
        val n2 = findViewById<TextView>(R.id.n2)
        val n3 = findViewById<TextView>(R.id.n3)
        val n4 = findViewById<TextView>(R.id.n4)
        val n5 = findViewById<TextView>(R.id.n5)
        val n6 = findViewById<TextView>(R.id.n6)
        val n7 = findViewById<TextView>(R.id.n7)
        val n8 = findViewById<TextView>(R.id.n8)
        val n9 = findViewById<TextView>(R.id.n9)
        val n0 = findViewById<TextView>(R.id.n0)
        val dote = findViewById<TextView>(R.id.dote)

        val brecket1 = findViewById<TextView>(R.id.brecket1)
        val brecket2 = findViewById<TextView>(R.id.brecket2)

        val plus = findViewById<TextView>(R.id.plus)
        val minus = findViewById<TextView>(R.id.minus)
        val divine = findViewById<TextView>(R.id.divine)
        val multiply = findViewById<TextView>(R.id.multiply)

        val ac = findViewById<TextView>(R.id.ac)
        val equal = findViewById<TextView>(R.id.equal)
        val back = findViewById<TextView>(R.id.back)

        n1.setOnClickListener {
            setTextFields("1")
        }
        n2.setOnClickListener {
            setTextFields("2")
        }
        n3.setOnClickListener {
            setTextFields("3")
        }
        n4.setOnClickListener {
            setTextFields("4")
        }
        n5.setOnClickListener {
            setTextFields("5")
        }
        n6.setOnClickListener {
            setTextFields("6")
        }
        n7.setOnClickListener {
            setTextFields("7")
        }
        n8.setOnClickListener {
            setTextFields("8")
        }
        n9.setOnClickListener {
            setTextFields("9")
        }
        n0.setOnClickListener {
            setTextFields("0")
        }
        dote.setOnClickListener { setTextFields(",") }

        brecket1.setOnClickListener {
                setTextFields("(")
        }
        brecket2.setOnClickListener {
        setTextFields(")")
        }
        plus.setOnClickListener {
            setTextFields("+")
        }
        minus.setOnClickListener {
            setTextFields("-")
        }
        divine.setOnClickListener {
            setTextFields("÷")
        }
        multiply.setOnClickListener {
            setTextFields("*")
        }
        ac.setOnClickListener {
            clear()
        }
        back.setOnClickListener { back() }

        equal.setOnClickListener {
            var math_operation = findViewById<TextView>(R.id.math_operation).text.toString()
            math_operation = math_operation.replace(',','.').replace('÷','/')
            try {
                val ex = ExpressionBuilder(math_operation).build()
                val res = ex.evaluate()
                val lonRes = res.toLong()
                if (lonRes.toDouble() == res)
                    findViewById<TextView>(R.id.result_text).text = lonRes.toString()
                else
                    findViewById<TextView>(R.id.result_text).text = String.format("%.3f",res)
            }
            catch (e:Exception){
                Log.d("Error","сообщение:${e.message}")
                findViewById<TextView>(R.id.result_text).text = "Ошибка!"
            }
        }
    }


    fun setTextFields(str:String) {
        var math_operation: TextView = findViewById<TextView>(R.id.math_operation)
        var res: TextView = findViewById<TextView>(R.id.result_text)

        if (math_operation.text.isEmpty())
            math_operation.append(str)
        else {
            val lastCharacter =
                math_operation.text.toString().get(math_operation.text.length - 1).toString()
            if (res.text.isEmpty()) {
                if (str == "*" || str == "÷" || str == "+" || str == "-") {
                    if (lastCharacter != "*" && lastCharacter != "÷" && lastCharacter != "-" && lastCharacter != "+")
                        math_operation.append(str)
                }
                    else math_operation.append(str)
            }
            else {
                if (str != "*" && str != "÷" && str != "+" && str != "-") {
                    clear()
                    math_operation.append(str)
                }
                else {
                    if (lastCharacter != "*" && lastCharacter != "÷" && lastCharacter != "-" && lastCharacter != "+") {
                        math_operation.text = res.text
                        res.text=""
                        math_operation.append(str)
                    }
                }
            }
        }
    }
    fun back(){
        findViewById<TextView>(R.id.result_text).text = ""
        var math_operation: TextView =  findViewById<TextView>(R.id.math_operation)
        val str = math_operation.text.toString()
        if (str.isNotEmpty()){
            math_operation.text= str.substring(0,math_operation.text.length-1)
        }


    }

    fun clear(){
        var math_operation: TextView =  findViewById<TextView>(R.id.math_operation)
        var res: TextView = findViewById<TextView>(R.id.result_text)
        math_operation.text = ""
        res.text= ""
    }
}