package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.fragment.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentTitleBinding= DataBindingUtil.inflate(
            inflater,R.layout.fragment_title,container,false
        )

        // here we are passing the anonymous function in the setOnClickListener
        binding.playButton.setOnClickListener{view:View->

            // in anonymous function we are finding the instance of navController which is the class that we
            // use to manage navigation within our navigation host fragment.

            /*
            Nav host fragment is parent in the view hierarchy of our current fragment i.e like a parent view
            so we can travel up this hierarchy to find the navHostFragment from any view in a fragment like a "play button"
            here.
             */

            /*
            Navigation provide us a helper function call findNavController() that takes the view,
            find the enclosing navHostFragment and return the navController(i.e instance of the navController) for that
            NavHostFragment. so navigate.findNavController(view) or view.findNavController return the instance of the
            navController.

            After getting the navController we can use it to navigate to the game fragment, with the help of the
            id of the action i.e R.id.action_titleFragment_to_gameFragment
            */

            /*
            we are able to use view.findNavController() in place of navigation.findNavController(view) because we had
            added the dependencies of kotlin specific version of the navigation, part of the jetpack project called
            Android KTX, which includes the extension functions. Kotlin extension functions allow the class to extend
            functionality of the class without subclassing it. Ktx has extension functions for the android "View" Class
             */

            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)


            /*
            Navigation can create on click listener for us. we can replace this anonymous function. then our code will be
            like this:
                binding.playButton.setOnClickListener(
                    Navigation.createNavigationOnClickListener(R.id.action_titleFragment_to_gameFragment)
                 )


             */

        }

        return binding.root
    }

}