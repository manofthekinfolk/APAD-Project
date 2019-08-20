
package com.example.getappengine


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.register_fragment.view.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

//DO WE NEED A POST FUNCTION?

class RegisterFragment : Fragment() {
//Comment out here if you want to test run the app on Android Studio

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.register_fragment, container, false)

        val registerButton = view.findViewById<View>(R.id.register_button)
        registerButton.setOnClickListener {
            doAsync {

                val emailTextField = view.findViewById<TextView>(R.id.email_text).toString()
                val passTextField = view.findViewById<TextView>(R.id.password_text).toString()
                val firstnameTextField = view.findViewById<TextView>(R.id.firstname_text).toString()
                val lastnameTextField = view.findViewById<TextView>(R.id.lastname_text).toString()
                val streetaddressTextField = view.findViewById<TextView>(R.id.streetaddress_text).toString()
                val cityTextField = view.findViewById<TextView>(R.id.city_text).toString()
                val confirmpasswordTextField = view.findViewById<TextView>(R.id.confirmpassword_text).toString()
                val gotresponse = RegisterAttempt(
                    emailTextField, passTextField, firstnameTextField, lastnameTextField,
                    streetaddressTextField, cityTextField, confirmpasswordTextField
                )
                val jsonobject = JSONObject(gotresponse)
                if (jsonobject.get("result") == emailTextField) {(activity as NavigationHost).navigateTo(LoginFragment(), false)
                }
            }

               // val url = "https://cloudsql-pickupsport.appspot.com/register"

                //CAll PASSWORD VERIFIER RIGHT HERE
                /*
                val map: HashMap<String, String> = hashMapOf(
                    emailTextField,firstnameTextField to lastnameTextField,passTextField, streetaddressTextField, cityTextField)
                    //Need to finish Hash map
                */
                    //val client = OkHttpClient()
                    //val req = OkHttpRequest(client)
                    //val resp = req.fetchPost(url, map) //mess with these
                    //val invalidResp = req.fetchPost(url, invalidMap) //mess with these
                    //println("resp: " + resp)
                    //println(resp?.javaClass?.name)
                /*
                uiThread {
                    if (resp.equals("False")) {
                        println("Sign Up Failed")
                    } else {
                        // Navigate to the next Fragment.
                        println("Success")
                        (activity as NavigationHost).navigateTo(LoginFragment(), false) */
                    }

        return view
            }

        }

//Comment out here if you want to test run the app on Android Studio



private fun RegisterAttempt(fn:String,ln:String,email:String, pw: String, city:String,sa:String,cp:String): String {
    val url = "https://cloudsql-pickupsport.appspot.com/register"
    val client = OkHttpClient()

    val json = """
            {
            "emailTextField":"${email}",
            "firstnameTextField":"${fn}",
            "lastnameTextField":"${ln}",
            "cityTextField":"${city}",
            "StreetAddressTextField":"${sa}",
            "passTextField":"${pw}"
            "confirmpasswordTextField":"${cp}"
            }
            """.trimIndent()


    val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    val request = Request.Builder()
        .header("User-Agent", "Android")
        .url(url)
        .post(body)
        .build()

    val response = client.newCall(request).execute()
    val bodystr =  response.body()!!.string() // this can be consumed only once

    return bodystr

}