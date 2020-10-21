package projects.kevin.crudapplication.ui.subscribe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.subscriber_fragment.*
import projects.kevin.crudapplication.R
import projects.kevin.crudapplication.data.db.AppDatabase
import projects.kevin.crudapplication.data.db.dao.SubscribeDAO
import projects.kevin.crudapplication.extensions.hideKeyboard
import projects.kevin.crudapplication.repository.DatabaseDataSource
import projects.kevin.crudapplication.repository.SubscriberRepository

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscribeDAO =
                    AppDatabase.getInstance(requireContext()).subscribeDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }

            viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
                Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun clearFields() {
        input_email.text?.clear()
        input_name.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()
            viewModel.addSubscriber(name, email)
        }
    }

}
