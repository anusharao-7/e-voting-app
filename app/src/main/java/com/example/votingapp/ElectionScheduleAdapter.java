package com.example.votingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ElectionScheduleAdapter extends RecyclerView.Adapter<ElectionScheduleAdapter.ElectionViewHolder> {

    private List<Election> electionList;

    public ElectionScheduleAdapter(List<Election> electionList) {
        this.electionList = electionList;
    }

    @NonNull
    @Override
    public ElectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.election_item, parent, false);
        return new ElectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionViewHolder holder, int position) {
        Election election = electionList.get(position);
        holder.electionDate.setText(election.getDate());
        holder.electionTime.setText(election.getTime());
        holder.electionDescription.setText(election.getDescription());
    }

    @Override
    public int getItemCount() {
        return electionList.size();
    }

    public static class ElectionViewHolder extends RecyclerView.ViewHolder {
        TextView electionDate;
        TextView electionTime;
        TextView electionDescription;

        public ElectionViewHolder(@NonNull View itemView) {
            super(itemView);
            electionDate = itemView.findViewById(R.id.electionDate);
            electionTime = itemView.findViewById(R.id.electionTime);
            electionDescription = itemView.findViewById(R.id.electionDescription);
        }
    }
}
