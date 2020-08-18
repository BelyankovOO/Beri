package beri.android.modules.CategoryModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import beri.android.R
import beri.android.supportModules.Adapter.CategoryAdapter
import beri.android.supportModules.ViewModels.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity: AppCompatActivity(), CategoryAdapter.CategoryListAdapterListener, CategoryContract.View {
    companion object{
        fun launch(context: Context){
            val intent = Intent(context, CategoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    var configurator: CategoryContract.Configurator? = null
    var presenter: CategoryContract.Presenter? = null
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryListAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if(configurator == null) configurator = CategoryConfigurator()
        configurator?.configurate(this)

        categoryRecyclerView = findViewById(R.id.category_recyclerview)
        categoryRecyclerView.layoutManager = GridLayoutManager(this, 2)
        categoryListAdapter = CategoryAdapter(null, this, this)
        categoryRecyclerView.adapter = categoryListAdapter

        presenter?.viewDidLoad()
    }

    override fun onShowDetails(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData) {
        presenter?.categoryClicked(categorySummaryViewData)
    }

    override fun setDataToAdapter(categorySummaryViewList: List<CategoryViewModel.CategorySummaryViewData>) {
        categoryListAdapter.setCategoryData(categorySummaryViewList)
    }


    override fun showProgressBar() {
        categoryRecyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        categoryRecyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}