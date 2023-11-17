package com.example.machineproblem4_palomo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmpDetailsAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details_admin)

        var sharedPreferences = getSharedPreferences("Employee_Preferences", MODE_PRIVATE)
        var employees = getEmployeesFromSharedPreferences(sharedPreferences)
        var back: Button = findViewById(R.id.btnBackDA)

        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = EmployeeAdapter(employees, this)
        recyclerView.adapter = adapter

        back.setOnClickListener(){
            val intent = Intent(this, AdminMenu::class.java)
            startActivity(intent)
        }
    }


    fun getEmployeesFromSharedPreferences(sharedPreferences: SharedPreferences): List<Employee> {
        val employees = mutableListOf<Employee>()
        val allPreferences = sharedPreferences.all

        for ((key, value) in allPreferences) {
            if (key.startsWith("EmployeeName_")) {
                val name = value as? String
                name?.let {
                    val department = sharedPreferences.getString("EmployeeDepartment_$name", "") ?: ""
                    val position = sharedPreferences.getString("EmployeePosition_$name", "") ?: ""
                    val salary = sharedPreferences.getString("EmployeeSalary_$name", "") ?: ""
                    val employee = Employee(name, department, position, salary)
                    employees.add(employee)
                }
            }
        }
        return employees
    }


}
