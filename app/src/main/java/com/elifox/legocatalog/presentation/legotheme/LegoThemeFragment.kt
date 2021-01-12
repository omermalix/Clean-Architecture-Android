package com.elifox.legocatalog.presentation.legotheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elifox.legocatalog.R
import com.elifox.legocatalog.data.Result
import com.elifox.legocatalog.databinding.FragmentThemesBinding
import com.elifox.legocatalog.di.VerticalItemDecorateAI
import com.elifox.legocatalog.presentation.MainActivity
import com.elifox.legocatalog.presentation.base.BaseFragment
import com.elifox.legocatalog.util.VerticalItemDecoration
import com.elifox.legocatalog.util.hide
import com.elifox.legocatalog.util.show
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LegoThemeFragment  @Inject constructor() : BaseFragment<FragmentThemesBinding, LegoThemeViewModel>(){
    override val layoutId = R.layout.fragment_themes

    @Inject
    lateinit var adapter: LegoThemeAdapter

    @Inject
    lateinit var verticalItemDecorateFactory: VerticalItemDecorateAI.Factory

    override fun getViewModelClass() = LegoThemeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.addItemDecoration(verticalItemDecorateFactory.create(resources.getDimension(R.dimen.margin_normal).toInt()))
        binding.recyclerView.adapter = adapter
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
    }

    private fun subscribeUi(binding: FragmentThemesBinding, adapter: LegoThemeAdapter) {
        viewModel.legoThemes.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it.results) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}