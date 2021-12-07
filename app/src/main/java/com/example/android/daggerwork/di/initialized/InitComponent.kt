package com.example.android.daggerwork.di.initialized

import com.example.android.dagger_support.InitializedScope
import com.example.android.daggerwork.domain.models.Labels
import com.example.android.daggerwork.router.Router
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Subcomponent

@InitializedScope
@MergeSubcomponent(InitializedScope::class)
interface InitComponent {
    val labels: Labels
    val router: Router

    @Subcomponent.Builder
    interface Builder {
        fun build(): InitComponent
        fun labelsRepositoryModule(lm: LabelsRepositoryModule): Builder
    }
}
