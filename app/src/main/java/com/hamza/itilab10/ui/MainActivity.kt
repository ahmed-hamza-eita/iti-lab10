package com.hamza.itilab10.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.hamza.itilab10.adapters.AdapterPosts
import com.hamza.itilab10.databinding.ActivityMainBinding
import com.hamza.itilab10.network.RetrofitConnection
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = AdapterPosts()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)


        actions()
    }

    private fun actions() {
        binding.btnGetPosts.setOnClickListener {
            getPosts()

        }
    }

    private fun getPosts() {
        lifecycleScope.launch {
            val result =
                RetrofitConnection().getRetrofit().getPosts(binding.edtInputId.text.toString())
            if (result.isSuccessful) {
                val posts = result.body()
                adapter.list = posts
                binding.rv.adapter = adapter

            } else {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding
    }
}