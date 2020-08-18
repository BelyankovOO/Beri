package beri.android.modules.MainModule

import beri.android.supportModules.Dao.OnboardingCheckDao
import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.Model.User
import beri.android.supportModules.Repository.OnboardingRepository
import beri.android.supportModules.Repository.UserRepository
import beri.android.supportModules.Retrofit.RetrofitInstance
import beri.android.supportModules.service.UserService


class MainInteractor(private val activity: MainActivity): MainContract.Interactor {
    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepository
    private lateinit var tokenDao: TokenDao
    private lateinit var onboardingRepository: OnboardingRepository
    private lateinit var onboardingCheckDao: OnboardingCheckDao

    override fun setup() {
        onboardingCheckDao = BeriDatabase.getInstance(activity).OnboardingCheckDao()
        tokenDao = BeriDatabase.getInstance(activity).TokenDao()
        userService = UserService(RetrofitInstance.instance(activity))
        userRepository = UserRepository(userService, tokenDao)
        onboardingRepository = OnboardingRepository(onboardingCheckDao)
    }

    override fun checkUserAuthority(callback: (User?) -> Unit) {
        userRepository.getUser(){
            callback(it)
        }
    }

    override fun checkOnboardingStatus(callback: (Boolean?) -> Unit){
        onboardingRepository.checkStatusOfOnboarding {
            println("status $it")
            callback(it)
        }
    }

    override fun setOnboardingStatus(status: Boolean){
        onboardingRepository.updateStatusOfOnboarding(status)
    }

}