package com.belema.listofkotlindevelopers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.belema.listofkotlindevelopers.MyApp
import com.belema.listofkotlindevelopers.databinding.FragmentUserListBinding
import com.belema.listofkotlindevelopers.di.component.DaggerUserListComponent
import com.belema.listofkotlindevelopers.remote.util.NetworkUtil
import com.belema.listofkotlindevelopers.ui.adapter.ItemListAdapter
import com.belema.listofkotlindevelopers.viewmodel.UserListViewModel
import com.belema.listofkotlindevelopers.viewmodel.UserListViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {

    private lateinit var userListBinding: FragmentUserListBinding

    @Inject
    lateinit var userListViewModelFactory: UserListViewModelFactory

    private lateinit var networkUtil: NetworkUtil

    private lateinit var userListViewModel: UserListViewModel

    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        userListBinding = FragmentUserListBinding.inflate(inflater, container, false)
        return userListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerUserListComponent.builder().applicationComponent(MyApp.applicationComponent)
                .build().inject(this)

        progressDialog = CustomProgressDialog(context)

        networkUtil = NetworkUtil(context)

        val userListAdapter = ItemListAdapter { login ->
            findNavController().navigate(UserListFragmentDirections
                .actionUserListFragmentToUserFragment(login))
        }

        val linearLayoutManager = LinearLayoutManager(context)
        userListBinding.userListRv.layoutManager = linearLayoutManager
        userListBinding.userListRv.addItemDecoration(DividerItemDecoration(context,
            linearLayoutManager.orientation))
        userListBinding.userListRv.adapter = userListAdapter

        userListViewModel = ViewModelProviders.of(this, userListViewModelFactory)
                .get(UserListViewModel::class.java)

        userListViewModel.getData(networkUtil.isConnected())

        userListViewModel.data.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                userListAdapter.setData(it)
            }
        })

        userListViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading){
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })

        userListViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Snackbar.make(userListBinding.root, it.localizedMessage,
                        Snackbar.LENGTH_LONG).show()
            }
        })
    }


}