package com.example.proyecto


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Actividad de inicio de sesión que utiliza las preferencias compartidas para almacenar el usuario autenticado.
class Login : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContra: EditText
    private lateinit var btnValidar: Button
    private lateinit var tvError: TextView
    private lateinit var btnRegistro: Button
    private lateinit var usuarioClase: User
    private lateinit var preferenciasCompartidas: Preferences

    private val usuarioAdmin = "victor"
    private val contraAdmin = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferenciasCompartidas = Preferences(this)

        // Si ya hay un usuario autenticado, se vuelve a la actividad principal.
        if (preferenciasCompartidas.isAutenticado()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        usuarioClase = User()

        etUsuario = findViewById(R.id.etUser)
        etContra = findViewById(R.id.etContra)
        btnValidar = findViewById(R.id.btnValidar)
        tvError = findViewById(R.id.tvError)
        btnRegistro = findViewById(R.id.btnRegistrar)

        btnValidar.setOnClickListener {
            val myUser = etUsuario.text.toString()
            val myPass = etContra.text.toString()

            usuarioClase.setUser(myUser)
            usuarioClase.setPassword(myPass)

            // Verifica si el usuario y la contraseña coinciden con los almacenados o si son las credenciales de admiin.
            if (UserList.checkUsersPassword(usuarioClase) || (usuarioClase.getUser() == usuarioAdmin && usuarioClase.getPassword() == contraAdmin)) {
                preferenciasCompartidas.guardarUsuario(myUser)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                tvError.apply {
                    visibility = View.VISIBLE
                    text = "Credenciales incorrectas"
                }
            }
        }

        btnRegistro.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}