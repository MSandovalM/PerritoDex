package com.sanddev.doggodex.doglist

import com.sanddev.doggodex.models.Dog
import com.sanddev.doggodex.api.ApiResponseStatus
import com.sanddev.doggodex.api.DogsApi.retrofitService
import com.sanddev.doggodex.api.dto.DogDTOMapper
import com.sanddev.doggodex.api.makeNetworkCall

class DogRepository {
    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllDogs()
        val dogDTOList = dogListApiResponse.data.dogs
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
    }
}