package x.pandoraapp.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.fragment_settings.*
import x.pandoraapp.R
import x.pandoraapp.constraints.USER_ID_BUNDLE
import x.pandoraapp.models.User
import x.pandoraapp.repository.SharedPreferencesRepositoryImplementation

class SettingsFragment : Fragment() {

    private val sharedRepository by lazy {
        SharedPreferencesRepositoryImplementation<User>(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = sharedRepository.getValue(USER_ID_BUNDLE, User::class.java)

        tv_name.text = userData?.name
        emailText.text = userData?.email
        foneNumberText.text = userData?.telephone
        imageView.load(userData?.profilePicture) {
            placeholder(R.drawable.ic_group)
            transformations(CircleCropTransformation())
        }

        signOutButton.setOnClickListener {
            sharedRepository.removeValue(USER_ID_BUNDLE)
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

}