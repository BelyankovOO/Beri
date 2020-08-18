package beri.android.modules.SubcategoryModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import beri.android.R
import beri.android.supportModules.Adapter.SubcategoryAdapter
import beri.android.supportModules.ViewModels.CategoryViewModel
import beri.android.supportModules.ViewModels.SubcategoryViewModel

import com.bumptech.glide.Glide



class SubcategoryActivity: AppCompatActivity(), SubcategoryContract.View {
    companion object{
        const val INTENT_ID_KEY = "category"
        fun launch(context: Context, category: CategoryViewModel.CategorySummaryViewData){
            val intent = Intent(context, SubcategoryActivity::class.java)
            intent.putExtra(INTENT_ID_KEY, category)
            context.startActivity(intent)
        }
    }

    var configurator: SubcategoryContract.Configurator? = null
    var presenter: SubcategoryContract.Presenter? = null
    private lateinit var categoryName: TextView
    private lateinit var categoryImage: ImageView
    private lateinit var categoryDescription: TextView
    //private lateinit var backButton: ImageButton
    lateinit var subcategoryRecyclerView: RecyclerView
    private lateinit var subcategoryListAdapter: SubcategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory)

        if(configurator == null) configurator = SubcategoryConfigurator()
        configurator?.configurate(this)

        categoryName = findViewById(R.id.category_name)
        categoryDescription = findViewById(R.id.category_description)
        categoryImage = findViewById(R.id.category_image)
        //backButton = findViewById(R.id.back_button)
        subcategoryRecyclerView = findViewById(R.id.subcategory_recyclerview)

        val category = intent.getParcelableExtra(INTENT_ID_KEY) as CategoryViewModel.CategorySummaryViewData
        category.id?.let {
            presenter?.viewDidLoad(it)
        }

        categoryName.text = category.name
        categoryDescription.text =  getString(R.string.category_description)
        Glide.with(this)
            .load(category.imageUrl)
            .into(categoryImage)
        subcategoryRecyclerView.layoutManager = LinearLayoutManager(this)
        subcategoryListAdapter = SubcategoryAdapter(null, this)
        subcategoryRecyclerView.adapter = subcategoryListAdapter
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        configurator = null
        presenter = null
        super.onDestroy()
    }

    override fun showProgressBar() {
        //progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        //progressBar.visibility = View.INVISIBLE
    }

    override fun onShowMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setDataInAdapter(subcategoryList: List<SubcategoryViewModel.SubcategorySummaryViewData>) {
        subcategoryListAdapter.setSubcategoryData(subcategoryList)
    }

    override fun getDataFromAdapter(): List<SubcategoryViewModel.SubcategorySummaryViewData> {
        return subcategoryListAdapter.getChangedList()
    }
}