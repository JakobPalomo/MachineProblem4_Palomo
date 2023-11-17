package com.example.machineproblem4_palomo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        var AddEmp : Button = findViewById(R.id.btnAdd)
        var DetailsEmployee : Button =findViewById(R.id.btnDetails)
        var Logout : Button = findViewById(R.id.btnLogOut)

        AddEmp.setOnClickListener(){
            val intent = Intent(this, AddEmployee::class.java)
            startActivity(intent)
        }

        DetailsEmployee.setOnClickListener(){
            val intent = Intent(this, EmpDetailsAdmin::class.java)
            startActivity(intent)
        }

        Logout.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}