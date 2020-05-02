package kr.puze.weddingphotobook.Adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_grid.view.*
import kr.puze.weddingphotobook.R

class MainGridAdapter(private val items: ArrayList<String>, var isOnEdit: Boolean) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view = convertView
        val context = parent.context
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_grid, parent, false)
        }

        Log.d("LOGTAG, Fitting Grid", items[position] + position)
//        Glide.with(context).load(items[position]).into(view!!.image_item)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            view.image_item.clipToOutline = true
//        }

        if(isOnEdit){
            view!!.image_uncheck.visibility = View.VISIBLE
            view.image_uncheck.setOnClickListener {
                view.image_uncheck.visibility = View.GONE
                view.image_check.visibility = View.VISIBLE
            }
            view.image_check.setOnClickListener {
                view.image_uncheck.visibility = View.VISIBLE
                view.image_check.visibility = View.GONE
            }
        }else{
            view!!.image_uncheck.visibility = View.GONE
            view.image_check.visibility = View.GONE
        }
        return view
    }
}