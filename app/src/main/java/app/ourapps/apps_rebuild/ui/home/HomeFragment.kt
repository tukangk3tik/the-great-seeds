package app.ourapps.apps_rebuild.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.ourapps.apps_rebuild.utils.AppRoutes
import app.ourapps.apps_rebuild.databinding.FragmentHomeBinding
import app.ourapps.apps_rebuild.models.menu.Menu
import app.ourapps.apps_rebuild.utils.menu.MenuClickListener
import app.ourapps.apps_rebuild.utils.menu.MenuGridAdapter
import app.ourapps.apps_rebuild.utils.preferencemanager.PreferenceManager

class HomeFragment : Fragment(), MenuClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: MenuGridAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val appRoutes = AppRoutes.getInstance()
    private lateinit var userPreferences: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = HomeViewModel()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userPreferences = PreferenceManager(root.context)

        if (!homeViewModel.isAvailableIdentity()){
            homeViewModel.setIdentity(userPreferences.getName(), userPreferences.getJabatan())
        }

        adapter = MenuGridAdapter()
        adapter.listener = this
        binding.rvMainMenu.layoutManager = GridLayoutManager(root.context, 2)
        binding.rvMainMenu.adapter = adapter

        homeViewModel.listMainMenu.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                adapter.setData(it)
            }
        })

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getIdentity()?.observe(viewLifecycleOwner, {
            binding.tvPegawaiName.text = it[0]      //first index string for name
            binding.tvPegawaiJabatan.text = it[1]   //second index string for jabatan
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(view: View, menu: Menu) {
        appRoutes.setDestination(menu.route)
        val routeClass = appRoutes.getRoute()

        if (routeClass != null) {
            val mIntent = Intent(activity, routeClass)
            startActivity(mIntent)
        }
    }


}