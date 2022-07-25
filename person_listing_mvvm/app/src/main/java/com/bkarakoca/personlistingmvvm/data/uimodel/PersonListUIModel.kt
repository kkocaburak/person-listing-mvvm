package com.bkarakoca.personlistingmvvm.data.uimodel

data class PersonListUIModel(
    val personList: List<PersonUIModel>,
    val nextPage: String? = null
)