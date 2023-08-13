package com.hamza.itilab10.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.hamza.itilab10.R
import com.hamza.itilab10.databinding.ActivityLoginBinding
import com.hamza.itilab10.databinding.ActivityMainBinding
import com.hamza.itilab10.models.ModelLogin
import com.hamza.itilab10.network.RetrofitConnection
import com.hamza.itilab10.utils.ProgressLoading
import com.hamza.retofit_lab9.utils.Const
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        actions()
    }

    private fun actions() {
        binding.btnLogin.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        val email = binding.edtEmail.text.trim().toString()
        val password = binding.edtPassword.text.trim().toString()
        if (email.isEmpty()) {
            binding.edtEmail.error = getString(R.string.required)
        } else if (password.isEmpty()) {
            binding.edtPassword.error = getString(R.string.required)
        } else {
            ProgressLoading.show(this)
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        lifecycleScope.launch {
            val result = RetrofitConnection.getRetrofit(Const.BASE_URL_LOGIN)
                .login(ModelLogin(email, password))

            if (result.isSuccessful) {


                intent()
                ProgressLoading.dismiss()

            } else {
                val error = JSONObject(result.errorBody()?.string())
                Toast.makeText(this@LoginActivity, error.getString("message"), Toast.LENGTH_SHORT)
                    .show()
                ProgressLoading.dismiss()
            }
        }
    }

    private fun intent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding
    }
}