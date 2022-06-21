package com.osmanozdemir.yemektarifleri

import android.content.Context
import android.media.MediaCodec
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.reclcler_row.*
import java.lang.Exception


class ListeFragment : Fragment() {

    var yemekIsmiListesi = ArrayList<String>()
    var yemekIdListesi = ArrayList<Int>()

    private lateinit var  listeAdapter : ListeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = ListeRecyclerAdapter(yemekIsmiListesi,yemekIdListesi)
        RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listeAdapter



        sqlVeriAlma()
    }


    fun sqlVeriAlma(){

        try {
            activity?.let {
                val datebase = it.openOrCreateDatabase("Yemekler",Context.MODE_PRIVATE,null)

                val cursor = datebase.rawQuery("SELECT * FROM yemekler",null)
                val yemekIsmıIndex = cursor.getColumnIndex("yemekismi")
                val yemekIdIndex = cursor .getColumnIndex("id")

                yemekIsmiListesi.clear()
                yemekIdListesi.clear()

                while (cursor.moveToNext()){
                    yemekIsmiListesi.add(cursor.getString(yemekIsmıIndex))
                    yemekIdListesi.add(cursor.getInt(yemekIdIndex))
                }

                listeAdapter.notifyDataSetChanged()



                cursor.close()

            }



        }catch (e:Exception){

        }




    }

}