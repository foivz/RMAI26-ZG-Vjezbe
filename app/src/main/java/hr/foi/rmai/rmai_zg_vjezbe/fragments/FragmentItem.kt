package hr.foi.rmai.rmai_zg_vjezbe.fragments

import kotlin.reflect.KClass

data class FragmentItem(
    val titleRes: Int,
    val iconRes: Int,
    val fragmentClass: KClass<*>
)
