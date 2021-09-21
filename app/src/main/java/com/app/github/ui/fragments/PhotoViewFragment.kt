package com.app.github.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.app.github.R
import com.app.github.databinding.FragmentPhotoViewBinding
import com.app.github.models.GithubSearchModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_photo_view.view.*

class PhotoViewFragment : Fragment(R.layout.fragment_photo_view) {

    private lateinit var bitmap: Bitmap
    private val args by navArgs<PhotoViewFragmentArgs>()

    private lateinit var binding: FragmentPhotoViewBinding
    private lateinit var githubSearchItem: GithubSearchModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoViewBinding.bind(view)
        githubSearchItem = args.photo
        bindViews()
    }

    private fun bindViews() {

        with(binding)
        {
            apply {

                Glide.with(this@PhotoViewFragment)
                        .load(githubSearchItem.avatarUrl)
                        .error(R.drawable.ic_error)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                                progressBar.isVisible = false
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                progressBar.isVisible = false
                                bitmap = (resource as BitmapDrawable).bitmap
                                return false
                            }
                        })
                        .into(binding.imageView)
            }
        }
        binding.data = githubSearchItem
    }

}