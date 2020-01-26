package com.github.sulatskovalex.data.common

import com.github.sulatskovalex.domain.ResultValue
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.CompletableDeferred

internal class SingleObserver<O, R>(
    private val deferred: CompletableDeferred<ResultValue<R>>,
    private val mapper: (O) -> R
) :
    StreamObserver<O> {
    override fun onNext(value: O) {
        deferred.complete(ResultValue.success(mapper.invoke(value)))
    }

    override fun onError(t: Throwable) {
        deferred.complete(ResultValue.error(t))
    }

    override fun onCompleted() {}
}