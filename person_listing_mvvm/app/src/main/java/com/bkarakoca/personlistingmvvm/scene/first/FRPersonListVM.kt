package com.bkarakoca.personlistingmvvm.scene.first

import androidx.lifecycle.MutableLiveData
import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.base.BaseViewModel
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonUIModel
import com.bkarakoca.personlistingmvvm.domain.person.FetchPersonListUseCase
import com.bkarakoca.personlistingmvvm.internal.extension.launch
import com.bkarakoca.personlistingmvvm.internal.popup.PopupListener
import com.bkarakoca.personlistingmvvm.internal.popup.PopupModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FRPersonListVM @Inject constructor(
    private val fetchPersonListUseCase: FetchPersonListUseCase
) : BaseViewModel() {

    val personList = MutableLiveData<List<PersonUIModel>>(listOf())
    val isPersonListLoading = MutableLiveData(false)
    private val nextPage = MutableLiveData<String?>(null)

    fun initVM() {
        fetchPersonList()
    }

    fun fetchPersonList() = launch {
        fetchPersonListUseCase.execute(
            FetchPersonListUseCase.Params(nextPage.value)
        ).onStart {
            isPersonListLoading.value = true
            showLoading()
        }.onCompletion {
            isPersonListLoading.value = false
            hideLoading()
        }.catch { throwable ->
            handleFetchPersonListFailure(throwable)
        }.collect { personListUIModel ->
            personList.value = personList.value?.plus(personListUIModel.personList)?.distinctBy {
                it.personId
            }
            nextPage.value = personListUIModel.nextPage
        }
    }

    private fun handleFetchPersonListFailure(throwable: Throwable) {
        navigate(
            PopupModel(
                title = resourceProvider.getString(R.string.common_error_title),
                message = throwable.localizedMessage,
                positiveButtonText = resourceProvider.getString(R.string.popup_retry)
            ),
            PopupListener(
                onPositiveButtonClick = {
                    fetchPersonList()
                }
            )
        )
    }

}