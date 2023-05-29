package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.service.autofill.TextValueSanitizer
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
//binding the main activity file with layout page using view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //using layout inflater to inflate the binding

        setContentView(binding.root) //calling the object created by the binding




        binding.Finish.setOnClickListener {
            if (isInputValid()) {
                calculateTip()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }



        }

    private fun isInputValid(): Boolean {
        val amountText = binding.Amount.text.toString()
        val selectedId = binding.Option.checkedRadioButtonId

        return amountText.isNotEmpty() && selectedId != -1
    }



    fun calculateTip()
    {

        //converting the text obtained from amount entered into the edit text view we use

        val stringInTextField = binding.Amount.text.toString()
        val cost = stringInTextField.toDouble()
        val newCost:Double = cost
        val selectedId = binding.Option.checkedRadioButtonId
        val TipPercentage = when(selectedId){

            R.id.Option1 -> 0.15
            R.id.Option2 -> 0.13
            else ->0.10
        }

        var Tip = TipPercentage*cost


        val roundUp = binding.rounded.isChecked

        if (roundUp){
            Tip = kotlin.math.ceil(Tip)
        }
        val TIP:Double = Tip

        //till now we just get the amount in form of a string to get the string or the answer in form of currency we will use currency formatter

        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(Tip)

        binding.TipAmount.text = getString(R.string.tip_amount, formattedTip)

        val totalAmount = TIP+newCost

        binding.total.text = "Total Amount: ${totalAmount}"
    }


    }
