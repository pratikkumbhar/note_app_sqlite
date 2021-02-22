package com.example.mynotesapp.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynotesapp.R;
import java.util.List;

public class listAdaptor extends RecyclerView.Adapter<listAdaptor.ViewHolder> {

    private List<Holder> list;
    private Context context;
    private OnNoteListener onNoteListener;

    public listAdaptor(List<Holder> list,OnNoteListener listener) {
        this.list = list;
        this.onNoteListener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_iteam,parent,false);


        return new ViewHolder(v,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Holder holder1 = list.get(position);

            holder.title.setText(holder1.getTitle());
        holder.note.setText(holder1.getNote());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView note;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.note_container_title);
            note = (TextView)itemView.findViewById(R.id.note_container_notee);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNotechick(v , getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNotechick(View v ,int position);


    }

}
