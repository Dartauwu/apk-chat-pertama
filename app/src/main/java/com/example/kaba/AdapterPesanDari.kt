package com.example.kaba

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_chat_dari.view.*
import kotlinx.android.synthetic.main.item_chat_untuk.view.*

class AdapterPesanDari(val text :String, val user :User) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
       val item:View = viewHolder.itemView
        item.tv_pesan_dari.text = text
        Picasso.get().load(user.photo).into(item.iv_chat_dari_photo)

    }

    override fun getLayout(): Int {
       return R.layout.item_chat_dari
    }


}