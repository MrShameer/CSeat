package com.example.cseat.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.QuickAccess;
import com.example.cseat.R;
import com.example.cseat.SectionPelajar;
import com.example.cseat.TabPelajar;

import java.util.List;

public class RecyclerPelajar extends RecyclerView.Adapter<RecyclerPelajar.ViewHolder>{
    List<String> studentsname, studentclass, studentproblem;
    boolean isexpand;
    public RecyclerPelajar(List<String> studentsname, List<String> studentclass, List<String> studentproblem) {
        this.studentsname = studentsname;
        this.studentclass = studentclass;
        this.studentproblem= studentproblem;
        isexpand = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pelajar_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rctv.setText(studentsname.get(position));
        holder.textView.setText(studentclass.get(position));
        holder.power.setText(studentproblem.get(position));
        holder.expand.setVisibility(isexpand ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return studentsname.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView, rctv,power;
        ConstraintLayout expand,cl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.tvclass);
            rctv =(TextView) itemView.findViewById(R.id.rctv);
            power = itemView.findViewById(R.id.power);
            expand = itemView.findViewById(R.id.expandableLayout);
            cl = itemView.findViewById(R.id.cl);

            cl.setOnClickListener(new View.OnClickListener() {
                 @Override
                public void onClick(View v) {
                     isexpand = !isexpand;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
