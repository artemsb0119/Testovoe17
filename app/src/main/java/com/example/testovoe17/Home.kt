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

class Home : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerViewToken: RecyclerView
    private lateinit var adapter1: TokenAdapter
    private lateinit var token: List<Token>

    private lateinit var recyclerViewRound: RecyclerView
    private lateinit var adapter2: RoundAdapter
    private lateinit var round: List<Round>

    private lateinit var recyclerViewCoins: RecyclerView
    private lateinit var adapter3: CryptoAdapter
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerViewToken = view.findViewById(R.id.recyclerViewToken)
        val horizontalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewToken.layoutManager = horizontalLayoutManager
        recyclerViewRound = view.findViewById(R.id.recyclerViewRounds)
        val horizontalLayoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRound.layoutManager = horizontalLayoutManager1
        recyclerViewCoins = view.findViewById(R.id.recyclerViewAll)
        val horizontalLayoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCoins.layoutManager = horizontalLayoutManager2
        loadData()
        return view
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/17/token.json").readText()
                val gson = Gson()
                token = gson.fromJson(data, Array<Token>::class.java).toList()

                val data1 = URL("http://135.181.248.237/17/round.json").readText()
                val gson1 = Gson()
                round = gson1.fromJson(data1, Array<Round>::class.java).toList()

                val data2 = URL("http://135.181.248.237/17/crypto.json").readText()
                val gson2 = Gson()
                crypto = gson2.fromJson(data2, Array<Crypto>::class.java).toList()

                requireActivity().runOnUiThread {
                    adapter1 = TokenAdapter(token)
                    recyclerViewToken.adapter = adapter1
                    adapter2 = RoundAdapter(round)
                    recyclerViewRound.adapter = adapter2
                    adapter3 = CryptoAdapter(requireContext(), crypto)
                    recyclerViewCoins.adapter = adapter3
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}