package com.example.kaba

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    val firebase = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        checkUserSign()
    }

    private fun checkUserSign() {
        if(FirebaseAuth.getInstance().uid.isNullOrEmpty()) {
            Login.launchIntentClearTask(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu_home,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_sign_out ->{
                signOutUser()

            }
            R.id.nav_message->{
               FriendListActivity.launchIntent(this)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOutUser() {
        FirebaseAuth.getInstance().signOut()
        Login.launchIntentClearTask(this)
    }


    companion object {
         fun launchIntent(context : Context) {
             val intent = Intent(context, Home::class.java)
             context.startActivity(intent)
         }
             fun launchIntentClearTask(context: Context){
                 val intent = Intent(context, Home::class.java)
                 intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                 context.startActivity(intent)

        }
    }
}