package com.example.machineproblem4_palomo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class AddEmployee : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        val sharedPreferences = getSharedPreferences("Employee_Preferences", MODE_PRIVATE)
        var validate = Validator()

        var EmpName : EditText = findViewById(R.id.edtEmpName)
        var EmpAge : EditText = findViewById(R.id.edtEmpAge)
        var EmpAddress : EditText = findViewById(R.id.edtEmpAddress)
        var EmpDepartment : Spinner = findViewById(R.id.spnDepartment)
        var EmpPosition : Spinner = findViewById(R.id.spnEmpPosition)
        var EmpSalary : EditText = findViewById(R.id.edtEmpSalary)
        var EmpAccess : Spinner = findViewById(R.id.spnEmpAccess)
        var EmpPassword : EditText = findViewById(R.id.edtEmpPassword)
        var EmpConfirm : EditText = findViewById(R.id.edtEmpConfirm)


// Sample data for the spinner
        val spnDepartment = arrayOf("Sales", "Marketing", "Finance", "Human Resources", "Operations")
        val spnPosition = arrayOf("Manager", "Supervisor", "Associate", "Assistant", "Coordinator")
        var spnAccess = arrayOf("Admin", "Employee")
// Create an ArrayAdapter using the string array and a default spinner layout
        var adpDept = ArrayAdapter(this, android.R.layout.simple_spinner_item, spnDepartment)
        var adpPos = ArrayAdapter(this, android.R.layout.simple_spinner_item, spnPosition)
        var adpAcc = ArrayAdapter(this, android.R.layout.simple_spinner_item, spnAccess)
// Specify the layout to use when the list of choices appears
        adpDept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adpPos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adpAcc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        EmpDepartment.adapter = adpDept
        EmpPosition.adapter = adpPos
        EmpAccess.adapter = adpAcc

        val btnSave: Button = findViewById(R.id.btnSubmitEmp)
        val btnBack: Button = findViewById(R.id.btnBackAdmin)
        val btnCancel : Button = findViewById(R.id.btnAdminCancel)

        btnBack.setOnClickListener(){
            finish()
        }

        btnCancel.setOnClickListener {
            // Clear EditText fields
            EmpName.text.clear()
            EmpAge.text.clear()
            EmpAddress.text.clear()
            EmpSalary.text.clear()
            EmpPassword.text.clear()
            EmpConfirm.text.clear()

            // Reset Spinner selections
            EmpDepartment.setSelection(0)
            EmpPosition.setSelection(0)
            EmpAccess.setSelection(0)
        }

        btnSave.setOnClickListener {
            var enteredPassword: String = EmpPassword.text.toString()
            var confirmPassword: String = EmpConfirm.text.toString()

            var empName: String = EmpName.text.toString().lowercase()
            var empAge: String = EmpAge.text.toString()
            var empAddress: String = EmpAddress.text.toString()
            var empSalary: String = EmpSalary.text.toString()

            if (enteredPassword == confirmPassword) {
                if (validate.isStrongPassword(enteredPassword)) {
                    if (!isEmployeeNameExists(sharedPreferences, empName)) {
                        // Store employee details
                        val editor = sharedPreferences.edit()
                        editor.putString("EmployeeName_$empName", empName)
                        editor.putInt("EmployeeAge_$empName", empAge.toInt())
                        editor.putString("EmployeeAddress_$empName", empAddress)
                        editor.putString("EmployeeDepartment_$empName", EmpDepartment.selectedItem.toString())
                        editor.putString("EmployeePosition_$empName", EmpPosition.selectedItem.toString())
                        editor.putString("EmployeeSalary_$empName", empSalary)
                        editor.putString("EmployeeAccess_$empName", EmpAccess.selectedItem.toString())

                        // Store password associated with the employee name
                        editor.putString("EmployeePassword_$empName", enteredPassword)

                        editor.apply()

                        Toast.makeText(this, "Employee details saved", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Employee name already exists", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Password must be at least 5 characters long, contain 1 lowercase, 1 uppercase, and 1 digit",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }



    }

    // Function to check if an employee name exists in SharedPreferences
    public fun isEmployeeNameExists(sharedPreferences: SharedPreferences, employeeName: String): Boolean {
        return sharedPreferences.contains("EmployeeName_$employeeName")
    }
    // Function to get an employee's password based on their name
    public fun getEmployeePassword(sharedPreferences: SharedPreferences, employeeName: String): String? {
        return sharedPreferences.getString("EmployeePassword_$employeeName", null)
    }


}



