package kr.puze.weddingphotobook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.puze.weddingphotobook.Adapter.MainGridAdapter
import kr.puze.weddingphotobook.Epub.EpubNavigator
import kr.puze.weddingphotobook.Epub.MainActivity
import kr.puze.weddingphotobook.Utils.PrefUtil

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var mainAdapter: MainGridAdapter
        lateinit var prefUtil: PrefUtil
        var isOnEdit = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){
        prefUtil = PrefUtil(this@MainActivity)
        prefUtil.getStringArrayPref(prefUtil.KEY).map { str -> Log.d("dudco", str) }
        setGridView(prefUtil.getStringArrayPref(prefUtil.KEY))

        text_move_main.visibility = View.INVISIBLE
        text_delete_main.visibility = View.INVISIBLE
        text_edit_main.setOnClickListener {
            if(isOnEdit){
                text_move_main.visibility = View.INVISIBLE
                text_delete_main.visibility = View.INVISIBLE
                text_edit_main.text = "완료"

            }else{
                text_move_main.visibility = View.VISIBLE
                text_delete_main.visibility = View.VISIBLE
                text_edit_main.text = "편집"
            }
            isOnEdit = !isOnEdit
        }
        text_add_main.setOnClickListener {
            startActivity(Intent(this@MainActivity, FindActivity::class.java))
        }
    }

    private fun setGridView(items: ArrayList<String>) {
        mainAdapter = MainGridAdapter(items, isOnEdit)
        Log.d("mainAdapter", mainAdapter.count.toString())
        grid_main.adapter = mainAdapter
        grid_main.setOnItemClickListener { parent, view, position, id ->
            try {
                var intent = Intent(this@MainActivity, MainActivity::class.java);
                intent.putExtra("path", items[position]);
                startActivity(intent)
            } catch (e: TypeCastException) {
            }
        }
    }
}
