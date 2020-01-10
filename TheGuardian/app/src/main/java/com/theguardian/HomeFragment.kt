package com.theguardian


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theguardian.databinding.FragmentHomeBinding
import com.theguardian.livedata.NewsListLiveData
import com.theguardian.models.Result
import com.theguardian.utils.AppDatabase
import com.theguardian.utils.BaseFragment
import com.theguardian.utils.BaseListAdapter

class HomeFragment : BaseFragment(), BaseListAdapter.OnListItemClickListener<Result> {

    lateinit var baseLiveData: NewsListLiveData
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: BaseListAdapter<Result>
    var newsList: ArrayList<Result>? = null
    lateinit var db: AppDatabase
    var pageNum = 1
    var fetchingNews = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseLiveData = ViewModelProviders.of(activity!!).get(NewsListLiveData::class.java)

        db = AppDatabase.getAppDatabase(activity!!)
        baseLiveData.baseData.observe(this, Observer {
            if (newsList == null) {
                newsList = it.response.results
                fillDataIntoList()
            } else {
                newsList?.addAll(it.response.results)
                adapter.notifyDataSetChanged()
            }
            db.responseDao().insertResults(it.response.results)
            fetchingNews = false
        })
    }

    private fun fillDataIntoList() {
        adapter =
            BaseListAdapter<Result>(R.layout.layout_news_list_item)
        adapter.onListItemClickListener = this
        adapter.submitList(newsList)
        binding.newsList.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        if (db.responseDao().getResults().size > 0) {
            newsList = java.util.ArrayList()
            newsList?.addAll(db.responseDao().getResults())
            pageNum = newsList?.size!! / 10
            fillDataIntoList()
        } else
            fetchNews()

        binding.newsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!fetchingNews && newsList != null && (binding.newsList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() >= newsList?.size!! - 1) {
                    pageNum++
                    fetchNews()
                }
            }
        })
        return binding.root
    }

    private fun fetchNews() {
        fetchingNews = true
        baseLiveData.searchNews(pageNum)
    }

    override fun onItemClick(view: View, itemData: Result) {
        val bundle = bundleOf("id" to itemData.id)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_articleDetailFragment, bundle)
    }


}
