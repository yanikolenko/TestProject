package com.example.barmenu.ui.bottom_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.barmenu.databinding.FragmentNonAlcoholBinding
import com.example.barmenu.ui.adapter.AlcoholAdapter
import com.example.barmenu.ui.model.DrinkItem
import com.example.barmenu.ui.view_model.MainViewModel

class NonAlcoholFragment : Fragment() {

    private val binding: FragmentNonAlcoholBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: MainViewModel by viewModels()
    var alcoholAdapter: AlcoholAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initViewModel(){
        viewModel.allNonAlcoholCocktails.observe(viewLifecycleOwner){
            alcoholAdapter?.setInfo(it as List<DrinkItem>)
            binding.nonAlcoholRecyclerView.adapter = alcoholAdapter
        }
    }

    private fun initAdapter(){
        alcoholAdapter = AlcoholAdapter{

            val directions = NonAlcoholFragmentDirections.actionNonAlcoholFragmentToDetailInfo()
            directions.something = it.toInt()
            findNavController().navigate(directions)

        }
    }

    private fun initUI(){
        initAdapter()
        initViewModel()
    }
}