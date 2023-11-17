package com.example.machineproblem4_palomo

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EmpDetailsUser : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details_user)

        val sharedPreferences = getSharedPreferences("Employee_Preferences", MODE_PRIVATE)

        var enteredName = intent.getStringExtra("User")

        val ViewBack: Button = findViewById(R.id.ViewBack)
        val ViewAccess: TextView = findViewById(R.id.ViewAccess)
        val ViewSalary: TextView = findViewById(R.id.ViewSalary)
        val ViewPosition: TextView = findViewById(R.id.ViewPosition)
        val ViewDepartment: TextView = findViewById(R.id.ViewDepartment)
        val ViewAge: TextView = findViewById(R.id.ViewAge)
        val ViewName: TextView = findViewById(R.id.ViewName)
        val ViewAddress: TextView = findViewById(R.id.ViewAddress)

// Retrieve details from SharedPreferences based on the entered name
        val access = sharedPreferences.getString("EmployeeAccess_$enteredName", "")
        val salary = sharedPreferences.getString("EmployeeSalary_$enteredName", "")
        val position = sharedPreferences.getString("EmployeePosition_$enteredName", "")
        val department = sharedPreferences.getString("EmployeeDepartment_$enteredName", "")
        val age = sharedPreferences.getInt("EmployeeAge_$enteredName", 0)
        val address = sharedPreferences.getString("EmployeeAddress_$enteredName", "")

// Set the retrieved details to TextViews
        ViewAccess.text = access
        ViewSalary.text = salary
        ViewPosition.text = position
        ViewDepartment.text = department
        ViewAge.text = age.toString()
        ViewName.text = enteredName
        ViewAddress.text = address
    // Setting the entered name to the ViewName TextView

    ViewBack.setOnClickListener(){
        enteredName = null
        finish()
    }
    }
}