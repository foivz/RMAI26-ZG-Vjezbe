package hr.foi.rmai.rmai_zg_vjezbe.ws

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WsNews {
    const val BASE_URL = "https://ase.foi.hr/memento/"

    private var instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsService = instance.create(NewsService::class.java)
}