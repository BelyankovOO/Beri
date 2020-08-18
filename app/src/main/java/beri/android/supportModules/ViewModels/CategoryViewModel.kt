package beri.android.supportModules.ViewModels

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import beri.android.supportModules.DataService.CategoryRequest
import beri.android.supportModules.Help.I18N
import beri.android.supportModules.Model.Category
import beri.android.supportModules.Model.Image
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.Thumbnail
import beri.android.supportModules.Repository.CategoryRepository
import beri.android.supportModules.service.CategoryService
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application): AndroidViewModel(application) {
    var categoryRepository: CategoryRepository? = null

    @Parcelize
    data class CategorySummaryViewData(
        var id: String? = "",
        var name: String? = "",
        var numberOfChoseenSubcategory: Int? = 0,
        var imageUrl: String? = ""
    ): Parcelable

    private fun categoryToCategorySummaryViewData(category: Category?): CategorySummaryViewData {
        return CategorySummaryViewData(
            category?.id,
            category?.name,
            0,
            category?.image?.thumbnails?.get(5)?.url
        )
    }

    fun searchCategories(callback: (List<CategorySummaryViewData>?) -> Unit){
        val categoryRepository = categoryRepository ?: return
        categoryRepository.getCategories { result ->
            val searchViews = result.map{
                it.let {
                    val newSubcategories = categoryRepository.checkNewSubcategories(it?.id, it?.subcategories){
                        it?.let{
                            //println("New subcategories $it")
                            categoryRepository.insertSubcategories(it)
                        }
                    }
                    categoryToCategorySummaryViewData(it)
                }
            }
            callback(searchViews)
        }
    }

}