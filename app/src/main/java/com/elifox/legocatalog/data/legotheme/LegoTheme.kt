package com.elifox.legocatalog.data.legotheme

data class LegoTheme(
        val id: Int,
        val name: String,
        val parentId: Int? = null
) {
    override fun toString() = name
}