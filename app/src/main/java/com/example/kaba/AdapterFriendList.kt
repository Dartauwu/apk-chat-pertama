package com.example.kaba

import com.google.firebase.database.core.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_friend.view.*

class AdapterFriendList( val user : User) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        val itemView = viewHolder.itemView

        itemView.friend_name.text = user.name
        Picasso.get().load(user.photo).into(itemView.iv_friend_photo)

    }

    override fun getLayout(): Int {
        return R.layout.item_friend
    }
}