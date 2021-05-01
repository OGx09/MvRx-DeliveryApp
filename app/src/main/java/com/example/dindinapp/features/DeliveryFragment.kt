package com.example.dindinapp.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.BaseMvRxFragment
import com.example.dindinapp.adapter.DeliveryItemAdapter
import com.example.dindinapp.databinding.DeliveryLayoutBinding
import com.example.dindinapp.models.FoodMenu
import org.koin.android.ext.android.inject

// Created by Gbenga Oladipupo(Devmike01) on 1/10/21.
class DeliveryFragment : BaseMvRxFragment() {

    private lateinit var binding : DeliveryLayoutBinding

    private val deliveryAdapter : DeliveryItemAdapter by inject()

    companion object{

        const val ARG_FOOD_DELIVERY ="DeliveryFragment.ARG_FOOD_DELIVERY"
        const val ARG_FOOD_MENUS ="DeliveryFragment.ARG_FOOD_MENUS"

        fun newInstance(foodMenuList: ArrayList<FoodMenu>): DeliveryFragment{
            val bundle = Bundle()
            bundle.putParcelableArrayList(ARG_FOOD_MENUS, foodMenuList)
            val deliveryFragment = DeliveryFragment()
            deliveryFragment.arguments = bundle
            return deliveryFragment
        }
    }

    override fun invalidate() {

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DeliveryLayoutBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deliveryItemsRv.layoutManager = LinearLayoutManager(activity)
        binding.deliveryItemsRv.adapter = deliveryAdapter
        val foodList = arguments?.getParcelableArrayList<FoodMenu>(ARG_FOOD_MENUS) as ArrayList<FoodMenu>
        deliveryAdapter.submitList(foodList)

       deliveryAdapter.setOnSumCalculatorListener(object : DeliveryItemAdapter.OnSumCalculatorListener{
           override fun onSumCalculate(total: String) {
               binding.sumTv.text = total
           }

       })

        //val tab = binding.tabLayout.newTab()
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cart"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Orders"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Information"))
    }
}