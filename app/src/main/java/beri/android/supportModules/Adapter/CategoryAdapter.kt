package beri.android.supportModules.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import beri.android.R
import beri.android.supportModules.ViewModels.CategoryViewModel
import com.bumptech.glide.Glide

class CategoryAdapter(private var categorySummaryViewList: List<CategoryViewModel.CategorySummaryViewData>?,
                      private val categoryListAdapterListener: CategoryListAdapterListener,
                      private val parentActivity: Activity): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    interface CategoryListAdapterListener{
        fun onShowDetails(categorySummaryViewData: CategoryViewModel.CategorySummaryViewData)
    }

    inner class ViewHolder(v: View, private val categoryListAdapterListener: CategoryListAdapterListener): RecyclerView.ViewHolder(v){
        var categorySummaryViewData: CategoryViewModel.CategorySummaryViewData? = null
        val categoryImage: ImageView = v.findViewById(R.id.category_image)
        val categoryName: TextView = v.findViewById(R.id.categoryName)
        val numberOfChossenSubcategory: TextView = v.findViewById(R.id.numberOfSubcategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false), categoryListAdapterListener)
    }

    override fun getItemCount(): Int {
        return categorySummaryViewList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchViewList = categorySummaryViewList ?: return
        val searchView = searchViewList[position]
        Glide.with(parentActivity)
            .load(searchView.imageUrl)
            .into(holder.categoryImage)
        holder.categoryName.text = searchView.name
        holder.numberOfChossenSubcategory.text = "Выбрано: ${searchView.numberOfChoseenSubcategory}"
        holder.itemView.setOnClickListener {
            categoryListAdapterListener.onShowDetails(searchView)
        }
    }

    fun setCategoryData(categorySummaryViewData: List<CategoryViewModel.CategorySummaryViewData>){
        categorySummaryViewList = categorySummaryViewData ?: return
        this.notifyDataSetChanged()
    }
}