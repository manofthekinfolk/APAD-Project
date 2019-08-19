package com.example.getappengine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.content.Context
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.login_fragment.view.error_text


//import com.google.gson.Gson
interface TestListener {
    fun onLogin(id : Int)
}
//HttpGetPost below?
class LoginFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        view.register_button.setOnClickListener{
            (activity as NavigationHost).navigateTo(RegisterFragment(), false)
        }


        view.login_button.setOnClickListener{
            doAsync{

            val emailTextField = view.findViewById<TextView>(R.id.email_text).toString() //manipulate this
            val passTextField = view.findViewById<TextView>(R.id.password_text).toString()
                val url = "https://cloudsql-pickupsport.appspot.com/login"//manipulate this

            //val email = username_text.text.toString()
            //val password = password_text.text.toString()

            //val map: HashMap<String, String> = hashMapOf("email" to emailTextField, "password" to passTextField)
            val map: HashMap<String, String> = hashMapOf(emailTextField to passTextField)
            val invalidMap: HashMap<String, String> = hashMapOf(emailTextField to "", passTextField to "") //empty check
            //val LoginInfo = (emailTextField.text.toString(),passTextField.text.toString())
            val client = OkHttpClient()
            val req = OkHttpRequest(client)
            val resp = req.fetchPost(url, map)
            val invalidResp = req.fetchPost(url, invalidMap)
            println("resp: " + resp)
            println(resp?.javaClass?.name)

            uiThread {
                if (!resp.equals(invalidResp)) {
                    println("Logged in")
                    password_text.error = null
                    // Navigate to the next Fragment.
                    (activity as NavigationHost).navigateTo(HomeFragment(), false)
                } else {
                    if (emailTextField.equals("") || passTextField.equals("")) {
                        println("empty!")
                        error_text.error = getString(R.string.error_empty)
                    } else {
                        println("Wrong credentials")
                        error_text.error = getString(R.string.error_invalid)
                    }
                }
            }
                /*
                var realId = resp?.substring(resp?.indexOf("id")+4,resp?.indexOf(",",resp?.indexOf("id")))
                setMyId(realId)
                println("real id: " + realId)

                val pref = activity!!.getPreferences(Context.MODE_PRIVATE)
                val edt = pref.edit()
                edt.putString("userId", realId)
                edt.commit()
                */
            }
        }
        view.email_text.setOnKeyListener({ _, _, _ ->
            // Clear the error once user tries to change username
            error_text.error = null
            false
        })
        view.password_text.setOnKeyListener({ _, _, _ ->
            // Clear the error once user tries to change password
            error_text.error = null
            false
        })
        return view
    }


/*
     fun setMyId(ID: String?) {
        myId = ID
    }
     fun getMyId() : String? {
        return myId
    } */
}