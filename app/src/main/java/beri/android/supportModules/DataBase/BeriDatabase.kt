package beri.android.supportModules.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import beri.android.supportModules.Dao.OnboardingCheckDao
import beri.android.supportModules.Dao.SubcategoryDao
import beri.android.supportModules.Dao.TokenDao
import beri.android.supportModules.Model.OnboardingCheck
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.Token

@Database(entities = arrayOf(Subcategory::class, Token::class, OnboardingCheck::class), version = 5)
abstract class BeriDatabase : RoomDatabase() {
    abstract fun SubcategoryDao(): SubcategoryDao
    abstract fun TokenDao(): TokenDao
    abstract fun OnboardingCheckDao(): OnboardingCheckDao
    companion object {
        private var instance: BeriDatabase? = null
        fun getInstance(context: Context): BeriDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, BeriDatabase::class.java, "Beri")
                    //.addMigrations(MigrationClass)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as BeriDatabase
        }
    }
}

