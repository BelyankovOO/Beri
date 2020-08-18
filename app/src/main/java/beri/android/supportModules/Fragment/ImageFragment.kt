package beri.android.supportModules.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import beri.android.R

class ImageFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.onboarding_pager_layout, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textOfImage = view.findViewById<TextView>(R.id.textOfImage)

        if(arguments!!.getInt(IMAGE_NUMBER) == 1){
            imageView.setImageResource(R.drawable.forest)
            textOfImage.text = "FOREST"

        }
        if(arguments!!.getInt(IMAGE_NUMBER) == 2){
            imageView.setImageResource(R.drawable.mountain)
            textOfImage.text = "MOUNTAIN"
        }
        if(arguments!!.getInt(IMAGE_NUMBER) == 3){
            imageView.setImageResource(R.drawable.mountain)
            textOfImage.text = "MOUNTAIN2"
        }
        return view
    }

    companion object {
        private val IMAGE_NUMBER = "image_number"
        fun newInstance(image_number: Int): ImageFragment{
            val args = Bundle()
            val fragment = ImageFragment()
            args.putInt(IMAGE_NUMBER, image_number)
            fragment.arguments = args
            return fragment
        }
    }
}




