package id.petersam.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import id.petersam.news.R
import id.petersam.news.databinding.FragmentHomeBinding
import id.petersam.news.util.showSnackBar
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, HomeViewModelFactory(activity.application))
            .get(HomeViewModel::class.java)
    }

    private var newsListAdapter: NewsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        newsListAdapter = NewsListAdapter(NewsListAdapter.NewsListListener {
            viewModel.navigateToNewsDetail(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.newsRv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        viewModel.navigateToSelectedNews.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.onNewsDetailNavigated()
            }
        })

        handleOnChipClicked()

        return binding.root
    }

    private fun handleOnChipClicked() {
        var lastCheckedId = binding.chipId.id
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            group.findViewById<Chip>(lastCheckedId).isEnabled = true
            if (checkedId == View.NO_ID) {
                group.check(lastCheckedId)
                return@setOnCheckedChangeListener
            }

            lastCheckedId = checkedId
            val chip = group.findViewById<Chip>(lastCheckedId)
            chip.isEnabled = false
            viewModel.refreshDataFromRepository(chip.tag.toString())
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            rootView.showSnackBar("Network Error")
            viewModel.onNetworkErrorShown()
        }
    }

}
