package com.example.kaba

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_chat_untuk.view.*

class AdapterPesanUntuk(val text :String,val currUser: User) : Item<ViewHolder> () {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val item: View =viewHolder.itemView

        item.tv_pesan_untuk.text = text
        Picasso.get().load(currUser.photo).into(item.iv_chat_untuk_photo)

    }

    override fun getLayout(): Int {
        return R.layout.item_chat_untuk
    }
}