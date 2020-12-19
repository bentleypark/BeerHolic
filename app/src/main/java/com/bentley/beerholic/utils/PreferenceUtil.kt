package com.bentley.beerholic.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

object PreferenceUtil {
    fun getPref(context: Context) =
        context.getSharedPreferences(PreferenceUtil.javaClass.name, Context.MODE_PRIVATE)
}

class SharedPreferenceManager @Inject constructor(private val pref: SharedPreferences) {
    fun getLastQuery() = pref.getString(KEY_LAST_QUERY, "")

    fun setLastQuery(query: String) {
        pref.edit { putString(KEY_LAST_QUERY, query) }
    }

    companion object {
        const val KEY_LAST_QUERY = "lastQuery"
    }
}
