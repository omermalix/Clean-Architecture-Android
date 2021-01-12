package com.elifox.legocatalog.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.elifox.legocatalog.BR

@Suppress("UNUSED_PARAMETER")
abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel> : Fragment() {

    protected abstract val layoutId: Int

    abstract fun getViewModelClass(): Class<V>

    protected lateinit var binding: T

    val viewModel: V by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    private var mRootView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*,*>) {
            val activity: BaseActivity<*,*> = context
            activity.onFragmentAttached()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            mRootView = binding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun onDetach() {
        super.onDetach()
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}