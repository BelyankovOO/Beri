package beri.android.supportModules.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OnboardingCheck(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var status: Boolean = false
)