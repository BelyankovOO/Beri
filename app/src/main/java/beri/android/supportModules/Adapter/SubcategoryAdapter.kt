package beri.android.supportModules.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import beri.android.supportModules.DataService.SubcategoryRow
import beri.android.R
import beri.android.supportModules.DataService.RowType
import beri.android.supportModules.ViewModels.SubcategoryViewModel

class SubcategoryAdapter(private var subcategoryList: ArrayList<SubcategoryViewModel.SubcategorySummaryViewData>?, private val parentActivity: Activity) : RecyclerView.Adapter<SubcategoryAdapter.ViewHolder>(){

    private val chosenHeader = SubcategoryRow(RowType.HEADER, null, "Выбранные")
    private val unchosenHeader = SubcategoryRow(RowType.HEADER, null, "Не выбранные")

    private var subcategoryRowList: ArrayList<SubcategoryRow>? = ArrayList()

    private var numberOfChosen = 0
    private var numberOfUnchosen = 0

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val headerName: TextView? = v.findViewById(R.id.header)
        val subcategoryName: TextView? = v.findViewById(R.id.subcategory_name)
        val swap_button: ImageButton? = v.findViewById(R.id.swap_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val inflatedView : View = when (viewType){
        RowType.SUBCATEGORY.ordinal -> layoutInflater.inflate(R.layout.subcategory_recyclerview_item, parent,false)
        else -> layoutInflater.inflate(R.layout.subcategory_recyclerview_header, parent,false)
    }
    return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return subcategoryRowList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchCategoryList = subcategoryRowList ?: return
        val searchCategory = searchCategoryList[position]
        if(searchCategory.type == RowType.SUBCATEGORY){
            updateImage(holder.swap_button, searchCategory)
            holder.subcategoryName?.text = searchCategory.subcategory?.name
            holder.swap_button?.setOnClickListener {
                swapItems(holder.adapterPosition)
                updateImage(holder.swap_button, searchCategory)
            }
        } else {
            holder.headerName?.text = searchCategory.header
        }
    }

    override fun getItemViewType(position: Int): Int {
        return subcategoryRowList!![position].type.ordinal
    }

    private fun swapItems(fromPosition: Int){
        val item = subcategoryRowList?.get(fromPosition) ?: return
        var toPosition = 0
        if(item.subcategory?.choosen == true){
            item.subcategory?.choosen = false
            subcategoryRowList?.removeAt(fromPosition)
            numberOfUnchosen ++
            numberOfChosen --
            toPosition = subcategoryRowList?.indexOf(unchosenHeader)!! + numberOfUnchosen
            subcategoryRowList?.add((subcategoryRowList?.indexOf(unchosenHeader)!! + numberOfUnchosen), item)
        } else {
            item.subcategory?.choosen = true
            subcategoryRowList?.removeAt(fromPosition)
            numberOfUnchosen --
            numberOfChosen ++
            toPosition = subcategoryRowList?.indexOf(chosenHeader)!! + numberOfChosen
            subcategoryRowList?.add((subcategoryRowList?.indexOf(chosenHeader)!! + numberOfChosen), item)

        }
        this.notifyItemMoved(fromPosition, toPosition)
    }

    private fun prepareList(categoryData: List<SubcategoryViewModel.SubcategorySummaryViewData>){
        subcategoryRowList?.add(chosenHeader)
        subcategoryRowList?.add(unchosenHeader)
        categoryData.map{result ->
            val item = SubcategoryRow(RowType.SUBCATEGORY, result, null )
            if(result.choosen == false){
                numberOfUnchosen ++
                subcategoryRowList?.add((subcategoryRowList?.indexOf(unchosenHeader)!! + numberOfUnchosen), item)
            } else {
                numberOfChosen ++
                subcategoryRowList?.add((subcategoryRowList?.indexOf(chosenHeader)!! + numberOfChosen), item)
            }
        }
    }

    private fun updateImage(widget: ImageButton?, subcategoryRow: SubcategoryRow){
        if(subcategoryRow.subcategory?.choosen == true){
            widget?.setImageResource(R.drawable.minus)
        } else {
            widget?.setImageResource(R.drawable.add)
        }
    }

    fun setSubcategoryData(categoryData: List<SubcategoryViewModel.SubcategorySummaryViewData>){
        prepareList(categoryData)
        this.notifyDataSetChanged()
    }

    fun getChangedList(): ArrayList<SubcategoryViewModel.SubcategorySummaryViewData> {
        val changedList: ArrayList<SubcategoryViewModel.SubcategorySummaryViewData> = ArrayList()
        subcategoryRowList?.let { list ->
            list.map{
                if(it.type == RowType.SUBCATEGORY) {
                    it.subcategory?.let{ subcategory ->
                        changedList.add(subcategory)
                    }
                }
            }
        }
        return changedList
    }

}

