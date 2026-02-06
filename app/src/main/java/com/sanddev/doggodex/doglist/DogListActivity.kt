package com.sanddev.doggodex.doglist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanddev.doggodex.R
import com.sanddev.doggodex.api.ApiResponseStatus
import com.sanddev.doggodex.databinding.ActivityDogListBinding
import com.sanddev.doggodex.dogdetail.DogDetailActivity
import com.sanddev.doggodex.dogdetail.DogDetailActivity.Companion.DOG_KEY

class DogListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDogListBinding
    private val dogListViewModel: DogListViewModel by viewModels() // asi se instancia un viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val progressBar = binding.loadingWheel

        val recycler = binding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()
        recycler.adapter = adapter

        dogListViewModel.dogList.observe(this) { dogList ->
            adapter.submitList(dogList)
        }

        dogListViewModel.status.observe(this) { status ->
            when(status) {
                is ApiResponseStatus.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is ApiResponseStatus.Success -> {
                    progressBar.visibility = View.GONE
                }
            }
        }

        adapter.setOnItemClickListener { dog ->
            // Pasar el dog a DogDetailActivity
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DOG_KEY, dog)
            startActivity(intent)
        }

    }

}