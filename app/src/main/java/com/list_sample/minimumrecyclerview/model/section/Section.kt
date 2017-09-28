package com.list_sample.minimumrecyclerview.model.section

/**
 * Created by monkey on 2017/09/28.
 */
interface Section {
    val id: Int
    fun count(): Int
    fun itemAt(row: Int): Any
}