package com.example.kaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class  Login : AppCompatActivity() {

    val firebase = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textView = findViewById<TextView>(R.id.register_login)

        textView.setOnClickListener {
            val intent = Intent (this, Register::class.java)
            startActivity(intent)
        }

        initView()
        
    }

    private fun initView() {
        btn_login_login_user.setOnClickListener {

            loginToFirebase()
        }
    }

    private fun loginToFirebase() {
        val email = et_login_email.text.toString().trim()
        val password = et_password_password.text.toString()
       firebase.signInWithEmailAndPassword(email,password)
           .addOnCompleteListener {
               if(it.isSuccessful){
                   Toast.makeText(this,"Berhasil Login", Toast.LENGTH_LONG).show()
                   Home.launchIntent(this)
                   val uid:String? =  FirebaseAuth.getInstance().uid
                   val ref :DatabaseReference =  FirebaseDatabase.getInstance().getReference("user/$uid")
                   ref.addValueEventListener(object : ValueEventListener{
                       override fun onDataChange(snapshot: DataSnapshot) {
                          currentUserData = snapshot.getValue(User::class.java)!!
                       }

                       override fun onCancelled(error: DatabaseError) {

                       }

                   })
               }else{

               }
           }
           .addOnFailureListener {
               Toast.makeText(this,"Failure ${it.message}",Toast.LENGTH_LONG).show()
           }
    }

    companion object{

        lateinit var currentUserData :User

        fun launchIntent(context: Context) {
            val intent = Intent(context, Login::class.java)
            context.startActivity(intent)
        }

        fun launchIntentClearTask(context: Context) {
            val intent = Intent(context, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)


        }
    }
}

