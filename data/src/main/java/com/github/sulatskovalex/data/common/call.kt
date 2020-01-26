package com.github.sulatskovalex.data.common

import com.github.sulatskovalex.domain.ResultValue
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.CompletableDeferred


internal suspend fun <I, O, R> call(
    f: (I, StreamObserver<O>) -> Unit,
    body: I,
    mapper: (O) -> R
): ResultValue<R> =
    CompletableDeferred<ResultValue<R>>().apply {
        f.invoke(body, SingleObserver(this, mapper))
    }.await()