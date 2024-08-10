package com.example.nonameapp.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.adapter.SubjectAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentExerciseTestBinding

class ExerciseTestFragment :
    BaseFragment<FragmentExerciseTestBinding>(FragmentExerciseTestBinding::inflate) {
    private lateinit var homeActivity: HomeActivity
    private lateinit var adapter: SubjectAdapter

    companion object {
        var idSubject = ""
        var nameSubject = ""
    }

    private fun setSubjectIdAndName(newId: String = "", newName: String = "") {
        idSubject = newId
        nameSubject = newName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        homeActivity = activity as HomeActivity

        adapter = SubjectAdapter(
            { homeActivity.onItemClick(false) },
            { findNavController().navigate(R.id.exercise_to_test) },
            { id, name -> setSubjectIdAndName(newId = id, newName = name) },
        )
    }

    override val viewModel: ExerciseTestModel
        get() = ViewModelProvider(this)[ExerciseTestModel::class.java]

    override fun initData() {
        try {
            viewModel.getSubject(HomeFragment.idSubject)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show();
        }
    }

    override fun bindData() {
        binding.recyclerviewSubject.adapter = adapter
        binding.recyclerviewSubject.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listSubject.value?.test?.let {
            adapter.setSubjectList(it)
        }
    }

    override fun observeData() {
        viewModel.listSubject.observe(this) {
            bindData()
        }
    }

    override fun setOnClick() {
        binding.btnBackToExercise.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
            homeActivity.onItemClick(true)
        }
    }

    private fun getToken(
        context: Context,
        key: String = "access_token",
        defaultValue: String = "",
    ) = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        .getString(key, defaultValue) ?: defaultValue

}