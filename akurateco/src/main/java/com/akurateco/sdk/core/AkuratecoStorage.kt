/*
 * Property of Akurateco (https://akurateco.com).
 */

package com.akurateco.sdk.core

import android.content.Context
import android.content.SharedPreferences.Editor
import androidx.core.content.edit

/**
 * The [AkuratecoCredential] values storage.
 *
 * @param context the app context.
 */
internal class AkuratecoStorage(context: Context) {

    companion object {
        private const val AKURATECO_STORAGE = "AKURATECO_STORAGE"
    }

    private val storage = context.getSharedPreferences(AKURATECO_STORAGE, Context.MODE_PRIVATE)

    fun setCredential(key: String, value: String) = storage.edit {
        putString(key, value)
    }

    fun getCredentials(key: String) = storage.getString(key, null).orEmpty()
}
