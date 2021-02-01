/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sample.app

import android.app.Application
import com.akurateco.sdk.core.AkuratecoSdk
import io.kimo.lib.faker.Faker

class AkuratecoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Faker.with(this)
        AkuratecoSdk.init(this)
    }
}
