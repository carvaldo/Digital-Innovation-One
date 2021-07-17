package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.fimo.databinding.FragmentFirstBinding
import com.github.carvaldo.fimo.datasource.remote.Movie
import com.github.carvaldo.fimo.datasource.remote.MovieService
import com.github.carvaldo.fimo.datasource.remote.SearchResult
import com.github.carvaldo.fimo.datasource.remote.ServiceGenerator
import com.google.gson.Gson
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val adapter by lazy { SearchAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter.onItemClickListener = { _: Int, movie: Movie ->
            Log.d("WTF", "onCreateView: click")
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(movie.id))
        }
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ServiceGenerator.create<MovieService>().search("Titanic").enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val result = response.body()!!
                if (result.errorMessage.isNotBlank()) {
                    val gson = Gson().fromJson(JsonParser.parseString("{\n" +
                            "    \"searchType\": \"Movie\",\n" +
                            "    \"expression\": \"Titanic\",\n" +
                            "    \"results\": [\n" +
                            "        {\n" +
                            "            \"id\": \"tt0120338\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic\",\n" +
                            "            \"description\": \"(1997)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt0051994\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BOWRiZDljY2YtOWJiNy00OTUyLTgzMmMtMzBlYTI0YTYwYTkzL2ltYWdlXkEyXkFqcGdeQXVyNjQzNDI3NzY@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"A Night to Remember\",\n" +
                            "            \"description\": \"(1958) aka \\\"La última noche del Titanic\\\"\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt1869152\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMTA4MjIyZWEtZjYwMS00ZmQ1LWJiMDEtMWNiNTI5NWE3OGJjXkEyXkFqcGdeQXVyNjk1Njg5NTA@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic\",\n" +
                            "            \"description\": \"(2012) (TV Mini Series)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt1640571\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMTMxMjQ1MjA5Ml5BMl5BanBnXkFtZTcwNjIzNjg1Mw@@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic II\",\n" +
                            "            \"description\": \"(2010) (Video)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt0081400\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BNTQyZGI0NDgtYTM0ZC00NTdkLTk2OTItYTgwYmYwNjZlNDRlXkEyXkFqcGdeQXVyMTY5Nzc4MDY@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Raise the Titanic\",\n" +
                            "            \"description\": \"(1980)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt0115392\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMTBhMjUzMDItYTBlZS00OThkLWJmZDQtMjg1YTQ5ZDkxYmFjXkEyXkFqcGdeQXVyNjk1Njg5NTA@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic\",\n" +
                            "            \"description\": \"(1996) (TV Mini Series)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt0046435\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMTU3NTUyMTc3Nl5BMl5BanBnXkFtZTgwOTA2MDE3MTE@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic\",\n" +
                            "            \"description\": \"(1953)\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"tt1695366\",\n" +
                            "            \"resultType\": \"Title\",\n" +
                            "            \"image\": \"https://imdb-api.com/images/original/MV5BMjI2MzU2NzEzN15BMl5BanBnXkFtZTcwMzI5NTU3Nw@@._V1_Ratio0.7273_AL_.jpg\",\n" +
                            "            \"title\": \"Titanic: Blood and Steel\",\n" +
                            "            \"description\": \"(2012) (TV Mini Series)\"\n" +
                            "        }\n" +
                            "    ],\n" +
                            "    \"errorMessage\": \"\"\n" +
                            "}"), SearchResult::class.java).results
                    adapter.items = gson
                } else {
                    adapter.items = response.body()?.results
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}