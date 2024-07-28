package br.com.fabriciocurvello.appthreadkotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var tvStatus: TextView
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnStart = findViewById(R.id.btn_start)
        tvStatus = findViewById(R.id.tv_status)

        btnStart.setOnClickListener {
            startBackgroundTask()
        }

    }

    private fun startBackgroundTask() {
        Thread {
            for (cont in 0..10) {
                val progress = cont
                mainHandler.post {
                    tvStatus.text = "Status: ${progress * 10}%"
                }
                Thread.sleep(500) // Simula uma tarefa longa
            }
            mainHandler.post{
                tvStatus.text = "Status: Finalizado"
            }
        }.start()
    }
}