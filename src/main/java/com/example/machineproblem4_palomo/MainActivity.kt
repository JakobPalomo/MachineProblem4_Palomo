package com.example.machineproblem4_palomo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences("Employee_Preferences", MODE_PRIVATE)
        var Check = AddEmployee();

        var Login : Button = findViewById(R.id.btnLogin)
        var Password : EditText = findViewById(R.id.edtPassword)
        var Username : EditText = findViewById(R.id.edtUsername)

        Login.setOnClickListener {
            var enteredNameLowercase: String = Username.text.toString().lowercase()
            var enteredPassword: String = Password.text.toString()

            val defaultUsername = "admin"
            val defaultPassword = "admin"

            val sharedPreferences = getSharedPreferences("Employee_Preferences", MODE_PRIVATE)

            if (enteredNameLowercase == defaultUsername && enteredPassword == defaultPassword) {
                // Username and password match the default ones, proceed to the next screen
                val intent = Intent(this, AdminMenu::class.java)
                startActivity(intent)
            } else {
                // Check if the entered employee name exists in SharedPreferences
                if (Check.isEmployeeNameExists(sharedPreferences, enteredNameLowercase)) {
                    // Entered employee name exists in SharedPreferences
                    val storedPassword = sharedPreferences.getString("EmployeePassword_$enteredNameLowercase", "")
                    val storedAccess = sharedPreferences.getString("EmployeeAccess_$enteredNameLowercase", "")

                    if (storedPassword != null && storedPassword == enteredPassword) {
                        // Password matches the stored password for the entered employee name
                        if (storedAccess.equals("Admin", ignoreCase = true)) {
                            // Access is Admin, proceed to AdminMenu
                            Password.text.clear()
                            Username.text.clear()
                            val intent = Intent(this, AdminMenu::class.java)
                            intent.putExtra("User",enteredNameLowercase)
                            startActivity(intent)
                        } else if (storedAccess.equals("Employee", ignoreCase = true)) {
                            Password.text.clear()
                            Username.text.clear()
                            val intent = Intent(this, EmpDetailsUser::class.java)
                            intent.putExtra("User",enteredNameLowercase)
                            startActivity(intent)
                        }
                    } else {
                        // Password is incorrect, display an error message
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Employee name doesn't exist in SharedPreferences
                    Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}

