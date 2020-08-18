package beri.android.supportModules.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import beri.android.R
import beri.android.supportModules.DataService.*
import beri.android.supportModules.ViewModels.CategoryViewModel
import beri.android.supportModules.ViewModels.ProfileViewModel

class ProfileAdapter(private val profileAdapterListener: ProfileAdapterListener, private val parentActivity: Activity): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var profileRowList: ArrayList<ProfileRow>? = ArrayList()

    interface ProfileAdapterListener{
        fun onSignOut()
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val signOutImage: ImageView? = v.findViewById(R.id.sign_out_image)
        val phoneNumber: TextView? = v.findViewById(R.id.profile_phone)
        val phoneDescription: TextView? = v.findViewById(R.id.phone_despription)
        val profileButton: Button? = v.findViewById(R.id.sign_out_button)
    }

    override fun getItemViewType(position: Int): Int {
        return profileRowList!![position].type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val inflatedView : View = when (viewType) {
            ProfileRowType.IMAGE.ordinal -> layoutInflater.inflate(
                R.layout.sign_out_recyclerview_image,
                parent,
                false)
            ProfileRowType.ITEM.ordinal -> layoutInflater.inflate(
                R.layout.sign_out_recyclerview_item,
                parent,
                false)
            else -> layoutInflater.inflate(R.layout.sign_out_recyclerview_button, parent, false)
        }
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
       //println("123 ${profileRowList?.size}")
       return profileRowList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchCategoryList = profileRowList ?: return
        val searchCategory = searchCategoryList[position]
        when(searchCategory.type){
            ProfileRowType.IMAGE -> {
                holder.signOutImage?.setImageResource(R.drawable.saturn)
            }
            ProfileRowType.ITEM -> {
                holder.phoneNumber?.text = searchCategory.item?.phoneNumber
                holder.phoneDescription?.text = searchCategory.item?.phoneDescription
            }
            else -> {
                holder.profileButton?.text = searchCategory.button?.buttonText
                holder.profileButton?.setOnClickListener {
                    profileAdapterListener.onSignOut()
                }
            }
        }
    }

    fun setupStyleList(userSummaryViewData: ProfileViewModel.ProfileSummaryViewData){
        profileRowList?.add(ProfileRow(ProfileRowType.IMAGE, userSummaryViewData.imageUrl, null, null))
        profileRowList?.add(ProfileRow(ProfileRowType.ITEM, null, ProfileItem(userSummaryViewData.phoneNumber, "Телефон"), null))
        profileRowList?.add(ProfileRow(ProfileRowType.BUTTON, null, null, ProfileButton("Выход")))
        //println("123 ${profileRowList}")
        this.notifyDataSetChanged()
    }

}