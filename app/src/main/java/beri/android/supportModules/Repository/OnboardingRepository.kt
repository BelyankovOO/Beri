package beri.android.supportModules.Repository

import beri.android.supportModules.Dao.OnboardingCheckDao
import beri.android.supportModules.Model.OnboardingCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OnboardingRepository(private val onboardingCheckDao: OnboardingCheckDao) {

    fun updateStatusOfOnboarding(status: Boolean){
        val onboardingCheck = OnboardingCheck(null, status)
        GlobalScope.launch {
            onboardingCheckDao.insertOnboardingStatus(onboardingCheck)
        }
    }

    fun checkStatusOfOnboarding(callback: (Boolean?) -> Unit){
        GlobalScope.launch {
            val status = onboardingCheckDao.getOnboardingStatus()?.status
            GlobalScope.launch(Dispatchers.Main) {
                callback(status)
            }
        }
    }

}