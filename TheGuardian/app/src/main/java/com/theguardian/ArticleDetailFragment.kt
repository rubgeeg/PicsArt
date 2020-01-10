package com.theguardian


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.theguardian.databinding.FragmentArticleDetailBinding
import com.theguardian.utils.AppDatabase
import com.theguardian.utils.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class ArticleDetailFragment : BaseFragment() {

    lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false)

        val id = arguments?.getString("id")

        binding.data = AppDatabase.getAppDatabase(activity!!).responseDao().getResult(id!!)
        return binding.root
    }


}
