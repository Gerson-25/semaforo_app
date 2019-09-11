package com.example.ejemploapp
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import kotlin.math.log

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val mHandlerThread= Handler()
    lateinit var mThread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myTxt = findViewById<TextView>(R.id.myTxt)
        val boton1:Button = findViewById(R.id.btnActivar)
        var layoutFondo = findViewById<LinearLayout>(R.id.fondo)
        var start = false
        var currentLight = DESACTIVADO

        /*var detenerse = resources.getColor(R.color.stop)
        var precaucion = resources.getColor(R.color.watch_out)
        var coontinuar = resources.getColor(R.color.go)
        var desactivado = resources.getColor(R.color.desactivado)*/
        var colores = arrayOf(
            R.color.stop,
            R.color.watch_out,
            R.color.go,
            R.color.desactivado
        )
        var statusText = arrayOf(
            "DESACTIVADO",
            "DETENGASE",
            "PRECAUCION",
            "CONTINUE"
        )


        fun changeColor(colorIndex: Int){
            when(colorIndex){
                DESACTIVADO -> Log.d("SEMAFORO", "DESACTIVADO")
                DETENERSE -> Log.d("SEMAFORO", "DETENERSE")
                PRECAUCION -> Log.d("SEMAFORO", "PRECAUCION")
                CONTINUAR -> Log.d("SEMAFORO", "CONTINUAR")
            }
            val resourceColor = colores[colorIndex]
            layoutFondo.setBackgroundColor(ContextCompat.getColor(this,resourceColor))
        }

        fun changeText(colorIndex: Int){
            val text = when(colorIndex){
                DETENERSE -> "ALTO"
                PRECAUCION -> "PRECAUCION"
                CONTINUAR -> "CONTINUE"
                else -> "DESACTIVADO"
            }
            myTxt.text =text
        }

        fun changeLight(color: Int){
            if (start){
                btnActivar.text = "DETENER"
                changeColor(color)
                changeText(color)
                currentLight = if (currentLight < CONTINUAR) currentLight + 1 else DETENERSE
                mHandlerThread.postDelayed({
                    runOnUiThread{
                        changeLight(currentLight)
                    }
                }, DELAY)
            }
        }

        fun startLight(){
            start = true
            currentLight = DETENERSE
            changeLight(currentLight)
        }
        fun stopLight(){
            start = false
            btnActivar.text = "INICIAR"
            changeColor(DESACTIVADO)
            changeText(DESACTIVADO)
        }
        btnActivar!!.setOnClickListener {
            if(!start){
                startLight()
            } else {
                stopLight()
            }
        }

        /*mThread = Thread(
            Runnable {
                var i = 1
                while(i<4){
                    var message = Message()
                    if (i == 1)
                    {
                        layoutFondo.background = getDrawable(R.color.stop)
                        message.arg1 = i
                    }
                    else if (i == 2)
                    {
                        message.arg1 = i
                        layoutFondo.background = getDrawable(R.color.watch_out)
                    }
                    else {
                        layoutFondo.background= getDrawable(R.color.go)
                        message.arg1 = i
                        i = 0
                    }
                    i ++
                    message.what = STOP

                    Thread.sleep(5000)
                }

            }
        )
        boton1.setOnClickListener {
        if (!mThread.isAlive)
        {
            mHandlerThread.sendEmptyMessage(START)
        }
        }
        }
    override fun onResume() {
        super.onResume()
        mHandlerThread = object:Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == START){
                    mThread.start()
                }
                else if(msg.what == STOP)
                {

                }
            }
        }*/
    }
    companion object{
        private const val DETENERSE = 0
        private const val PRECAUCION = 1
        private const val CONTINUAR = 2
        private const val DESACTIVADO = 3
        private const val DELAY = 5000L
        /*private const val START = 1
        private const val STOP = 0*/
    }
    }




