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
        return inflater.inflate(R.layout.fragment_emotional_menu, container, false);
    }
}