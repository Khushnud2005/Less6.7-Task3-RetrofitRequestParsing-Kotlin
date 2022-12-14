package uz.example.less67_task1_posterlistwithprogrbar_kotlin.network

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import org.json.JSONObject
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.App
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.model.Poster

class VolleyHttp {
    companion object {
        val TAG = VolleyHttp::class.java.simpleName
        val IS_TESTER = true
        val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
        val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"
        //val SERVER_PRODUCTION = "https://6221f0dc666291106a17fcb5.mockapi.io/"

        fun server(url: String): String {
            if (IS_TESTER)
                return SERVER_DEVELOPMENT + url
            return SERVER_PRODUCTION + url
        }

        fun headers(): HashMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Content-type"] = "application/json; charset=UTF-8"
            return headers
        }

        /**
         *  Request Method`s
         */

        fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.GET, server(api),
                Response.Listener { response ->
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    volleyHandler.onError(error.toString())
                }) {
                override fun getParams(): MutableMap<String, String> {
                    return params
                }
            }
            App.instance!!.addToRequestQueue(stringRequest)
        }

        fun post(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.POST, server(api),
                Response.Listener { response ->
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            App.instance!!.addToRequestQueue(stringRequest)
        }

        fun put(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.PUT, server(api),
                Response.Listener { response ->
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            App.instance!!.addToRequestQueue(stringRequest)
        }

        fun del(url: String, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.DELETE, server(url),
                Response.Listener { response ->
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    volleyHandler.onError(error.toString())
                }) {
            }
            App.instance!!.addToRequestQueue(stringRequest)
        }

        /**
         *  Request Api`s
         */

        var API_LIST_POST = "posts"
        var API_SINGLE_POST = "posts/" //id
        var API_CREATE_POST = "posts"
        var API_UPDATE_POST = "posts/" //id
        var API_DELETE_POST = "posts/" //id

        /**
         *  Request Param`s
         */

        fun paramsEmpty(): HashMap<String, String> {
            return HashMap<String, String>()
        }

        fun paramsCreate(poster: Poster): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params.put("title", poster.title)
            params.put("body", poster.body)
            params.put("userId", poster.userId)
            return params
        }

        fun paramsUpdate(poster: Poster): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params.put("id", poster.id)
            params.put("title", poster.title)
            params.put("body", poster.body)
            params.put("userId", poster.userId)
            return params
        }



    }
}