package com.pruebas.abmp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pruebas.abmp.models.Animal;

import java.util.ArrayList;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.ViewHolder>
        implements View.OnClickListener {

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animals_list, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal a = animals.get(position);
        holder.tvName.setText(a.getName());
        holder.tvKingdom.setText(a.getKingdom());
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
        private TextView tvName, tvKingdom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvKingdom = itemView.findViewById(R.id.tvKingdom);
        }
    }
}