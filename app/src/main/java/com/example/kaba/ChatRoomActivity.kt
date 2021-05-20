package com.example.kaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_room.*
import org.w3c.dom.Text

class ChatRoomActivity : AppCompatActivity() {


    lateinit var friend :User
    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)


        friend = intent.getParcelableExtra<User>(FriendListActivity.FRIEND_KEY)!!
        supportActionBar!!.title = friend.name


        rv_chat_room_list.adapter = adapter

        initView()

        loadMessageFromFirebase()
    }

    private fun loadMessageFromFirebase() {

        val reference:DatabaseReference = FirebaseDatabase.getInstance().getReference("/message")

        reference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val messageCollection:Message? = snapshot.getValue(Message::class.java)

                if(messageCollection!=null){

                    if(messageCollection.fromId == FirebaseAuth.getInstance().uid){

                        adapter.add(AdapterPesanUntuk(messageCollection.text,Login.currentUserData))

                }else{

                        adapter.add(AdapterPesanDari(messageCollection.text,friend))

                    }
                }
            }


            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun initView() {
        btn_chat_room_send_message.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        //disini pesan akan di olah untuk dikirim ke firebase


        val messageDbReference = FirebaseDatabase.getInstance().getReference("/message").push()
        val id = messageDbReference.key.toString()
        val fromId: String = FirebaseAuth.getInstance().uid.toString()
        val toId:String = friend.uid
        val text: String = et_chat_room_chat_message.text.toString()
        val time = System.currentTimeMillis()/1000



        messageDbReference.setValue(
                Message(id,fromId,toId,text,time)
        )
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"berhasil terkirim ke database",Toast.LENGTH_LONG).show()

                }
    }


    companion object {
        fun launchIntent(context: Context) {
            val intent = Intent(context, ChatRoomActivity::class.java)
            context.startActivity(intent)
        }
    }
}