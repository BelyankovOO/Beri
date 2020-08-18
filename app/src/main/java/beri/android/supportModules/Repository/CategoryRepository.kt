package beri.android.supportModules.Repository

import androidx.lifecycle.LiveData
import beri.android.supportModules.Dao.SubcategoryDao
import beri.android.supportModules.DataService.CategoryRequest
import beri.android.supportModules.Help.I18N
import beri.android.supportModules.Model.Category
import beri.android.supportModules.Model.Image
import beri.android.supportModules.Model.Subcategory
import beri.android.supportModules.Model.Thumbnail
import beri.android.supportModules.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryRepository(private val subcategoryDao: SubcategoryDao, private val categoryService: CategoryService) {

    fun getCategories(callback: (List<Category?>) -> Unit ) {
        categoryService.getCategory { result ->
           val searchViews = result.map{
               categoryRequestToCategory(it)
           }
            callback(searchViews)
        }
    }

    private fun categoryRequestToCategory(categoryResponce: CategoryRequest?): Category?{
        val subcategory = categoryResponce?.subcategories ?: return null
        val i18n = categoryResponce.i18n ?: return null
        val image = categoryResponce.image ?: return null
        return Category(categoryResponce.id, imageRequestToImage(image), categoryResponce.name, subcategoryRequestToSubcategory(subcategory))

    }

    private fun subcategoryRequestToSubcategory(subcategoryResponce: List<CategoryRequest.SubcategoryRequest>): List<Subcategory>{
        return subcategoryResponce.map{
            Subcategory(
                it.categoryId ?: "",
                it.id ?: "",
                it.name ?: "",
                chosen = false
            )
        }
    }

    private fun i18nRequestToI18N(i18nResponce: List<CategoryRequest.I18NRequest>): List<I18N>{
        return i18nResponce.map{
            I18N(
                it.languageCode ?: "",
                it.value ?: ""
            )
        }
    }

    private fun imageRequestToImage(imageResponce: CategoryRequest.ImageRequest): Image {
        val thumbnail = imageResponce.thumbnails
        return Image(
            imageResponce.id ?: "",
            thumbnailsRequestToThumbnaisl(thumbnail!!)
        )
    }

    private fun thumbnailsRequestToThumbnaisl(thumbnailstResponce: MutableList<CategoryRequest.ImageRequest.ThumbnailRequest>): List<Thumbnail>{
        return thumbnailstResponce.map{
            Thumbnail(
                it.id ?: "",
                it.url ?: ""
            )
        }
    }

    fun saveSubcategoryToDatabaseFromCategory(category: Category){
        GlobalScope.launch {
            for (subcategory in category.subcategories) {
                subcategoryDao.insertSubcategory(subcategory)
            }
        }
    }

    fun insertSubcategories(subcategories: List<Subcategory>){
        GlobalScope.launch {
            subcategoryDao.insertSubcategories(subcategories)
        }
    }

    fun getAllSubcategoryFromDatabase(): List<Subcategory>?{
        return subcategoryDao.loadAllSubcategories()
    }

    fun checkNewSubcategories(categoryId: String?, subcategoriesFromServer: List<Subcategory>?, callback: (List<Subcategory>?) -> Unit) {
        GlobalScope.launch {
            var newSubcategories: List<Subcategory>? = null
            if (subcategoriesFromServer != null) {
                categoryId?.let {
                    val subcategoriesFromDatabase = subcategoryDao.loadSubcategoriesByCategoryIdLocal(categoryId)
                    newSubcategories = subcategoriesFromServer.filter { subcategory ->
                        subcategoriesFromDatabase?.find { subcategory.id == it.id } == null
                    }
                }
            }
            GlobalScope.launch(Dispatchers.Main) {
                callback(newSubcategories)
            }
        }
    }


}