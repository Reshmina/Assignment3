package com.example.assignment3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private RealmResults<Person> mPersonRealmResults;
    private Context mContext;
    private static final String TAG = "Experiment";
    private int count;

    public MyAdapter(RealmResults<Person> persons, Context context){
        mPersonRealmResults = persons;
        mContext = context;
        count = 0;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
        Log.i(TAG, "onCreateViewHolder: Triggered And Also the Parent Id is " + parent.getId());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: Triggered");
        Person person = mPersonRealmResults.get(position);
        holder.name.setText(person.getName());
        holder.phone.setText(person.getPhone());
        holder.dept.setText(person.getDept());
        holder.roll.setText(person.getRoll());
        if(person.isGender()){
            holder.gender.setText("Female");
        }else
            holder.gender.setText("Male");
        if(person.getDept().toString().equals("CSE"))
            holder.layout.setBackgroundColor(Color.argb(255,76,175,80));
        else if(person.getDept().toString().equals("IT"))
            holder.layout.setBackgroundColor(Color.argb(255,21,101,192));
        else if(person.getDept().toString().equals("ECE"))
            holder.layout.setBackgroundColor(Color.argb(255,251,192,45));
        else if(person.getDept().toString().equals("EE"))
            holder.layout.setBackgroundColor(Color.argb(255,211,47,47));
    }

    @Override
    public int getItemCount() {
        return mPersonRealmResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone;
        private TextView gender;
        private TextView dept;
        private TextView roll;
        private RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            count++;
            Log.i(TAG, "MyViewHolder: Number of Active ViewHolders:" + count);
            name = itemView.findViewById(R.id.name_tv);
            phone = itemView.findViewById(R.id.phone_tv);
            gender=itemView.findViewById(R.id.gender_tv);
            dept=itemView.findViewById(R.id.dept_tv);
            roll=itemView.findViewById(R.id.roll_tv);
            layout=itemView.findViewById(R.id.category_image);
        }
    }
}
