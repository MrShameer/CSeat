package com.example.cseat.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cseat.QuickAccess;
import com.example.cseat.R;
import com.example.cseat.SectionPelajar;
import com.example.cseat.TabPelajar;

import java.util.List;

public class RecyclerPelajar extends RecyclerView.Adapter<RecyclerPelajar.ViewHolder>{

   // private static final String TAG = "RecyclerAdapter";
    int count = 0;
    List<String> studentsname, studentclass, studentproblem;

    public RecyclerPelajar(List<String> studentsname, List<String> studentclass, List<String> studentproblem) {
        this.studentsname = studentsname;
        this.studentclass = studentclass;
        this.studentproblem= studentproblem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     //  Log.i(TAG, "onCreateViewHolder: " + count++);
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
    }

    @Override
    public int getItemCount() {

        return studentsname.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView, rctv,power;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.tvclass);
            rctv =(TextView) itemView.findViewById(R.id.rctv);
            power = itemView.findViewById(R.id.power);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(QuickAccess.this,"dfd",Toast.LENGTH_SHORT).show();

            //Log.d("tekan","sfgdg");
        }
    }
}
