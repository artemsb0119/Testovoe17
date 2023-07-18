package com.example.testovoe17

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Coins : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerViewCoins: RecyclerView
    private lateinit var adapter: CoinsAdapter
    private lateinit var crypto: List<Crypto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coins, container, false)
        recyclerViewCoins = view.findViewById(R.id.recyclerViewCoins)
        val verticatalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewCoins.layoutManager = verticatalLayoutManager
        loadData()
        return view
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/17/crypto.json").readText()
                val gson = Gson()
                crypto = gson.fromJson(data, Array<Crypto>::class.java).toList()

                requireActivity().runOnUiThread {
                    adapter = CoinsAdapter(requireContext(), crypto)
                    recyclerViewCoins.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Coins().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}