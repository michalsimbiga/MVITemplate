package com.msimbiga.application

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.test(scope: CoroutineScope): TestObserver<T> {
    return TestObserver(scope, this)
}

class TestObserver<T>(
    scope: CoroutineScope,
    flow: Flow<T>
) {
    private val values = mutableListOf<T>()
    private val job: Job = scope.launch {
        flow.collect { values.add(it) }
    }

    fun assertNoValues(): TestObserver<T> {
        assertThat(values).isEqualTo(emptyList<T>())
        return this
    }

    fun assetSize(size: Int): TestObserver<T> {
        assert(values.size == size)
        return this
    }

    fun assertValues(vararg values: T): TestObserver<T> {
        assertThat(this.values).isEqualTo(values.toList())
        return this
    }

    fun finish() {
        job.cancel()
    }
}
