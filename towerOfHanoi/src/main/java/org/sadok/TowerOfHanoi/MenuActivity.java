package org.sadok.TowerOfHanoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class MenuActivity extends AppCompatActivity {
    public static String selectedItem;
    public static String selectedBackGround;
    public static String selectedShapeItem;
    public static String selectedFeedBackItem;
    public static CheckBox checkbox;
    public static boolean checkboxTrue;
    public static boolean checkboxTrueEmo;

    public static Fragment_emotional_menu fragmentEmotion;
    public FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        manager = (FragmentManager) getSupportFragmentManager();
        this.fragmentEmotion = new Fragment_emotional_menu();

        final Spinner spinner = (Spinner) findViewById(R.id.ringsNumber);
        final Spinner backGround = (Spinner) findViewById(R.id.backGround);
        final Spinner spinnerShape = (Spinner) findViewById(R.id.ringsShape);
        final Spinner spinnerFeedBack = (Spinner) findViewById(R.id.feedback);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        final Button start = findViewById(R.id.button);
        final CheckBox checkboxEmotion = (CheckBox) findViewById(R.id.checkBox2);

        checkboxEmotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    manager.beginTransaction().add(R.id.fragEmotional, fragmentEmotion).commit();
                }
                else{
                    manager.beginTransaction().remove(fragmentEmotion).commit();
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this,
                        TowerOfHanoiActivity.class);
                startActivity(myIntent);

                if(checkboxEmotion.isChecked()){
                    checkboxTrueEmo = true;
                }else{
                    checkboxTrueEmo = false;
                }
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        final String backGroundNames[] = {"Fond d'écran n°1","Fond d'écran n°2","Fond d'écran n°3","Fond d'écran n°4","Fond d'écran n°5", "Fond d'écran n°6", "Fond d'écran n°7","Fond d'écran n°8", "Fond d'écran n°9", "Fond d'écran n°10"};
        int backGroundIcon[] = {R.drawable.ic_background0, R.drawable.ic_background2, R.drawable.ic_background3, R.drawable.ic_background4, R.drawable.ic_background5, R.drawable.ic_background6, R.drawable.ic_background7, R.drawable.ic_background8, R.drawable.ic_background9, R.drawable.ic_background10};
        //Spinner for rings number
        CustomAdapter adapterBackground =new CustomAdapter(this,
                backGroundIcon, backGroundNames);
        backGround.setAdapter(adapterBackground);
        backGround.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedBackGround = backGroundNames[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        //Spinner for Shape

        ArrayAdapter<CharSequence> adapterShape = ArrayAdapter.createFromResource(this, R.array.ringsShape, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
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
        ArrayAdapter<CharSequence> adapterFeedback = ArrayAdapter.createFromResource(this, R.array.feedback, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
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





