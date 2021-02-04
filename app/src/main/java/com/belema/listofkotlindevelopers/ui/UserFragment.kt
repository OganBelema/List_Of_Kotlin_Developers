package com.belema.listofkotlindevelopers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.belema.listofkotlindevelopers.MyApp
import com.belema.listofkotlindevelopers.R
import com.belema.listofkotlindevelopers.databinding.FragmentUserBinding
import com.belema.listofkotlindevelopers.di.component.DaggerUserComponent
import com.belema.listofkotlindevelopers.local.entity.UserEntity
import com.belema.listofkotlindevelopers.remote.util.NetworkUtil
import com.belema.listofkotlindevelopers.viewmodel.UserViewModel
import com.belema.listofkotlindevelopers.viewmodel.UserViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {

    private lateinit var fragmentUserBinding: FragmentUserBinding

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private lateinit var networkUtil: NetworkUtil

    private lateinit var userViewModel: UserViewModel

    private lateinit var progressDialog: CustomProgressDialog

    private val args: UserFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentUserBinding = FragmentUserBinding.inflate(inflater, container, false)
        return fragmentUserBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerUserComponent.builder().applicationComponent(MyApp.applicationComponent)
            .build().inject(this)

        progressDialog = CustomProgressDialog(context)

        networkUtil = NetworkUtil(context)

        userViewModel = ViewModelProviders.of(this, userViewModelFactory)
            .get(UserViewModel::class.java)

        val login = args.login

        if (login != null){
            userViewModel.getData(networkUtil.isConnected(), login)
        }

        userViewModel.data.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                updateUI(user)
            }
        })

        userViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading){
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })

        userViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Snackbar.make(fragmentUserBinding.root, it.localizedMessage,
                    Snackbar.LENGTH_LONG).show()
            }
        })

        fragmentUserBinding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateUI(user: UserEntity?) {
        Glide.with(fragmentUserBinding.userImageView.context)
            .load(user?.avatarUrl)
            .error(R.drawable.ic_avatar)
            .into(fragmentUserBinding.userImageView)

        fragmentUserBinding.nameTextView.text = resources.getString(R.string.name, user?.name ?: "")
        fragmentUserBinding.emailTextView.text = resources.getString(R.string.email, user?.email ?: "")
        fragmentUserBinding.companyTextView.text = resources.getString(R.string.company,
            user?.company ?: "")
        fragmentUserBinding.publicReposTextView.text = resources.getString(R.string.public_repos,
            user?.publicRepos ?: 0)
        fragmentUserBinding.followersTextView.text = resources.getString(R.string.followers,
            user?.followers ?: 0)
    }

}