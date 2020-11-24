package com.example.placeholder.home.ui

import androidx.lifecycle.MutableLiveData
import com.example.placeholder.common.viewmodel.BaseViewModel
import com.example.placeholder.common.viewmodel.SingleLiveEvent
import com.example.placeholder.home.interactor.HomeInteractor
import com.example.placeholder.home.model.PostType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(
    private val homeInteractor: HomeInteractor
) : BaseViewModel() {

    val postLiveData = SingleLiveEvent<PostType>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun check(postId: Long) {
        homeInteractor.checkPost(postId)
            .doOnSubscribe { loadingLiveData.postValue(true) }
            .doFinally { loadingLiveData.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { postLiveData.value = it },
                onError = {
                    Timber.e(it, "Error getting post: $postId")
                    postLiveData.value = PostType.Error(it)
                }
            )
            .disposeOnCleared()
    }
}