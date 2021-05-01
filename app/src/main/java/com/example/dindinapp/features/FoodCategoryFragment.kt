package com.example.dindinapp.features

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.andremion.counterfab.CounterFab
import com.example.dindinapp.MainActivity
import com.example.dindinapp.R
import com.example.dindinapp.adapter.FilterChipAdapter
import com.example.dindinapp.adapter.FoodAdapter
import com.example.dindinapp.adapter.TopAdViewPagerAdapter
import com.example.dindinapp.databinding.FragmentFirstBinding
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import java.lang.Exception


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FoodCategoryFragment :BaseMvRxFragment(){

    private val filterChipAdapter : FilterChipAdapter by inject()
    private lateinit var topAdViewPagerAdapter : TopAdViewPagerAdapter
    private val foodAdapter : FoodAdapter by inject()

    private val mFoodMenuList = ArrayList<FoodMenu>()

    private val foodDeliveryViewModel: FoodDeliveryViewModel by activityViewModel()

    private lateinit var binding: FragmentFirstBinding

    private lateinit var foodFilterList : ArrayList<FoodFilterResponse>
    private lateinit var categoryResponseList : ArrayList<CategoryResponse>

    private lateinit var tabLayout: TabLayout

    private  var hasTabs: Boolean = false

    private lateinit var counterFab: CounterFab


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        foodDeliveryViewModel.doGetFoodCategory(null)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topAdViewPagerAdapter = TopAdViewPagerAdapter(requireActivity().supportFragmentManager)

        val txt1 = "Kazarov\ndelivery"
        val txtSpannable = SpannableString(txt1)
        val boldSpan = StyleSpan(Typeface.BOLD)
        txtSpannable.setSpan(boldSpan, 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.titleTv.setTextColor(if(BuildConfig.VERSION_CODE < 23) {
            requireActivity().resources.getColor(R.color.white)} else {requireActivity().getColor(R.color.white)})
        binding.titleTv.text = txtSpannable

        counterFab = (requireActivity() as MainActivity).getCartFab()

        foodAdapter.setOnAddFoodListener(object : FoodAdapter.OnClickFoodListener {
            override fun onClickFood(foodMenu: FoodMenu) {
                counterFab.increase()
                mFoodMenuList.add(foodMenu)
                foodDeliveryViewModel.setSelectedOrders(foodMenu)
            }

        })

        counterFab.setOnClickListener { view ->
            if (mFoodMenuList.isNotEmpty()) {
                val bundle = Bundle().apply { putParcelableArrayList(MvRx.KEY_ARG, mFoodMenuList) }
                bundle.putParcelableArrayList(SecondFragment.ARG_FILTER_LIST, foodFilterList)
                bundle.putParcelableArrayList(
                    SecondFragment.ARG_CATEGORY_LIST,
                    categoryResponseList
                )
                findNavController(this).navigate(
                    R.id.action_FirstFragment_to_SecondFragment,
                    bundle
                )
            }
        }

        //foodDeliveryViewModel.doGetFoodMenuAds()
        setUpFilterRvLayoutManager()
        setUpTopAdRvLayoutManager()
        setUpFoodMenuRvLayoutManager()
        setUpCategoryMenu()

        binding.incFoodContent.nestedScrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> if (scrollY > 30 && !counterFab.isShown) counterFab.show() else if (scrollY < 30 && counterFab.isShown) counterFab.hide() })

        foodDeliveryViewModel.subscribe {state->
            val contentProgressbar = binding.incFoodContent.contentProgressbar
            when(state.foodCategoryResponseList){
                is Loading -> {
                    contentProgressbar.visibility = View.VISIBLE
                }
                is Success -> {
                    this.categoryResponseList = state.foodCategoryResponseList.invoke()
                    val firstCategory = state.foodCategoryResponseList.invoke().first()
                    foodFilterList = firstCategory.foodFilterResponse

                    if (!hasTabs) {
                        state.foodCategoryResponseList.invoke()
                            .forEach {
                                tabLayout.addTab(tabLayout.newTab().setText(it.name))
                            }
                    }
                    state.foodAdList?.apply {
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
        }
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
        binding.pager.offscreenPageLimit = 1;
    }

    override fun invalidate() {



    }


    override fun onResume() {
        super.onResume()
        this.hasTabs = false
        counterFab.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24)
    }


}