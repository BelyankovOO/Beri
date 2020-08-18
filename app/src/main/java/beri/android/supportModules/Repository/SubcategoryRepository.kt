package beri.android.supportModules.Repository

import androidx.lifecycle.LiveData
import beri.android.supportModules.Dao.SubcategoryDao
import beri.android.supportModules.Model.Subcategory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SubcategoryRepository(private val subcategoryDao: SubcategoryDao) {

    fun subcategoriesByCategoryIdFromDatabase(categoryId: String): LiveData<List<Subcategory>>?{
        return subcategoryDao.loadSubcategoriesByCategoryId(categoryId)
    }

    fun updateSubcategories(subcategoryList: List<Subcategory>){
        GlobalScope.launch {
            subcategoryDao.updateSubcategories(subcategoryList)
        }
    }

    fun updateChosenById(id: String, newChosen: Boolean){
        GlobalScope.launch {
            subcategoryDao.updateChosenById(id,newChosen)
        }
    }

}