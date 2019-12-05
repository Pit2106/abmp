package com.pruebas.abmp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pruebas.abmp.models.Animal;

import java.util.ArrayList;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder>
        implements View.OnClickListener {

    private static final String AMPHIBIANS = "Amphibia";
    private static final String BIRDS = "aves";
    private static final String MAMMALS = "Mammalia";

    private Context context;

    private ArrayList<Animal> animals;
    private View.OnClickListener listener;
    public AnimalListAdapter(){
        animals=new ArrayList<Animal>();
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animals_list, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal a = animals.get(position);
        if(a.getKind().equals(AMPHIBIANS)){

            holder.lyCardView.setBackgroundColor(context.getResources().getColor(R.color.colorAmphibians));
            holder.imgCard.setImageResource(R.mipmap.ic_amphibians);
        }
        if(a.getKind().equals(BIRDS)){
            holder.lyCardView.setBackgroundColor(context.getResources().getColor(R.color.colorBirds));
            holder.imgCard.setImageResource(R.mipmap.ic_birds);
        }
        if(a.getKind().equals(MAMMALS)){
            holder.lyCardView.setBackgroundColor(context.getResources().getColor(R.color.colorMammals));
            holder.imgCard.setImageResource(R.mipmap.ic_mammals);
        }
        holder.tvName.setText(a.getName());
        holder.tvOrder.setText(a.getOrder());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public void addList(ArrayList<Animal> arrayAnimals) {

        animals.addAll(arrayAnimals);
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvOrder;
        private LinearLayout lyCardView;
        private ImageView imgCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCard = itemView.findViewById(R.id.imgCard);
            lyCardView = itemView.findViewById(R.id.lyCardView);
            tvName = itemView.findViewById(R.id.tvName);
            tvOrder = itemView.findViewById(R.id.tvOrder);
        }
    }
}