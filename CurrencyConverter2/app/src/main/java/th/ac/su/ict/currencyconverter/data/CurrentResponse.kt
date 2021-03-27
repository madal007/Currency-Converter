package th.ac.su.ict.currencyconverter

import com.google.gson.annotations.SerializedName

   
data class CurrentResponse (

   @SerializedName("rates") var rates : Rates,
   @SerializedName("base") var base : String,
   @SerializedName("date") var date : String

)