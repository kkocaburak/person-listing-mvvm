package com.bkarakoca.personlistingmvvm.domain.person

import com.bkarakoca.personlistingmvvm.data.repository.PersonRepository
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonListUIModel
import com.bkarakoca.personlistingmvvm.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonListUseCase @Inject constructor(
    private val repository: PersonRepository
) : FlowUseCase<FetchPersonListUseCase.Params, PersonListUIModel>() {

    data class Params(
        val page: String?
    )

    override suspend fun execute(params: Params): Flow<PersonListUIModel> =
        repository.fetchPersonList(params.page)

}