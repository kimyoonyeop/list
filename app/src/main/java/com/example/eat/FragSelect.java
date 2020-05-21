package com.example.eat;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragSelect extends Fragment { // 프래그먼트는 액티비티의 자식
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Select_List> arrayList;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn_add;
    public static FragSelect newInstance() {
        FragSelect fragSelect = new FragSelect();
        return fragSelect;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // 프레그먼트 시작시 수행하는 생명주기

        view = inflater.inflate(R.layout.frag_select, container, false);// 액티비티 안에 프레그먼트 붙임

        btn_add = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.rv_select); //id 연동
        recyclerView.setHasFixedSize(true); //리사이클러 뷰 성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();// 빈 배열 리스트 생성
        Select_List select_list = new Select_List(); // 제품선택 목록 빈 배열 리스트 생성
        select_list.setTv_select_menu("시그니처 버거 세트");
        select_list.setTv_price("7,000원");
        select_list.setTv_quantity("1개");
        arrayList.add(select_list);//배열리스트에 추가
        Select_List select_list2 = new Select_List(); // 제품선택 목록 빈 배열 리스트 생성
        select_list2.setTv_select_menu("치킨 윙 and 봉");
        select_list2.setTv_price("2,000원");
        select_list2.setTv_quantity("1개");
        arrayList.add(select_list2);//배열리스트에 추가


        adapter = new SelectListAdapter(arrayList, getContext()); //어뎁터를 생성하면서 리스트를 어뎁터에 넘긴다.
        recyclerView.setAdapter(adapter);//리사이클러뷰에 커스텀 어뎁터 장착

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final Dialog dialogAdd = new Dialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                dialogAdd.setContentView(R.layout.dialog_add); //xml레이아웃 연결

                final EditText et_selectmenu = dialogAdd.findViewById(R.id.et_selectmenu);
                final EditText et_quantity = dialogAdd.findViewById(R.id.et_quantity);
                Button btn_add = dialogAdd.findViewById(R.id.btn_add);
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_selectmenu.getText().length() == 0 || et_quantity.getText().length() == 0) {
                            //입력필드에 값을 적지 않은 상황이라면
                            Toast.makeText(getContext(), "추가된 입력이 없습니다.", Toast.LENGTH_SHORT).show();
                            return; // if문을 벗어나 onclick 수행이 취소가 된다.

                        }
                        Select_List item = new Select_List();
                        item.setTv_select_menu(et_selectmenu.getText().toString());
                        item.setTv_quantity(et_quantity.getText().toString());
                        arrayList.add(0, item);
                        adapter.notifyItemInserted(0);
                        dialogAdd.dismiss();
                    }
                });

                dialogAdd.show();

            }
        });

        return view;
    }


}
