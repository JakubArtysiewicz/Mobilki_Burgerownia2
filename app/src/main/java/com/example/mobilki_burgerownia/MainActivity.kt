package com.example.mobilki_burgerownia

import android.content.DialogInterface
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.collections.forEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var iloscBurgerow = this.findViewById<TextInputEditText>(R.id.ilosc_burgerow_input).text

        val malyButton = this.findViewById<RadioButton>(R.id.malyButton);
        val duzyButton = this.findViewById<RadioButton>(R.id.duzyButton);
        val serCheck = this.findViewById<CheckBox>(R.id.serCheck);
        val beconCheck = this.findViewById<CheckBox>(R.id.bekonCheckBox);

        malyButton.tag = 10;
        duzyButton.tag = 15;
        serCheck.tag = 2;
        beconCheck.tag = 3;
        val listOfMainRadios = listOf<RadioButton>(malyButton,duzyButton);
        val listOfAddings = listOf<CheckBox>(serCheck, beconCheck)

        fun alert(kwota: Int) {
            AlertDialog.Builder(this)
                .setTitle("Kwota")
                .setMessage("Ilość Burgerów: ${iloscBurgerow} Kwota: ${kwota} zł")
                .setPositiveButton(
                    "Ok",
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                .show()
        }
        this.findViewById<Button>(R.id.ObliczButton).setOnClickListener {
            var podsumowanie = 0;
            listOfMainRadios.forEach { radio ->
                if(radio.isChecked){
                    podsumowanie += radio.tag as Int;
                    listOfAddings.forEach{ check ->
                        if(check.isChecked){
                            podsumowanie += check.tag as Int;
                        }

                    }
                    alert(podsumowanie * iloscBurgerow.toString().toInt())

                }
            }
        }
    }
}