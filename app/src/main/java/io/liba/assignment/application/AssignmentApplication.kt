package io.liba.assignment.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.liba.assignment.common.HyperlinkedDebugTree
import timber.log.Timber

@HiltAndroidApp
class AssignmentApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(HyperlinkedDebugTree())
    }
}
