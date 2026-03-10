package com.vitor_moura48.lifecycletracker

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.activity.viewModels
import com.vitor_moura48.lifecycletracker.databinding.ActivityMainBinding

class ContadorViewModel : ViewModel() {
    var onCreateCount = 0
    var onStartCount = 0
    var onResumeCount = 0
    var onPauseCount = 0
    var onStopCount = 0
    var onDestroyCount = 0

    fun resetarContagem() {
        onCreateCount = 0
        onStartCount = 0
        onResumeCount = 0
        onPauseCount = 0
        onStopCount = 0
        onDestroyCount = 0
    }
}

class MainActivity : AppCompatActivity() {

    private val viewModel: ContadorViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("main", "onCreate: Tela criada")

        viewModel.onCreateCount++

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onCreateLabel: TextView = findViewById(R.id.onCreateLabel)
        val onStartLabel: TextView = findViewById(R.id.onStartLabel)
        val onResumeLabel: TextView = findViewById(R.id.onResumeLabel)
        val onPauseLabel: TextView = findViewById(R.id.onPauseLabel)
        val onStopLabel: TextView = findViewById(R.id.onStopLabel)
        val onDestroyLabel: TextView = findViewById(R.id.onDestroyLabel)

        val btnReset: Button = findViewById(R.id.buttonReset)

        // Usa o valor do ViewModel
        updateLabels()

        btnReset.setOnClickListener {
            viewModel.resetarContagem()
            updateLabels()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
        }
    }

    private fun updateLabels() {
        binding.onCreateLabel.text = "onCreate: ${viewModel.onCreateCount}"
        binding.onStartLabel.text = "onStart: ${viewModel.onStartCount}"
        binding.onResumeLabel.text = "onResume: ${viewModel.onResumeCount}"
        binding.onPauseLabel.text = "onPause: ${viewModel.onPauseCount}"
        binding.onStopLabel.text = "onStop: ${viewModel.onStopCount}"
        binding.onDestroyLabel.text = "onDestroy: ${viewModel.onDestroyCount}"
    }

    // criando logs para os callbacks
    override fun onStart() {
        super.onStart()
        Log.i("main", "onStart: Tela visível")

        viewModel.onStartCount++
        binding.onStartLabel.text = "onStart: ${viewModel.onStartCount}"
    }

    override fun onResume() {
        super.onResume()
        Log.i("main", "onResume: App em foco")

        viewModel.onResumeCount++
        binding.onResumeLabel.text = "onResume: ${viewModel.onResumeCount}"
    }

    override fun onPause() {
        super.onPause()
        Log.i("main", "onPause: Perdeu foco")

        viewModel.onPauseCount++
        binding.onPauseLabel.text = "onPause: ${viewModel.onPauseCount}"
    }

    override fun onStop() {
        super.onStop()
        Log.i("main", "onStop: Tela não visível")

        viewModel.onStopCount++
        binding.onStopLabel.text = "onStop: ${viewModel.onStopCount}"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("main", "onDestroy: Tela destruída")

        viewModel.onDestroyCount++
        binding.onDestroyLabel.text = "onDestroy: ${viewModel.onDestroyCount}"
    }
}