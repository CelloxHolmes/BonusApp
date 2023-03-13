package com.example.bonusapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bonusapp.databinding.ActivityMainBinding
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var fibonacciButton: Button
    private lateinit var eratosthenesButton: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fibonacciButton = binding.fibonacciButton
        eratosthenesButton = binding.eratosthenesButton

        fibonacciButton.setOnClickListener {
            val inputText = binding.inputNumber.text
            val limit = inputText.toString().toInt()
            val fibonacci = generateFibonacciSequence(limit)
            val result = StringBuilder()
            fibonacci.forEach { result.append(it).append(", ") }
            binding.resultTextView.setText(result.toString().dropLast(2))
        }

        eratosthenesButton.setOnClickListener {
            val inputText = binding.inputNumber.text.toString()
            val limit = inputText.toInt()
            val primes = generatePrimes(limit)
            binding.resultTextView.setText(primes.toString())
        }
    }

    private fun generateFibonacciSequence(n: Int): List<Long> {
        val fibonacci = mutableListOf(0L, 1L)
        for (i in 2 until n) {
            fibonacci.add(fibonacci[i-1] + fibonacci[i-2])
        }
        return fibonacci
    }

    private fun generatePrimes(n: Int): List<Int> {
        val primes = mutableListOf<Int>()
        var candidate = 2
        while (primes.size < n) {
            var isPrime = true
            for (factor in 2..sqrt(candidate.toDouble()).toInt()) {
                if (candidate % factor == 0) {
                    isPrime = false
                    break
                }
            }
            if (isPrime) {
                primes.add(candidate)
            }
            candidate++
        }
        return primes
    }

}
