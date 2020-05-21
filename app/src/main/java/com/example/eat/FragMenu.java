package com.example.eat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragMenu extends Fragment { // 프래그먼트는 액티비티의 자식
    private View view;

    public static FragMenu newInstance() {
        FragMenu fragMenu = new FragMenu();
        return fragMenu;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_menu, container, false);// 액티비티 안에 프레그먼트 붙임

        return view;
    }


}
