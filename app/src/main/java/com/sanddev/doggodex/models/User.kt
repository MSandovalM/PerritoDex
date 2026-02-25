package com.sanddev.doggodex.models

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.sanddev.doggodex.AUTH_PREFS
import com.sanddev.doggodex.EMAIL_KEY
import com.sanddev.doggodex.ID_KEY
import com.sanddev.doggodex.ID_TOKEN_KEY

class User(
    val id: Long,
    val email: String,
    val token: String,
) {
    companion object {
        fun setLoggedInUser(activity: Activity, user: User) {
            activity.getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE).also {
                it.edit {
                    putLong(ID_KEY, user.id)
                        .putString(EMAIL_KEY, user.email)
                        .putString(ID_TOKEN_KEY, user.token)
                }
            }
        }

        fun getLoggedInUser(activity: Activity): User? {
            val prefs = activity.getSharedPreferences(
                AUTH_PREFS,
                Context.MODE_PRIVATE
            ) ?: return null

            val userId = prefs.getLong(ID_KEY, 0L)
            if (userId == 0L) return null

            return User(
                userId,
                prefs.getString(EMAIL_KEY, "") ?: "",
                prefs.getString(ID_TOKEN_KEY, "") ?: ""
            )
        }

        fun clearUserSession(activity: Activity) {
            activity.getSharedPreferences(AUTH_PREFS, MODE_PRIVATE).also {
                it.edit {
                    clear()
                }
            }
        }

    }
}