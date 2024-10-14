package com.example.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.androidmaster.R


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val vDivider: View = view.findViewById(R.id.vDivider)
    private val cvCategory: CardView = view.findViewById(R.id.cvCategory)

    fun render(taskCategory: TaskCategory) {

        tvCategoryName.text = taskCategory.isSelected.toString()

        if (taskCategory.isSelected) {
            cvCategory.setAlpha(1f)
        } else {
            cvCategory.setAlpha(0.3f)
        }

        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(
                        vDivider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Otro"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(
                        vDivider.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(
                        vDivider.context,
                        R.color.todo_personal_category
                    )
                )

            }
        }
    }
}