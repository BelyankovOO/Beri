package beri.android.modules.SubcategoryModule

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import beri.android.supportModules.Dao.SubcategoryDao
import beri.android.supportModules.DataBase.BeriDatabase
import beri.android.supportModules.Repository.SubcategoryRepository
import beri.android.supportModules.ViewModels.SubcategoryViewModel

class SubcategoryInteractor(private var activity: SubcategoryActivity): SubcategoryContract.Interactor {
    private lateinit var subcategoryViewModel: SubcategoryViewModel
    private lateinit var subcategoryDao: SubcategoryDao

    override fun setup(){
        subcategoryDao = BeriDatabase.getInstance(activity).SubcategoryDao()
        subcategoryViewModel = ViewModelProvider(activity).get(SubcategoryViewModel::class.java)
        subcategoryViewModel.subcategoryRepository = SubcategoryRepository(subcategoryDao)
    }

    override fun loadSubcategories(categoryId: String, callback: (List<SubcategoryViewModel.SubcategorySummaryViewData>?) -> Unit) {
        subcategoryViewModel.searchSubcategoriesFromDatabase(categoryId)?.observe(activity, Observer<List<SubcategoryViewModel.SubcategorySummaryViewData>> {
            it?.let {
                callback(it)
            }
        })
    }

    override fun updateDataInDatabaseCheck(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>){
        subcategoryViewModel.updateSubcategoryChosenById(subcategoryList)
    }


}