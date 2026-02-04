package com.sanddev.doggodex.doglist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanddev.doggodex.Dog
import com.sanddev.doggodex.R
import com.sanddev.doggodex.databinding.ActivityDogListBinding

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

        val recycler = binding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()
        recycler.adapter = adapter

        dogListViewModel.dogList.observe(this) { dogList ->
            adapter.submitList(dogList)
        }

    }

}