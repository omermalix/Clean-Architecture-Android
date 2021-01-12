package com.elifox.legocatalog.di

import com.elifox.legocatalog.util.VerticalItemDecoration
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class VerticalItemDecorateAI @AssistedInject constructor(@Assisted dp: Int): VerticalItemDecoration(dp, true) {

    @AssistedInject.Factory
    interface Factory {
        fun create(dp: Int): VerticalItemDecoration
    }
}

