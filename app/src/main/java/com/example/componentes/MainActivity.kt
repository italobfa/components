package com.example.componentes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snack.setOnClickListener(this)
        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)
        button_get_seekbar.setOnClickListener(this)
        button_set_seekbar.setOnClickListener(this)


        spinner_static.onItemSelectedListener = this
        seekbar.setOnSeekBarChangeListener(this)
        check_on_off.setOnCheckedChangeListener(this)
        switch_on_off.setOnCheckedChangeListener(this)
        radio_on.setOnCheckedChangeListener(this)
        radio_off.setOnCheckedChangeListener(this)

        loadSpinner()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "Toast", Toast.LENGTH_SHORT)

                toast.setText("opa")

                toast.show()
            }
            R.id.button_snack -> {
                val snack = Snackbar.make(liner_root, "snack", Snackbar.LENGTH_LONG)

                snack.setAction("Desfazer", View.OnClickListener {
                    toast("desfeito")
                })

                snack.setTextColor(Color.RED)
                snack.setBackgroundTint(Color.GREEN)
                snack.show()
            }
            R.id.button_get_spinner -> {
                val selectedItem = spinner_static.selectedItem
                val selectedItemId = spinner_static.selectedItemId
                val selectedItemPosition = spinner_static.selectedItemPosition

                toast("$selectedItem $selectedItemId $selectedItemPosition")
            }
            R.id.button_set_spinner -> {
                spinner_static.setSelection(2)
            }
            R.id.button_get_seekbar -> {
                toast("Seekbar: ${seekbar.progress}")
            }
            R.id.button_set_seekbar -> {
                seekbar.progress = 15
            }
        }
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_static -> {
                toast(parent?.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        toast("nothing")
    }

     private fun loadSpinner() {
        val mList = listOf<String>("Gramas", "Quilos", "Toneladas")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        text_seekbar_value.text = "Valor seekbar: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        toast("Track stoped")
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when(buttonView.id){
            R.id.switch_on_off -> {
                toast("Switch${if(isChecked) "true" else "false"}")
                progressbar.visibility = View.GONE
                //switch_on_off.isChecked = true
            }
            R.id.check_on_off -> {
                toast("Checkbox${if(isChecked) "true" else "false"}")
            }
            R.id.radio_on -> {
                toast("Radio on${if(isChecked) "true" else "false"}")
            }
            R.id.radio_off -> {
                toast("Radio off${if(isChecked) "true" else "false"}")
            }
        }
    }
}