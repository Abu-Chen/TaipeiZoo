package com.abu.taipeizoo.extension

val Any.TAG: String
    get() {
        val tag = "ABu-${javaClass.simpleName}"
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }