package com.sanddev.doggodex

import android.util.Patterns

public fun validEmail(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}