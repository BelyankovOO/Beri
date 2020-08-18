package beri.android.modules.CategoryModule

import androidx.lifecycle.ViewModelProvider
import beri.android.supportModules.Dao.SubcategoryDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.Repository.CategoryRepository
import beri.android.supportModules.ViewModels.CategoryViewModel
import beri.android.supportModules.service.CategoryService
import beri.android.supportModules.Retrofit.RetrofitInstance

class CategoryInteractor(private var activity: CategoryActivity): CategoryContract.Interactor{

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var subcategoryDao: SubcategoryDao
    private lateinit var categoryService: CategoryService

    override fun setup(){
        categoryService = CategoryService(RetrofitInstance.instance(activity))
        subcategoryDao = BeriDatabase.getInstance(activity).SubcategoryDao()
        categoryViewModel = ViewModelProvider(activity).get(CategoryViewModel::class.java)
        categoryViewModel.categoryRepository = CategoryRepository(subcategoryDao, categoryService)
    }

    override fun loadCategories(callback: (List<CategoryViewModel.CategorySummaryViewData>?) -> Unit){
        categoryViewModel.searchCategories{ result ->
            callback(result)
        }
    }

}