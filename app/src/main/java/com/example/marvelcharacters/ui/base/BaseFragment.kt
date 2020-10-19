package com.example.marvelcharacters.ui.base

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.domain.failure.CharactersFailure
import com.example.domain.failure.Failure
import com.example.marvelcharacters.R
import kotlin.reflect.KClass

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

    //protected val viewModel: VM by viewModelByClass(this.getViewModel())

    protected abstract val viewModel : VM


    abstract fun getLayout() : Int
    //abstract fun getViewModel() : KClass<VM>

    private var progressDialog: AlertDialog? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(),container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservable()
    }

    private fun initObservable(){
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it) showLoading()
            else hideLoading()
        })
        viewModel.failureLD.observe(viewLifecycleOwner, failureObserver)
    }

    private val failureObserver = Observer<Failure>{
        when(it){
            is Failure.ServerError ->
                toast("ServerError with error code ${it.message}")
            is Failure.Unknown ->{
                toast("UnknownError")
                Log.e("UnknownError", it.message)
            }
            is CharactersFailure.ConflictMessage ->
                toast(it.message)
            is CharactersFailure.NotFound->
                toast("Data not found")
            is CharactersFailure.Unauthorized->
                toast("Unauthorized")
        }
    }

    private fun Fragment.createLoadingDialog(): AlertDialog?{
        return this.activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(R.layout.fullscreen_loading_dialog)
            builder.setCancelable(false)
            builder.create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    private fun showLoading(){
        if (progressDialog == null){
            progressDialog = createLoadingDialog()
            progressDialog?.show()
        }
    }

    private fun hideLoading(){
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun toast( message: String, duration: Int = Toast.LENGTH_LONG)=
        Toast.makeText(context, message , duration).show()



}