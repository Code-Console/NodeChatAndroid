package com.robotics.nodechat.kotlin

import android.arch.lifecycle.ViewModel

open class ParentViewModel : ViewModel() {
    var title: String? = null
    var subtitle: String? = null
    var color: Int? = null
}