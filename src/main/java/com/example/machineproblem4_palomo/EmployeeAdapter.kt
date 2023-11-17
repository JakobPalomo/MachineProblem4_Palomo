package com.example.machineproblem4_palomo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

data class Employee(
    val name: String,
    val department: String,
    val position: String,
    val salary: String // Assuming salary is a numeric value
    // Add more fields as needed
)

class EmployeeAdapter(private val employeeList: List<Employee>, private val context: Context) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.textViewEmployeeName)
        val position: TextView = itemView.findViewById(R.id.textViewPosition)
        val salary: TextView = itemView.findViewById(R.id.textViewSalary)
        val department: TextView = itemView.findViewById(R.id.textViewDepartment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = employeeList[position]
        holder.employeeName.text = currentItem.name
        holder.position.text = currentItem.position
        holder.salary.text = currentItem.salary
        holder.department.text = currentItem.department

        holder.itemView.setOnClickListener {
            val intent = Intent(context, EmpDetailsUser::class.java)
            intent.putExtra("User", currentItem.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = employeeList.size
}