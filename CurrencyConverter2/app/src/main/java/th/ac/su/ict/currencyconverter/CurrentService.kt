package th.ac.su.ict.currencyconverter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentService {
    @GET("latest?")
    fun getCurrentMoneyData(@Query("base")exUnit:String
    ): Call<CurrentResponse>
}