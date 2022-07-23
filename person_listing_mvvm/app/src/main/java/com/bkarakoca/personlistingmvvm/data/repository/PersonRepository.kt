package com.bkarakoca.personlistingmvvm.data.repository

import com.bkarakoca.personlistingmvvm.data.service.local.LocalDataSource
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonUIModel
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

interface PersonRepository {
    suspend fun fetchPersonList(): List<PersonUIModel>
}

class PersonRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : PersonRepository {

    override suspend fun fetchPersonList(): List<PersonUIModel> {
        return suspendCancellableCoroutine {
            localDataSource.fetch(
                null,
                completionHandler = { response, error ->
                    if (response != null) {
                        response.people.map {
                            PersonUIModel(
                                it.fullName,
                                it.id
                            )
                        }
                    } else {
                        throw Exception(error?.errorDescription)
                    }
                }
            )
        }
    }
}