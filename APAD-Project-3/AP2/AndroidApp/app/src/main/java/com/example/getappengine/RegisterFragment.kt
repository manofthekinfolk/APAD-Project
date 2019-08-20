
package com.example.getappengine


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.register_fragment.view.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info




//DO WE NEED A POST FUNCTION?

class RegisterFragment : Fragment(), AnkoLogger {
//Comment out here if you want to test run the app on Android Studio

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        var sp = getActivity()?.getPreferences(Context.MODE_PRIVATE)
        val registerButton = view.findViewById<View>(R.id.register_button)
        registerButton.setOnClickListener {
            doAsync {
                /*
                val em = view.findViewById<TextView>(R.id.email_text).toString()
                val pw = view.findViewById<TextView>(R.id.password_text).toString()
                val fn = view.findViewById<TextView>(R.id.firstname_text).toString()
                val ln = view.findViewById<TextView>(R.id.lastname_text).toString()
                val sa = view.findViewById<TextView>(R.id.streetaddress_text).toString()
                val city = view.findViewById<TextView>(R.id.city_text).toString()
                val confirmpasswordTextField = view.findViewById<TextView>(R.id.confirmpassword_text).toString()
                val url =
                    "https://cloudsql-pickupsport.appspot.com/adduser/" + em + "/" + fn + "/" + ln + "/" + sa + "/" + city + "/" + pw
                */
                var response = registerUser(
                    view.email_text.text.toString(),
                    view.firstname_text.text.toString(),
                    view.lastname_text.text.toString(),
                    view.streetaddress_text.text.toString(),
                    view.city_text.text.toString(),
                    view.password_text.text.toString()
                )

                print(response)
                /*
                //response = response?.trim()?.replace("\"", "")
                response = response?.trim()?.replace("","/")
                print(response)
                uiThread {
                    info(response)
                    if (response == "Email is taken!") {
                        view.error_text.text = "Email is taken! Please use another username."
                    } else {
                        sp?.edit()?.putString("email", view.email_text.text.toString())?.apply()

                        //printing out to log
                        info(sp?.getString("Email", ""))

                        //go to a different activity if it passes login and password
                        */
                //(activity as NavigationHost).navigateTo(HomeFragment(), false)
            }
        }

        (activity as NavigationHost).navigateTo(HomeFragment(), false)

        /*
        view.back_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(LoginFragment(), false)
        }

        return view
        */
        return view
    }
}
    private fun registerUser(fn: String,ln: String,email: String,sa: String, city: String,pw: String): String? {
        val client = OkHttpClient()
        val request = OkHttpRequest(client)  //instantiating myOK instance

        val url = "https://cloudsql-pickupsport.appspot.com/adduser"

        val contents: HashMap<String, String> = HashMap<String, String>()
        contents.put("First Name", fn)
        contents.put("Last Name", ln)
        contents.put("email", email)
        contents.put("street address", sa)
        contents.put("city", city)
        contents.put("password", pw)

        println(contents)

        return request.POST(url, contents)

       // print(request.POST())
    }



                /*
                data class registerinfo(
                    val em: String,
                    val pw: String,
                    val fn: String,
                    val ln: String,
                    val sa: String,
                    val city: String
                    companion object{

                }
                )
                val json = Gson().toJson(registerinfo)

                   */
    /*
                val client = OkHttpClient()
                val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
                val request = Request.Builder()
                    .header("User-Agent", "Android")
                    .url(url)
                    .post(body)
                    .build()


                val response = client.newCall(request).execute()
                val bodystr = response.body()?.toString()

                (activity as NavigationHost).navigateTo(LoginFragment(), false)
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


/*
private fun RegisterAttempt(email:String,fn:String,ln:String, sa:String, city:String,pw: String): String? {
    val url = "https://cloudsql-pickupsport.appspot.com/register/"+email+"/"+fn+"/"+ln+"/"+sa+"/"+city+"/"+pw
    val client = OkHttpClient()

    val json = """
            {
            "emailTextField":"${email}",
            "firstnameTextField":"${fn}",
            "lastnameTextField":"${ln}",
            "cityTextField":"${city}",
            "StreetAddressTextField":"${sa}",
            "passTextField":"${pw}"
            }
            """.trimIndent()



    val response = client.newCall(request).execute()
    val bodystr =  response.body()?.toString() // this can be consumed only once
    return bodystr
    //return request

}
        */

private fun registerUser(email:String,fn:String,ln:String, sa:String, city:String,pw: String): String? {
    val client = OkHttpClient()
    val request = OkHttpRequest(client)  //instantiating myOK instance

    val url = "https://barkinapad.appspot.com/adduser/"

    val contents:HashMap<String, String> = HashMap<String, String>()
    contents.put("email", email)
    contents.put("First Name", fn)
    contents.put("Last Name", ln)
    contents.put("street address", sa)
    contents.put("city", city)
    contents.put("password", pw)

    return request.POST(url, contents)
}
}
        */
