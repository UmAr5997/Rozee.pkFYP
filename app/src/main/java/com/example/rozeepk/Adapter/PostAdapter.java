package com.example.rozeepk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rozeepk.Class.Post;
import com.example.rozeepk.Activity.FullPostActivity;
import com.example.rozeepk.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> items;
    private Context context;

    public PostAdapter(Context context, List<Post> item) {
        this.context = context;
        this.items=item;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post_job_layout, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txttitle.setText(items.get(position).getJobtitle());
        holder.txtcompany.setText(items.get(position).getIndustry());
        holder.txtcity.setText(items.get(position).getCity());
        holder.txtdate.setText(items.get(position).getPost_valid_date());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FullPostActivity.class);
                intent.putExtra("id",items.get(position).getId());
                intent.putExtra("jobtitle",items.get(position).getJobtitle());
                intent.putExtra("jobdesc",items.get(position).getJobdesc());
                intent.putExtra("jobexp",items.get(position).getJobexp());
                intent.putExtra("city",items.get(position).getCity());
                intent.putExtra("industry",items.get(position).getIndustry());
                intent.putExtra("jobtype",items.get(position).getJobtype());
                intent.putExtra("department",items.get(position).getDepartment());
                intent.putExtra("eduction",items.get(position).getEduction());
                intent.putExtra("careerlevel",items.get(position).getCareerlevel());
                intent.putExtra("gender",items.get(position).getGender());
                intent.putExtra("age",items.get(position).getAge());
                intent.putExtra("postdate",items.get(position).getPost_date());
                intent.putExtra("applydate",items.get(position).getPost_valid_date());
                intent.putExtra("img",items.get(position).getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txttitle,txtcompany,txtcity,txtdate;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            txttitle=itemView.findViewById(R.id.jobtitle);
            txtcompany=itemView.findViewById(R.id.companyname);
            txtcity=itemView.findViewById(R.id.cityname);
            txtdate=itemView.findViewById(R.id.date);
        }
    }
}
