package beri.android.supportModules.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import beri.android.supportModules.Help.I18N

@Entity
data class Subcategory(
    var categoryId: String = "",
    //var createdAt: String,
    //var createdBy: String,
    //var i18n: MutableList<I18N>? = null,
    @PrimaryKey var id: String = "",
    //var lastModifiedAt: String,
    //var lastModifiedBy: String,
    var name: String = "",
    var chosen: Boolean = false
)