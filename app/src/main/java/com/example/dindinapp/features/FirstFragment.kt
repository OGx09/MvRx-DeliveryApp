package com.example.dindinapp.features

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.andremion.counterfab.CounterFab
import com.example.dindinapp.MainActivity
import com.example.dindinapp.adapter.FilterChipAdapter
import com.example.dindinapp.adapter.FoodAdapter
import com.example.dindinapp.adapter.TopAdViewPagerAdapter
import com.example.dindinapp.adapter.TopMenuAdapter
import com.example.dindinapp.databinding.FragmentFirstBinding
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment :BaseMvRxFragment(){

    private val topMenuAdapter : TopMenuAdapter by inject()
    private val filterChipAdapter : FilterChipAdapter by inject()
    private lateinit var topAdViewPagerAdapter : TopAdViewPagerAdapter
    private val foodAdapter : FoodAdapter by inject()

    private val foodDeliveryViewModel: FoodDeliveryViewModel by activityViewModel()

    private lateinit var binding: FragmentFirstBinding

    private val foodFilterList = ArrayList<FoodFilterResponse>()
    private lateinit var categoryResponseList : List<CategoryResponse>

    private lateinit var tabLayout: TabLayout

    private  var hasTabs: Boolean = false

    private lateinit var counterFab: CounterFab

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topAdViewPagerAdapter = TopAdViewPagerAdapter(requireActivity().supportFragmentManager)

        counterFab = binding.counterFab

        foodAdapter.setOnAddFoodListener(object : FoodAdapter.OnClickFoodListener {
            override fun onClickFood(foodMenu: FoodMenu) {
                counterFab.increase()
            }

        })


        topMenuAdapter.setOnClickTopMenuListener(object : TopMenuAdapter.OnClickTopMenuListener {
            override fun onClickTopMenu(categoryResponse: CategoryResponse) {
                //counterFab.increase()
            }
        })

        //foodDeliveryViewModel.doGetFoodMenuAds()
        setUpFilterRvLayoutManager()
        setUpTopAdRvLayoutManager()
        setUpFoodMenuRvLayoutManager()
        setUpCategoryMenu()
    }

    private fun setUpFilterRvLayoutManager(){
        //inc_food_content
        val filterRv =binding.incFoodContent.filterMenuRv
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        filterRv.layoutManager = layoutManager
        filterRv.adapter = filterChipAdapter
    }


    private fun setUpFoodMenuRvLayoutManager(){
        val foodMenuRv = binding.incFoodContent.foodMenuRv
        val layoutManager = LinearLayoutManager(requireContext())
        foodMenuRv.layoutManager = layoutManager
        foodMenuRv.adapter = foodAdapter

    }

    private fun setUpCategoryMenu(){
        tabLayout = binding.incFoodContent.tabLayout
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.apply {
                    foodDeliveryViewModel.doGetFoodCategory(categoryResponseList[tab.position].name)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setUpTopAdRvLayoutManager(){
        binding.pager.adapter = topAdViewPagerAdapter
        binding.dotsIndicator.setViewPager(binding.pager)
    }

    override fun invalidate() {
        val contentProgressbar = binding.incFoodContent.contentProgressbar

        withState(foodDeliveryViewModel){ state ->
            when(state.foodCategoryResponseList){
                is Loading -> {
                    contentProgressbar.visibility = View.VISIBLE
                }
                is Success -> {
                    this.categoryResponseList = state.foodCategoryResponseList.invoke()
                    val firstCategory = state.foodCategoryResponseList.invoke().first()
                    foodFilterList.addAll(firstCategory.foodFilterResponse)

                    if(!hasTabs) {
                        state.foodCategoryResponseList.invoke()
                            .forEach {
                                tabLayout.addTab(tabLayout.newTab().setText(it.name))
                            }
                    }
                    state.foodAdList?.apply {

                        Log.d("MainActivitySize", "Food list -> ${this.size}}")
                        topAdViewPagerAdapter.addAdverts(this)
                    }
                    filterChipAdapter.submitList(state.foodFilter)
                    foodAdapter.submitList(state.foodMenuList)
                    //foodDeliveryViewModel.doGetFoodMenu(firstCategory.name)
                    contentProgressbar.visibility = View.GONE
                    hasTabs = true
                }

                is Fail -> {
                    Snackbar.make(
                        requireActivity().window.decorView,
                        "An error has occurred",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                    contentProgressbar.visibility = View.GONE
                }else ->{ }
            }

            Log.d("MainActivityNNc", "Food list -> $state}")
        }

    }


    private fun showMessage(message: String){
        Snackbar.make(requireActivity().window.decorView, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }

}