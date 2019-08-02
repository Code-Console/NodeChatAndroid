package com.robotics.nodechat.kotlin

import android.arch.lifecycle.ViewModel

    class ChildViewModel : ParentViewModel() {
        init {
            title = "Title"
            subtitle = "Sub Title"
            color = -123454
            // I need to access ParentViewModel and update title, subtitle and color here.
        }
    }