package com.sanddev.doggodex.doglist

import com.sanddev.doggodex.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository {

    suspend fun downloadDogs(): List<Dog> {
        return withContext(Dispatchers.IO) {
            getFakeDogs()
        }
    }

    private fun getFakeDogs(): MutableList<Dog> {
        val dogList = mutableListOf<Dog>()
        dogList.add(
            Dog(
                1,
                1,
                "Chihuahua",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        dogList.add(
            Dog(
                1,
                1,
                "Labrador",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        dogList.add(
            Dog(
                1,
                1,
                "Retriever",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        dogList.add(
            Dog(
                1,
                1,
                "San Bernardo",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        dogList.add(
            Dog(
                1,
                1,
                "Husky",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        dogList.add(
            Dog(
                1,
                1,
                "Xoloescuincle",
                "Toy",
                5.4,
                6.7,
                "",
                "12 - 15",
                "",
                10.5,
                12.3
            )
        )
        return dogList
    }
}