package org.sadok.TowerOfHanoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class MenuActivity extends AppCompatActivity {
    public static String selectedItem;
    public static String selectedShapeItem;
    public static String selectedFeedBackItem;
    public static CheckBox checkbox;
    public static boolean checkboxTrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Spinner spinner = (Spinner) findViewById(R.id.ringsNumber);
        final Spinner spinnerShape = (Spinner) findViewById(R.id.ringsShape);
        final Spinner spinnerFeedBack = (Spinner) findViewById(R.id.feedback);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        final Button start = findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this,
                        TowerOfHanoiActivity.class);
                startActivity(myIntent);

                if (checkbox.isChecked()) {
                    checkboxTrue = true;
                }else{
                    checkboxTrue = false;
                }
            }
        });


        //Spinner for rings number
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ringsNumber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedItem = spinner.getSelectedItem().toString();

                System.out.println("Nombre d'anneau "+selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner for Shape

        ArrayAdapter<CharSequence> adapterShape = ArrayAdapter.createFromResource(this, R.array.ringsShape, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShape.setAdapter(adapterShape);
        spinnerShape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedShapeItem = spinnerShape.getSelectedItem().toString();

                System.out.println("Shape selectionner "+selectedShapeItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner for feedback
        ArrayAdapter<CharSequence> adapterFeedback = ArrayAdapter.createFromResource(this, R.array.feedback, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFeedBack.setAdapter(adapterFeedback);
        spinnerFeedBack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedFeedBackItem = spinnerFeedBack.getSelectedItem().toString();

                System.out.println("Feed selectionner "+selectedFeedBackItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

}





