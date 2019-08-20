package com.example.getappengine


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
//import com.squareup.okhttp.MediaType
//import com.squareup.okhttp.OkHttpClient
//import com.squareup.okhttp.Request
//import com.squareup.okhttp.RequestBody
//import com.squareup.okhttp.Response
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import okhttp3.OkHttpClient






/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        // Set an error if the password is less than 8 characters.
        view.login_button.setOnClickListener({

            // Clear the error.
            password_text.error = null
            // Navigate to the next Fragment.

            doAsync{
                val useremail = email_text.getText().toString()
                Global.setUser(useremail) //check with this
                val userpass = password_text.getText().toString()
                val gotresponse = LoginAttempt(useremail,userpass)   //time-consuming HTTP request
                val jsonobj = JSONObject(gotresponse)
                if(jsonobj.get("email") == email_text.text.toString()){(activity as NavigationHost).navigateTo(HomeFragment(), false)}
            }



        })
        view.register_button.setOnClickListener({
            doAsync{
                (activity as NavigationHost).navigateTo(RegisterFragment(), false)
            }
        })

        // Clear the error once more than 8 characters are typed.
        view.password_text.setOnKeyListener({ _, _, _ ->
            if (isPasswordValid(password_text.text!!)) {
                // Clear the error.
                password_text.error = null
            }
            false
        })
        return view
    }

    // "isPasswordValid"  method goes here
    // Currently checks for 8 characters but we could perform
    // an actual validation with a remote service like the Web version below
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun isPasswordValidWeb(text: Editable?): Boolean {
        return true
    }






    //OKHTTP TRY

    private fun LoginAttempt(em: String, pw: String): String? {
        val url = "http://cloudsql-pickupsport.appspot.com/login/"+em+"/"+pw
        val client = OkHttpClient()

        /*val json = """
            {
            "email":"${em}",
            "password":"${pw}"
            }
            """.trimIndent()
            */
        val formtxt = "email:{},password:{}".format(em, pw)
        val body = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), formtxt)
        val request = Request.Builder()
            .header("User-Agent", "Android")
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val bodystr = response.body()?.string() // this can be consumed only once

        return bodystr //changed from String? to String
    }




}


//import com.google.gson.Gson
//HttpGetPost below?
/*
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
*/
