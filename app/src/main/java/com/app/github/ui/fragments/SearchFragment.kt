package com.app.github.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.github.R
import com.app.github.databinding.FragmentSearchBinding
import com.app.github.models.GithubSearchModel
import com.app.github.ui.viewmodels.SearchViewModel
import com.app.github.ui.adapters.SearchAdapter
import com.app.github.ui.adapters.SearchLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.OnItemClickListener {

    private val viewModel by viewModels<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter = SearchAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        initViews()
    }

    private fun initViews() {

        setupRecyclerView()

        viewModel.photos.observe(viewLifecycleOwner) { data: PagingData<GithubSearchModel> ->
            adapter.submitData(viewLifecycleOwner.lifecycle, data)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnReload.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading
                        && loadState.append.endOfPaginationReached
                        && adapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun setupRecyclerView() {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = SearchLoadStateAdapter { adapter.retry() },
                    footer = SearchLoadStateAdapter { adapter.retry() }
            )

            btnReload.setOnClickListener {
                adapter.retry()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_photos, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                if (searchQuery != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchPhotos(searchQuery)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(githubSearchItem: GithubSearchModel) {
        val action = SearchFragmentDirections.actionPhotosFragmentToPhotoViewFragment(githubSearchItem)
        findNavController().navigate(action)
    }
}