package com.example.eat;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectListAdapter extends RecyclerView.Adapter<SelectListAdapter.CustomViewHolder> {

    private ArrayList<Select_List> arrayList;//선택목록 list를 담고 있다.
    private Context context;

    public SelectListAdapter(ArrayList<Select_List> arrayList, Context context) {  //생성자를 구성
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_list, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;//recyclerview가 list와 연결되는 순간
    }

    @Override
    public void onBindViewHolder(@NonNull SelectListAdapter.CustomViewHolder holder, int position) {
        holder.iv_hamburger.setImageResource(R.drawable.burger);
        holder.tv_select_menu.setText(arrayList.get(position).getTv_select_menu());
        holder.tv_price.setText(arrayList.get(position).getTv_price());
        holder.tv_quantity.setText(arrayList.get(position).getTv_quantity());

    }

    @Override
    public int getItemCount() { //리스트 아이템의 개수
        return arrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_hamburger;
        TextView tv_select_menu;
        TextView tv_price;
        TextView tv_quantity;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_hamburger = itemView.findViewById(R.id.iv_hamburger);
            this.tv_select_menu = itemView.findViewById(R.id.tv_select_menu);
            this.tv_price = itemView.findViewById(R.id.tv_price);
            this.tv_quantity = itemView.findViewById(R.id.tv_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                 final int curPos = getAdapterPosition(); // 현재 클릭한 아이템의 포지션(위치 .. 0부터 시작..)
                    final Dialog dialogMod = new Dialog(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                    dialogMod.setContentView(R.layout.dialog_modify); //xml레이아웃 연결
                    final EditText et_selectmenu = dialogMod.findViewById(R.id.et_selectmenu);
                    final EditText et_quantity = dialogMod.findViewById(R.id.et_quantity);
                    Button btn_modify = dialogMod.findViewById(R.id.btn_modify);
                    btn_modify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           if(et_selectmenu.getText().length() == 0 || et_quantity.getText().length() == 0 ){
                               //입력필드에 값을 적지 않은 상황이라면
                               Toast.makeText(context, "수정된 입력이 없습니다.", Toast.LENGTH_SHORT).show();
                               return; // if문을 벗어나 onclick 수행이 취소가 된다.

                           }
                            Select_List item = new Select_List();
                            item.setTv_select_menu(et_selectmenu.getText().toString());
                            item.setTv_quantity(et_quantity.getText().toString());
                            arrayList.set(curPos, item); //리스트에 있는 데이터 수정
                            notifyItemChanged(curPos); // 수정 완료 후 새로고침
                            dialogMod.dismiss(); //다이얼로그 닫기

                        }
                    });
                    Button btn_delete = dialogMod.findViewById(R.id.btn_delete); //삭제 버튼
                    btn_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            arrayList.remove(curPos);
                            notifyItemRemoved(curPos);
                            notifyItemRangeChanged(curPos, arrayList.size());
                            dialogMod.dismiss();

                        }
                    });


                    dialogMod.show();


                }
            });
        }
    }
}
