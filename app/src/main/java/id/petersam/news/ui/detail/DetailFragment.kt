package id.petersam.news.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.petersam.news.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val news = DetailFragmentArgs.fromBundle(arguments!!).selectedNews

        binding.viewModel =
            ViewModelProvider(this, DetailViewModelFactory(news)).get(DetailViewModel::class.java)

        return binding.root
    }

}
