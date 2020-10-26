package edu.stanford.dstratak.mymaps.models

import java.io.Serializable
import java.util.*

data class UserMap(val title: String, val places: List<Place>, val createTime: Date) : Serializable