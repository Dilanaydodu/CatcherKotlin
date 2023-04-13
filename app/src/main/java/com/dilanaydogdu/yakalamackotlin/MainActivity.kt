package com.dilanaydogdu.yakalamackotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var skor=0
    val array= ArrayList<ImageView>()
    var runnable = Runnable{ }
    var handler= Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ımage array
        array.add(imageView)
        array.add(imageView1)
        array.add(imageView2)
        array.add(imageView3)
        array.add(imageView4)
        array.add(imageView5)
        array.add(imageView6)
        array.add(imageView7)
        array.add(imageView8)


         gizle()

        object : CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
            timetext.text="Time:${p0/1000}"

            }

            override fun onFinish() {
                timetext.text="Süre Bitti."
                handler.removeCallbacks(runnable)

                for(i in array){
                    i.visibility=View.INVISIBLE
                }
                var a= AlertDialog.Builder(this@MainActivity)
                a.setTitle("Game Over")
                a.setMessage(" Again?")
                a.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                })
                a.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"GAme Over",Toast.LENGTH_LONG).show()
                })
                a.show()
            }

        }.start()


    }
    fun gizle(){
        runnable=object : Runnable{
            override fun run() {
                for(i in array){
                    i.visibility=View.INVISIBLE
                }
                val random = Random()
                val index= random.nextInt(9)
                array[index].visibility= View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)
    }
    fun skorarttir(view: View){
        skor= skor+1
        skortext.text="Score:${skor}"
    }
}