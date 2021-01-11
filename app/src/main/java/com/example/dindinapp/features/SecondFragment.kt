package com.example.dindinapp.features

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.example.dindinapp.MainActivity
import com.example.dindinapp.R
import com.example.dindinapp.adapter.FilterChipAdapter
import com.example.dindinapp.adapter.FoodAdapter
import com.example.dindinapp.databinding.DetailsFragmentLayoutBinding
import com.example.dindinapp.databinding.FragmentFirstBinding
import com.example.dindinapp.databinding.FragmentSecondBinding
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.states.FoodDeliveryState
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : BaseMvRxFragment() {
    private val foodAdapter : FoodAdapter by inject()
    private val foodFilterAdapter: FilterChipAdapter by inject()
    private lateinit var fab: FloatingActionButton

    private val foodDeliveryViewModel: FoodDeliveryViewModel by activityViewModel()

    companion object{

        const val ARG_FILTER_LIST = "SecondFragment.ARG_FILLTER_LIST"

        const val ARG_CATEGORY_LIST = "SecondFragment.ARG_CATEGORY_LIST"

        fun newInstance(mFoodMenuList: ArrayList<FoodMenu>) : SecondFragment{
            val bundle =  Bundle().apply { putParcelableArrayList(MvRx.KEY_ARG, mFoodMenuList) }
            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            return secondFragment
        }
    }

    override fun invalidate() {

     }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding: FragmentSecondBinding = FragmentSecondBinding
            .inflate(inflater, container, false)
        // Inflate the layout for this fragment
        setUpFoodMenuRvLayoutManager(binding)
        if(arguments != null) {
            for (i in 0..binding.tabLayout.tabCount){
                binding.tabLayout.getTabAt(i)?.view?.isClickable = false
            }

            fab = (requireActivity() as MainActivity).getCartFab()
            fab.setImageResource(R.drawable.ic_baseline_credit_card_24)

            binding.contentProgressbar.visibility = View.GONE
            val foodFilterList = arguments?.getParcelableArrayList<FoodFilterResponse>(ARG_FILTER_LIST) as ArrayList<FoodFilterResponse>
            foodFilterAdapter.submitList(foodFilterList)
            setUpFoodCategoryRvLayoutManager(binding)

            val foodCategoryList = arguments?.getParcelableArrayList<CategoryResponse>(ARG_CATEGORY_LIST) as ArrayList<CategoryResponse>
            foodCategoryList.forEach {
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it.name))
            }

            val selectedList = arguments?.getParcelableArrayList<FoodMenu>(MvRx.KEY_ARG) as ArrayList<FoodMenu>
            foodAdapter.submitList(selectedList)

            fab.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelableArrayList(DeliveryFragment.ARG_FOOD_MENUS, selectedList)
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_SecondFragment_to_DeliveryFragment, bundle)
            }
        }
        foodAdapter.setOnAddFoodListener(object : FoodAdapter.OnClickFoodListener{
            override fun onClickFood(foodMenu: FoodMenu) {
                return
            }

        })


        return binding.root
    }



    private fun setUpFoodMenuRvLayoutManager(binding: FragmentSecondBinding){
        val foodMenuRv = binding.foodMenuRv
        val layoutManager = LinearLayoutManager(requireContext())
        foodMenuRv.layoutManager = layoutManager
        foodMenuRv.swapAdapter(foodAdapter, true)
    }


    private fun setUpFoodCategoryRvLayoutManager(binding: FragmentSecondBinding){
        val filterMenuRv = binding.filterMenuRv
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        filterMenuRv.layoutManager = layoutManager
        filterMenuRv.adapter = foodFilterAdapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //foodDeliveryViewModel.getSelectedOrders()
        Log.d("", "")
    }
}