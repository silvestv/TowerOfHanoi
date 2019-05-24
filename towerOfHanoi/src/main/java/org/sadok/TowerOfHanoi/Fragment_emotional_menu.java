package org.sadok.TowerOfHanoi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Fragment_emotional_menu extends Fragment {
    private static String selectedR1Emo;
    private static String selectedR2Emo;
    private static String selectedR3Emo;
    private static String selectedR4Emo;
    private static String selectedR5Emo;
    private static String selectedR6Emo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_emotional_menu, container, false);

        final Spinner spR1 = (Spinner) rootview.findViewById(R.id.r1Emo);
        final Spinner spR2 = (Spinner) rootview.findViewById(R.id.r2Emo);
        final Spinner spR3 = (Spinner) rootview.findViewById(R.id.r3Emo);
        final Spinner spR4 = (Spinner) rootview.findViewById(R.id.r4Emo);
        final Spinner spR5 = (Spinner) rootview.findViewById(R.id.r5Emo);
        final Spinner spR6 = (Spinner) rootview.findViewById(R.id.r6Emo);


        //Spinner Emotional for Ring1
        ArrayAdapter<CharSequence> adapterR1Emo = ArrayAdapter.createFromResource(getContext(), R.array.r1Emo, android.R.layout.simple_spinner_item);
        adapterR1Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR1.setAdapter(adapterR1Emo);
        spR1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR1Emo = spR1.getSelectedItem().toString();
                System.out.println("Emotion Anneau 1 "+selectedR1Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner Emotional for Ring2
        ArrayAdapter<CharSequence> adapterR2Emo = ArrayAdapter.createFromResource(getContext(), R.array.r2Emo, android.R.layout.simple_spinner_item);
        adapterR2Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR2.setAdapter(adapterR2Emo);
        spR2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR2Emo = spR2.getSelectedItem().toString();
                System.out.println("Emotion Anneau 2 "+selectedR2Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner Emotional for Ring1
        ArrayAdapter<CharSequence> adapterR3Emo = ArrayAdapter.createFromResource(getContext(), R.array.r3Emo, android.R.layout.simple_spinner_item);
        adapterR3Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR3.setAdapter(adapterR3Emo);
        spR3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR3Emo = spR3.getSelectedItem().toString();
                System.out.println("Emotion Anneau 3 "+selectedR3Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner Emotional for Ring1
        ArrayAdapter<CharSequence> adapterR4Emo = ArrayAdapter.createFromResource(getContext(), R.array.r4Emo, android.R.layout.simple_spinner_item);
        adapterR4Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR4.setAdapter(adapterR4Emo);
        spR4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR4Emo = spR4.getSelectedItem().toString();
                System.out.println("Emotion Anneau 4 "+selectedR4Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner Emotional for Ring1
        ArrayAdapter<CharSequence> adapterR5Emo = ArrayAdapter.createFromResource(getContext(), R.array.r5Emo, android.R.layout.simple_spinner_item);
        adapterR5Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR5.setAdapter(adapterR5Emo);
        spR5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR5Emo = spR5.getSelectedItem().toString();
                System.out.println("Emotion Anneau 5 "+selectedR5Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Spinner Emotional for Ring1
        ArrayAdapter<CharSequence> adapterR6Emo = ArrayAdapter.createFromResource(getContext(), R.array.r6Emo, android.R.layout.simple_spinner_item);
        adapterR6Emo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spR6.setAdapter(adapterR6Emo);
        spR6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedR6Emo = spR6.getSelectedItem().toString();
                System.out.println("Emotion Anneau 6 "+selectedR6Emo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        return rootview;

    }

    public static String getSelectedR1Emo() {
        return selectedR1Emo;
    }

    public static String getSelectedR2Emo() {
        return selectedR2Emo;
    }

    public static String getSelectedR3Emo() {
        return selectedR3Emo;
    }

    public static String getSelectedR4Emo() {
        return selectedR4Emo;
    }

    public static String getSelectedR5Emo() {
        return selectedR5Emo;
    }

    public static String getSelectedR6Emo() {
        return selectedR6Emo;
    }
}