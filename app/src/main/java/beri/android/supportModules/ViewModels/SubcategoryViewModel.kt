package beri.android.supportModules.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Repository.SubcategoryRepository
import beri.android.supportModules.service.SubcategoryService

class SubcategoryViewModel(application: Application): AndroidViewModel(application) {
    private var subcategoriesLiveData: LiveData<List<SubcategorySummaryViewData>>? = null
    var subcategoryRepository: SubcategoryRepository? = null

    data class SubcategorySummaryViewData(
        var id: String? = "",
        var name: String? = "",
        var choosen: Boolean? = false
    )

    private fun subcategoryToSubcategoryView(subcategory: Subcategory?): SubcategorySummaryViewData{
        return SubcategorySummaryViewData(
            subcategory?.id,
            subcategory?.name,
            subcategory?.chosen
        )
    }

    private fun subcategoryViewToSubcategoryWithCategoryId(categoryId: String, subcategoryView: SubcategorySummaryViewData?): Subcategory{
        return Subcategory(
            categoryId,
            subcategoryView?.id ?: "",
            subcategoryView?.name ?: "",
            subcategoryView?.choosen ?: false

        )
    }

    fun searchSubcategoriesFromDatabase(categoryId: String): LiveData<List<SubcategorySummaryViewData>>?{
        val subcategoryRepository = subcategoryRepository ?: return null
        if(subcategoriesLiveData == null){
            val subcategories = subcategoryRepository.subcategoriesByCategoryIdFromDatabase(categoryId)
            subcategories?.let {
                subcategoriesLiveData = Transformations.map(subcategories) {
                    it.map {
                        subcategoryToSubcategoryView(it)
                    }
                }
            }
        }
        return subcategoriesLiveData
    }

    fun updateSubcategoriesInDatabaseByCategoryId(categoryId: String, subcategoryList: List<SubcategorySummaryViewData>?){
        val subcategoryRepository = subcategoryRepository ?: return
        if(subcategoryList != null){
            val subcategory = subcategoryList.map{
                subcategoryViewToSubcategoryWithCategoryId(categoryId, it)
            }
            subcategoryRepository.updateSubcategories(subcategory)
        }
    }

    fun updateSubcategoryChosenById(subcategoryList: List<SubcategorySummaryViewData>?){
        val subcategoryRepository = subcategoryRepository ?: return
        if(subcategoryList != null){
            subcategoryList.map{subcategory ->
                    subcategoryRepository.updateChosenById(subcategory.id!!, subcategory.choosen!!)
            }
        }
    }

}