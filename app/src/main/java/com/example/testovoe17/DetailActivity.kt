package com.example.testovoe17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.absoluteValue

class DetailActivity : AppCompatActivity() {

    private lateinit var back: ImageView
    private lateinit var imageViewCrypto: ImageView
    private lateinit var textViewId: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewCost: TextView
    private lateinit var textViewProc: TextView
    private lateinit var imageViewGraf: ImageView
    private lateinit var textViewMarketCapResult: TextView
    private lateinit var textViewSupplyResult: TextView
    private lateinit var textViewTotalResult: TextView
    private lateinit var textViewFullyResult: TextView
    private lateinit var textViewVolumeResult: TextView
    private lateinit var textViewAllResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        back = findViewById(R.id.back)
        imageViewCrypto = findViewById(R.id.imageViewCrypto)
        textViewId = findViewById(R.id.textViewId)
        textViewName = findViewById(R.id.textViewName)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewCost = findViewById(R.id.textViewCost)
        textViewProc = findViewById(R.id.textViewProc)
        imageViewGraf = findViewById(R.id.imageViewGraf)
        textViewMarketCapResult = findViewById(R.id.textViewMarketCapResult)
        textViewSupplyResult = findViewById(R.id.textViewSupplyResult)
        textViewTotalResult = findViewById(R.id.textViewTotalResult)
        textViewFullyResult = findViewById(R.id.textViewFullyResult)
        textViewVolumeResult = findViewById(R.id.textViewVolumeResult)
        textViewAllResult = findViewById(R.id.textViewAllResult)
        back.setOnClickListener {
            finish()
        }

        val crypto = intent.getSerializableExtra("crypto") as? Crypto

        if (crypto != null) {
            Glide.with(this)
                .load(crypto.image)
                .into(imageViewCrypto)
            textViewName.text = crypto.name
            textViewId.text = "#" + (crypto.id + 1).toString()
            textViewTitle.text = crypto.title

            val initialCost = crypto.cost.toFloat()
            val minPercentage = 0.85f
            val maxPercentage = 1.15f

            val random = Random()
            val randomPercentage =
                minPercentage + random.nextFloat() * (maxPercentage - minPercentage)
            val newCost = initialCost * randomPercentage

            var formattedNewCost =
                String.format("%.${initialCost.toString().split(".")[1].length}f", newCost)

            if (crypto.id == 0) {
                formattedNewCost = formattedNewCost.replace(",", "")
            }

            textViewCost.text = "$" + formattedNewCost

            textViewProc.text = crypto.proc
            if (crypto.proc.contains("-")) {
                val color = ContextCompat.getColor(this, R.color.red)
                textViewProc.setTextColor(color)
                textViewProc.setBackgroundResource(R.drawable.red_cons)
            } else {
                val color = ContextCompat.getColor(this, R.color.green)
                textViewProc.setTextColor(color)
                textViewProc.setBackgroundResource(R.drawable.green_cons)
            }

            Glide.with(this)
                .load(crypto.graf)
                .into(imageViewGraf)

            textViewMarketCapResult.text = crypto.market
            textViewSupplyResult.text = crypto.supply
            textViewTotalResult.text = crypto.total
            textViewFullyResult.text = crypto.fully
            textViewVolumeResult.text = crypto.volume
            textViewAllResult.text = crypto.all
        }
    }
}