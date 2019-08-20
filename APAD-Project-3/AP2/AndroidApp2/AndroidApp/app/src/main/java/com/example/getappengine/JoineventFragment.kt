package com.example.getappengine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.joinevent_fragment.*
import kotlinx.android.synthetic.main.joinevent_fragment.view.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.login_fragment.view.error_text
import kotlinx.android.synthetic.main.login_fragment.view.password_text
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class JoineventFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.joinevent_fragment, container, false)
        view.back_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(HomeFragment(), false)
        }

        view.joinevent_button.setOnClickListener {
            doAsync {

                val emailTextField = view.findViewById<TextView>(R.id.email_text).toString() //manipulate this
                val EventIDTextField = view.findViewById<TextView>(R.id.eventid_text).toString()
                val url = "https://cloudsql-pickupsport.appspot.com/joinevent"//manipulate this
                val map: HashMap<String, String> = hashMapOf(emailTextField to EventIDTextField)
                val invalidMap: HashMap<String, String> = hashMapOf(emailTextField to "", EventIDTextField to "") //empty check
                //val LoginInfo = (emailTextField.text.toString(),passTextField.text.toString())
                val client = OkHttpClient()
                val req = OkHttpRequest(client)
                val resp = req.POST(url, map)
                val invalidResp = req.POST(url, invalidMap)
                println("resp: " + resp)
                println(resp?.javaClass?.name)

                uiThread {
                    if (!resp.equals(invalidResp)) {
                        println("Event Joined!")
                        password_text.error = null //Change this error correction
                        // Navigate to the next Fragment.
                        (activity as NavigationHost).navigateTo(HomeFragment(), false)
                    } else {
                        if (emailTextField.equals("") || EventIDTextField.equals("")) {
                            println("Please fill out the Form!")
                            error_text.error = getString(R.string.error_empty)
                        } else {
                            println("The email or eventID doesn't exist!")
                            error_text.error = getString(R.string.error_invalid)
                        }
                    }
                }
                (activity as NavigationHost).navigateTo(HomeFragment(), false)


            }

        }

        return view
    }
}

