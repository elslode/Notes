package slode.elsloude.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Add_Note_Activity extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextDeskription;
    Spinner spinnerDaysOfWeek;
    RadioGroup radioGroupPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDeskription = findViewById(R.id.editTextDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
    }

    public void onClickSaveNote(View view) {
        String tite = editTextTitle.getText().toString().trim();
        String description = editTextDeskription.getText().toString().trim();
        String dayOfWeek = spinnerDaysOfWeek.getSelectedItem().toString();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        Note note = new Note(tite, description, dayOfWeek, priority);
        MainActivity.notes.add(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
