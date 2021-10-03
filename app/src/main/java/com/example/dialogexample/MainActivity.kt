package com.example.dialogexample

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dialogexample.databinding.ActivityMainBinding
import com.example.dialogexample.databinding.LayoutDialogCustomBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.btnSimpleDialog.setOnClickListener {
            openSimpleAlertDialog()
        }

        binding.btnAdButton.setOnClickListener {
            openButtonAlertDialog()
        }

        binding.btnAdComboBox.setOnClickListener {
            openComboBoxAlertDialog()
        }

        binding.btnAdCustom.setOnClickListener {
            openCustomAlertDialog()
        }

        binding.btnCustomDialog.setOnClickListener {
            openDialogFragment()
        }
    }

    private fun openDialogFragment() {
        CustomDialogFragment("Hi Yandi Disini !"){
            openComboBoxAlertDialog()
        }.show(supportFragmentManager,"custom_dialog")
    }

    private fun openCustomAlertDialog() {
        val dialogBinding = LayoutDialogCustomBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)

            .apply {
                setView(dialogBinding.root)
            }
            .create()
        dialogBinding.tvTitleDialog.text = "This is text set by Programmaticalitty"
        dialogBinding.btnCloseDialog.setOnClickListener{
            dialog.dismiss()
        }
        dialogBinding.btnShowAd.setOnClickListener{
            openComboBoxAlertDialog()
        }

            dialog.show()
    }

    private fun openComboBoxAlertDialog() {
        val selectedItems = ArrayList<Int>()
        AlertDialog.Builder(this)
            .apply {
                setTitle("Pick Some Toppings")
                setPositiveButton("Yes") {dialog, which ->
                    Toast.makeText(this@MainActivity, selectedItems.toString()
                            ,Toast.LENGTH_SHORT).show()
                }

                setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity, "No Button Clicked", Toast.LENGTH_SHORT).show()
                }

                setMultiChoiceItems(
                    R.array.burger_toppings_array, null
                ) {dialog, which, isChecked ->
                    if(isChecked) {
                    selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                    selectedItems.remove(which)
                    }
                }
            }
            .create()
            .show()
    }

    private fun openButtonAlertDialog() {
        AlertDialog.Builder(this)
            .apply {
                setTitle("Hello ! this is Dialog Title")
                setMessage("and This is message")
                setPositiveButton("Yes") {dialog, which ->
                    Toast.makeText(this@MainActivity, "Yes Button Clicked", Toast.LENGTH_SHORT).show()
                }

                setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity, "No Button Clicked", Toast.LENGTH_SHORT).show()
                }

                setNeutralButton("Probably") {dialog, which ->
                    Toast.makeText(this@MainActivity, "Probably Button Clicked", Toast.LENGTH_SHORT).show()
                }

            }
            .create()
            .show()
    }

    private fun openSimpleAlertDialog() {
        AlertDialog.Builder(this)
            .apply {
                setTitle("Hello ! this is Dialog Title")
                setMessage("and This is message")
            }
            .create()
            .show()
    }
}