package com.example.supreme

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declare views
    private lateinit var seekBar: SeekBar
    private lateinit var seekText: TextView
    private lateinit var progressLabel: TextView
    private lateinit var editText: EditText
    private lateinit var spinner: Spinner
    private lateinit var checkBox: CheckBox
    private lateinit var radioGroup: RadioGroup
    private lateinit var switchButton: Switch
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton
    private lateinit var textLabel: TextView
    private lateinit var spinLabel: TextView
    private lateinit var radioLabel: TextView
    private lateinit var checkBox11: CheckBox
    private lateinit var switchh: Switch
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bind views
        seekBar = findViewById(R.id.seekBarr)
        seekText = findViewById(R.id.SeekText)
        editText = findViewById(R.id.edit_text)
        spinner = findViewById(R.id.Spinnerr)
        checkBox = findViewById(R.id.checkbox11)
        radioGroup = findViewById(R.id.radio_groupp)
        switchButton = findViewById(R.id.switchh)
        submitButton = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        progressLabel = findViewById(R.id.progressLabel)

        radio1 = findViewById(R.id.radio1)
        radio2 = findViewById(R.id.radio2)
        radio3 = findViewById(R.id.radio3)
        textLabel = findViewById(R.id.text)
        spinLabel = findViewById(R.id.spinText)
        radioLabel = findViewById(R.id.text_view11)
        checkBox11 = findViewById(R.id.checkbox11)
        switchh = findViewById(R.id.switchh)
        button = findViewById(R.id.button)

        // Spinner setup (you must define "gender" array in res/values/strings.xml)
        val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // Spinner selected item text color
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (parent?.getChildAt(0) as? TextView)?.setTextColor(
                    ContextCompat.getColor(this@MainActivity, R.color.black)
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // SeekBar for text size adjustment
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fontSize = progress.coerceAtLeast(12)

                seekText.text = "Text Size : $progress"
                seekText.textSize = fontSize.toFloat()
                editText.textSize = fontSize.toFloat()
                editText.setHintTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))

                button.textSize = fontSize.toFloat()
                radio1.textSize = fontSize.toFloat()
                radio2.textSize = fontSize.toFloat()
                radio3.textSize = fontSize.toFloat()
                textLabel.textSize = fontSize.toFloat()
                spinLabel.textSize = fontSize.toFloat()
                progressLabel.textSize = fontSize.toFloat()
                radioLabel.textSize = fontSize.toFloat()
                checkBox11.textSize = fontSize.toFloat()
                switchh.textSize = fontSize.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Submit button click listener
        submitButton.setOnClickListener {
            var progress = 0

            val name = editText.text.toString().trim()
            val gender = spinner.selectedItem.toString()
            val isChecked = checkBox.isChecked
            val isSwitched = switchButton.isChecked
            val selectedRadioId = radioGroup.checkedRadioButtonId

            var favoriteColor = "None"
            if (selectedRadioId != -1) {
                val selectedRadio = findViewById<RadioButton>(selectedRadioId)
                favoriteColor = selectedRadio.text.toString()
                progress += 20
            }

            if (name.isNotEmpty()) progress += 20
            if (gender.isNotEmpty()) progress += 20
            if (isChecked) progress += 20
            if (isSwitched) progress += 20

            progressBar.progress = progress
            progressLabel.text = "Completed % : $progress"

            val summary = """
                Name: $name
                Gender: $gender
                Subscribed: ${if (isChecked) "Yes" else "No"}
                Favorite Color: $favoriteColor
                Accepted: ${if (isSwitched) "Yes" else "No"}
            """.trimIndent()

            Toast.makeText(this, summary, Toast.LENGTH_LONG).show()
        }
    }
}
