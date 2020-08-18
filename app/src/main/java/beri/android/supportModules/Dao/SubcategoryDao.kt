package beri.android.supportModules.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import beri.android.supportModules.Model.Category
import beri.android.supportModules.Model.Subcategory

@Dao
interface SubcategoryDao {
    @Query("SELECT * FROM Subcategory WHERE categoryId = :categoryId")
    fun loadSubcategoriesByCategoryId(categoryId: String): LiveData<List<Subcategory>>?

    @Query("SELECT * FROM Subcategory WHERE categoryId = :categoryId")
    fun loadSubcategoriesByCategoryIdLocal(categoryId: String): List<Subcategory>?

    @Query("SELECT * FROM Subcategory ORDER BY categoryId")
    fun loadAllSubcategories(): List<Subcategory>?

    @Query("UPDATE Subcategory SET chosen = :newChosen WHERE id = :id")
    fun updateChosenById(id: String , newChosen: Boolean)

    @Insert(onConflict = REPLACE)
    fun insertSubcategory(subcategory: Subcategory)

    @Insert(onConflict = REPLACE)
    fun insertSubcategories(subcategories: List<Subcategory>)

    @Update()
    fun updateSubcategories(subcategories: List<Subcategory>)
}
