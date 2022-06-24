package com.example.barmenu.ui.bottom_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.barmenu.R
import com.example.barmenu.databinding.FragmentAlcoholBinding
import com.example.barmenu.ui.adapter.AlcoholAdapter
import com.example.barmenu.ui.model.DrinkItem
import com.example.barmenu.ui.view_model.MainViewModel

class AlcoholFragment : Fragment() {

    private val binding: FragmentAlcoholBinding by viewBinding(CreateMethod.INFLATE)
    var alcoholAdapter: AlcoholAdapter? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initViewModel(){
        viewModel.allAlcoholCocktails.observe(viewLifecycleOwner){

            alcoholAdapter?.setInfo(it as List<DrinkItem>)
            binding.alcoholRecyclerView.adapter = alcoholAdapter

        }
    }

    private fun navigateDetailInfo(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            this.remove()
        }
    }

    private fun initAdapter(){
        alcoholAdapter = AlcoholAdapter(){
            val directions = AlcoholFragmentDirections.actionAlcoholFragmentToDetailInfo()
            directions.something = it.toInt()
            findNavController().navigate(directions)
        }
    }

    private fun initUi(){
        initAdapter()
        initViewModel()
        navigateDetailInfo()
    }

}