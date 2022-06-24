package com.example.barmenu.ui.detail_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.barmenu.databinding.FragmentDetailInfoBinding
import com.example.barmenu.tools.CenterZoomLinearLayoutManager
import com.example.barmenu.tools.DisplayBitmap
import com.example.barmenu.tools.GenerateImgLink
import com.example.barmenu.ui.adapter.IngredientsAdapter
import com.example.barmenu.ui.view_model.MainViewModel

class DetailInfoFragment : Fragment() {

    private val binding: FragmentDetailInfoBinding by viewBinding(CreateMethod.INFLATE)
    private val args: DetailInfoFragmentArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()
    private val displayBitmap = DisplayBitmap()
    private var centerZoomLayoutManager: CenterZoomLinearLayoutManager? = null
    var ingredientsAdapter: IngredientsAdapter? = null
    private val generateImgLink = GenerateImgLink()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initViewModel(){

        viewModel.getDrinksById("lookup.php?i=" + "${drinkId()}")
        viewModel.allDrinksInfo.observe(viewLifecycleOwner){



            try{
                //display drink info
                displayBitmap.displayPhoto(url = it?.get(0)!!.strDrinkThumb, binding.chosenDrinkImageView)
                binding.detailAlcoholName.text = it[0]!!.strDrink

                //display ingredients info

                val ingredients = listOf(
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient1.toString()), it[0]?.strIngredient1),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient2.toString()), it[0]?.strIngredient2),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient3.toString()), it[0]?.strIngredient3),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient4.toString()), it[0]?.strIngredient4),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient5.toString()), it[0]?.strIngredient5),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient6.toString()), it[0]?.strIngredient6),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient7.toString()), it[0]?.strIngredient7),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient8.toString()), it[0]?.strIngredient8),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient9.toString()), it[0]?.strIngredient9),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient10.toString()), it[0]?.strIngredient10),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient11.toString()), it[0]?.strIngredient11),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient12.toString()), it[0]?.strIngredient12),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient13.toString()), it[0]?.strIngredient13),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient14.toString()), it[0]?.strIngredient14),
                    listOf(generateImgLink.generateLink(name = it[0]?.strIngredient15.toString()), it[0]?.strIngredient15)
                )

                for (i in ingredients){
                    if (i[0] != null && i[1] != null){
                        ingredientsAdapter?.setInfo(listOf(i) as List<List<String>>)
                    }
                }
                binding.progressBar.visibility = View.INVISIBLE

            }catch (e: NullPointerException){}
        }
    }

    private fun drinkId(): Int = args.something

    private fun initAdapter(){
        centerZoomLayoutManager = CenterZoomLinearLayoutManager(requireContext())
        ingredientsAdapter = IngredientsAdapter()
        binding.ingredientsRecyclerView.adapter = ingredientsAdapter
        binding.ingredientsRecyclerView.layoutManager = centerZoomLayoutManager

    }

    private fun initUI(){
        initAdapter()
        initViewModel()
    }

}