package org.piramalswasthya.cho.database.room.typeconverters

import androidx.room.TypeConverter

class FloatListConverters {

        @TypeConverter
        fun fromFloatList(value: List<Float>?): String? {
            return value?.joinToString(separator = ",")
        }

        @TypeConverter
        fun toFloatList(value: String?): List<Float>? {
            return value?.split(",")?.map { it.toFloat() }
        }

}