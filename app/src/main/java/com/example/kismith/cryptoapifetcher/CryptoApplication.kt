package com.example.kismith.cryptoapifetcher

import android.app.Application
import android.util.Log
import com.zendesk.logger.Logger
import zendesk.core.AnonymousIdentity
import zendesk.core.Identity
import zendesk.core.Zendesk
import zendesk.support.Support

/**
 * Setup for global singletons Zendesk, Retrofit (and Gson if necessary).
 * */

class CryptoApplication : Application() {

   override fun onCreate() {
        super.onCreate()

        Logger.setLoggable(true)

        Zendesk.INSTANCE.init(this,
                "https://z3ntestkillian.zendesk.com",
                "839120bafb694e1872254c3aecbe5edd6220166726ee554b",
                "mobile_sdk_client_4bbb98d0fe4fd4a99cc1")

        val identity: Identity = AnonymousIdentity()
        Zendesk.INSTANCE.setIdentity(identity)

        Support.INSTANCE.init(Zendesk.INSTANCE)
        Log.i("CryptoApplication", "Zendesk created")
    }
}
