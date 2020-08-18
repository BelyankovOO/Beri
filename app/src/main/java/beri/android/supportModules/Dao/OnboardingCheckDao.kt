package beri.android.supportModules.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import beri.android.supportModules.Model.OnboardingCheck

@Dao
interface OnboardingCheckDao {
    @Query("SELECT * FROM OnboardingCheck")
    fun getOnboardingStatus(): OnboardingCheck?

    @Insert(onConflict = REPLACE)
    fun insertOnboardingStatus(onboardingCheck: OnboardingCheck)
}