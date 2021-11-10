package com.example.hotspotalk.view.util

import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val value: T) {
    private val pending = AtomicBoolean(true)

    fun getIfPending(): T? {
        return if (pending.compareAndSet(true, false)) {
            value
        }
        else {
            null
        }
    }

    fun peek(): T = value

    fun isPending(): Boolean = pending.get()
}

class EventObserver<T>(private val observe: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getIfPending()?.let { value ->
            observe(value)
        }
    }
}