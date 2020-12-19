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

    fun getIsNewQuery() = pref.getBoolean(KEY_IS_NEW_QUERY, false)

    fun setIsNewQueryStatus(state: Boolean) {
        pref.edit { putBoolean(KEY_IS_NEW_QUERY, state) }
    }

    fun getNextPage() = pref.getInt(KEY_NEXT_PAGE, 1)

    fun setNextPages(page: Int) {
        pref.edit { putInt(KEY_NEXT_PAGE, page) }
    }

    fun getLastQuery() = pref.getString(KEY_LAST_QUERY, "")

    fun setLastQuery(query: String) {
        pref.edit { putString(KEY_LAST_QUERY, query) }
    }

    companion object {
        const val KEY_IS_NEW_QUERY = "isNewQuery"
        const val KEY_NEXT_PAGE = "nextPage"
        const val KEY_LAST_QUERY = "lastQuery"
    }
}
