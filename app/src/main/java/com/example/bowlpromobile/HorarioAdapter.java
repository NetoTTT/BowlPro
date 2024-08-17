package com.example.bowlpromobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bowlpromobile.Horario;
import com.example.bowlpromobile.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {

    private List<Horario> horarios;
    private Set<Integer> selectedItems = new HashSet<>();

    public HorarioAdapter(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Horario horario = horarios.get(position);
        holder.dataHorarioTextView.setText(horario.getData_horario());
        holder.horarioTextView.setText(horario.getHorario());
        holder.checkBoxSelect.setChecked(selectedItems.contains(position));

        holder.checkBoxSelect.setOnClickListener(v -> {
            if (holder.checkBoxSelect.isChecked()) {
                selectedItems.add(position);
            } else {
                selectedItems.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }

    public Set<Integer> getSelectedItems() {
        return selectedItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataHorarioTextView;
        TextView horarioTextView;
        CheckBox checkBoxSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataHorarioTextView = itemView.findViewById(R.id.dataHorarioTextView);
            horarioTextView = itemView.findViewById(R.id.horarioTextView);
            checkBoxSelect = itemView.findViewById(R.id.checkBoxSelect);
        }
    }
}
