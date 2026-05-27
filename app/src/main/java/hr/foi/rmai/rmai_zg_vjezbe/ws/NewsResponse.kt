package hr.foi.rmai.rmai_zg_vjezbe.ws

data class NewsResponse(
    var count: Int,
    var results: ArrayList<NewsItem>
)
