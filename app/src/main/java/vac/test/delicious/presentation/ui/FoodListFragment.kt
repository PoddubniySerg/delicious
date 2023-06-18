package vac.test.delicious.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vac.test.delicious.databinding.FragmentFoodListBinding
import vac.test.delicious.domain.State
import vac.test.delicious.domain.entities.TopMenu
import vac.test.delicious.domain.model.TopMenuPresent
import vac.test.delicious.presentation.viewmodels.FoodListViewModel
import vac.test.delicious.presentation.viewmodels.FoodListViewModelFactory

class FoodListFragment : Fragment() {

    private var _binding: FragmentFoodListBinding? = null

    private val viewModel by viewModels<FoodListViewModel> { FoodListViewModelFactory() }

    private lateinit var adapterCardsFood: AdapterCardsFood
    private lateinit var adapterTopMenuList: AdapterTopMenuItems
    private lateinit var adapterBanners: AdapterBanners

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        setListeners()
        getData()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapters() {
        adapterCardsFood = AdapterCardsFood()
        adapterTopMenuList = AdapterTopMenuItems { pos -> onClickMenu(pos) }
        adapterBanners = AdapterBanners()
        with(_binding!!) {
            collectionsView.adapter = adapterCardsFood
            bannersList.adapter = adapterBanners
            topMenuView.adapter = adapterTopMenuList
        }
    }

    private fun setListeners() {
        with(_binding!!) {
            selectCityButton.setOnClickListener { selectCityList.performClick() }
        }
    }

    private fun getData() {
        viewModel.getPizza()
        viewModel.getTopMenuItems()
        viewModel.getBanners()
    }

    private fun initObservers() {
        viewModel.stateFlow.onEach { handleState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.bannersFlow.onEach {
            adapterBanners.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.cardFoodFlow.onEach {
            adapterCardsFood.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topMenuFlow.onEach {
            val list = mutableListOf<TopMenu>()
            for (i in it.indices) {
                val topMenu = TopMenuPresent(it[i])
                if (i == 0) {
                    topMenu.isSelected = true
                }
                list.add(topMenu)
            }
            adapterTopMenuList.submitList(list)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        (_binding!!.topMenuView.layoutManager as LinearLayoutManager)
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Complete -> _binding!!.progressBar.visibility = View.GONE
            State.Loading -> _binding!!.progressBar.visibility = View.VISIBLE
            is State.Error ->
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun onClickMenu(pos: Int) {
        val items = adapterTopMenuList.currentList
        val list = mutableListOf<TopMenu>()
        for (i in items.indices) {
            val topMenu = TopMenuPresent(items[i])
            topMenu.isSelected = i == pos
            list.add(topMenu)
        }
        adapterTopMenuList.submitList(list)
        when (pos) {
            0 -> viewModel.getPizza()
            1 -> viewModel.getCombo()
            2 -> viewModel.getDesserts()
            3 -> viewModel.getDrinks()
        }
    }
}