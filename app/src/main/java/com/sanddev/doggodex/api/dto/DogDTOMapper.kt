package com.sanddev.doggodex.api.dto

import com.sanddev.doggodex.Dog

class DogDTOMapper {

    fun fromDogDTOToDogDomain(dogDTO: DogDTO): Dog {
        dogDTO.apply {
            return Dog(
                id,
                index,
                name,
                type,
                heightFemale,
                heightMale,
                imageUrl,
                lifeExpectancy,
                temperament,
                weightFemale,
                weightMale
            )
        }
    }

    fun fromDogDTOListToDogDomainList(dogDTOList: List<DogDTO>): List<Dog> {
        return dogDTOList.map { fromDogDTOToDogDomain(it) }
    }
}
