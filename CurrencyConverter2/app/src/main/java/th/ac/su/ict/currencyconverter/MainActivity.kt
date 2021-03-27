package th.ac.su.ict.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


lateinit var edtinput:EditText
lateinit var result:TextView
//lateinit var btnconvert:Button
lateinit var rate:TextView

var BASE_URL = "https://api.exchangeratesapi.io/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtinput = findViewById<EditText>(R.id.EdtExchange)
        result = findViewById<TextView>(R.id.TvResult)
        rate = findViewById<TextView>(R.id.TvCurrentrate)
        val btnconvert = findViewById<Button>(R.id.BtCalculate)

        btnconvert.setOnClickListener {
            getCurrentMoneyData()
        }//btn

    }//override
        // fun กดปุ่มแล้วสั่งงาน
    fun getCurrentMoneyData() {
        val retrofit =Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(CurrentService::class.java)
        val call = service.getCurrentMoneyData("USD") //หน่วยเงินที่ใช้
        call.enqueue(object : Callback<CurrentResponse>{

            //เช็คสถานะ ผ่านไม่ผ่าน
            override fun onFailure(call: Call<CurrentResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
            override fun onResponse(
                    call: Call<CurrentResponse>?,
                    response: Response<CurrentResponse>?
            ) {
                if (response!=null){
                    if (response.code() == 200){

                        //เก็บ input แปลงมาคำนวณ + เก็บ rate api มาคำนวณ
                        val currentResponse = response.body()
                        val calculate = currentResponse.rates.THB.toDouble()
                        val Unit:Double = edtinput.text.toString().toDouble()
                        
                        //input * เรตเงิน
                        val sum = (Unit*calculate)
                        result.text = "= ${sum.toString()} THB"

                        //อัตราแลกเปลี่ยน
                        val showTHB = currentResponse.rates.THB.toString()
                        rate.text = "1 USD = ${showTHB.toString()}"

                    }
                }
            }
        })
    }// end fun


}// end class