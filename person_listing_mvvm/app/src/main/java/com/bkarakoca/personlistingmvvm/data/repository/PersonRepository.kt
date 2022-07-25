package com.bkarakoca.personlistingmvvm.data.repository

import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.data.service.local.LocalDataSource
import com.bkarakoca.personlistingmvvm.data.service.local.getNameWithId
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonListUIModel
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonUIModel
import com.bkarakoca.personlistingmvvm.internal.util.ResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface PersonRepository {
    suspend fun fetchPersonList(page: String?): Flow<PersonListUIModel>
}

@ExperimentalCoroutinesApi
class PersonRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val resProvider: ResourceProvider
) : PersonRepository {

    override suspend fun fetchPersonList(page: String?): Flow<PersonListUIModel> = callbackFlow {
        localDataSource.fetch(
            next = page,
            completionHandler = { response, error ->
                when {
                    response != null && response.people.isNotEmpty() -> {
                        trySend(
                            PersonListUIModel(
                                response.people.map {
                                    PersonUIModel(
                                        it.getNameWithId(),
                                        it.id
                                    )
                                },
                                response.next
                            )
                        )
                        close()
                    }
                    error != null -> {
                        close(Exception(error.errorDescription))
                    }
                    else -> {
                        close(Exception(resProvider.getString(R.string.common_error_empty_response)))
                    }
                }

            }
        )

        awaitClose()
    }
}