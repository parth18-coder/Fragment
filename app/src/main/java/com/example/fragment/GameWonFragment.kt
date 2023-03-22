package com.example.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.fragment.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        /*
        Now we use generated gameWonFragment args class to get the arguments from the argument bundle.
        we then can reference these arguments by their names
         */
        var args=GameWonFragmentArgs.fromBundle(requireArguments())

        Toast.makeText(context,"NumCorrect: ${args.numCorrect},NumQuestions:${args.numQuestion}",Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)
        return binding.root
    }

    // this create the share intent.
    private fun getShareIntent() : Intent {

        val args = GameWonFragmentArgs.fromBundle(requireArguments())

        // we are creating the new share implicit intent.
        //val shareIntent=Intent(Intent.ACTION_SEND)
        /*
        this tell the android that we want activities that are registered with an intent filter to
        handle the send action.
        */
        val shareIntent=Intent(Intent.ACTION_SEND)

        /*
        then we set the type of the data we are going to share to text/plain.
        Android uses the MIME type of our parameter to locate activities to share.
        if we wanted to share the images using the mime type of Image/JPeg then different set of activities would be
        able to handle them.
         */

        /*
        Intent Extras are a key value data structure. They are used to provide arguments to the intent.
        Some arguments type such as text have predefine keys i.e. Intent.Extra_text

         */

        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,args.numCorrect,args.numQuestion))

        return shareIntent
    }

    /*
    this method call getShareIntent() to create the Intent then call startActivity with the intent
    which starts the activity with new intent.
     */
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }



}