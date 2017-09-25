package com.feragusper.buenosairesantesydespues.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Adaptar that manages a collection of {@link HistoricalRecordModel}.
 */
public class HistoricalRecordsAdapter extends RecyclerView.Adapter<HistoricalRecordsAdapter.HistoricalRecordViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<HistoricalRecordModel> historicalRecordsCollection = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public HistoricalRecordsAdapter(Context context, Collection<HistoricalRecordModel> historicalRecordsCollection) {
        this.context = context;
        this.validateUsersCollection(historicalRecordsCollection);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.historicalRecordsCollection = (List<HistoricalRecordModel>) historicalRecordsCollection;
    }

    @Override
    public int getItemCount() {
        return (this.historicalRecordsCollection != null) ? this.historicalRecordsCollection.size() : 0;
    }

    @Override
    public HistoricalRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoricalRecordViewHolder(this.layoutInflater.inflate(R.layout.historical_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoricalRecordViewHolder holder, final int position) {
        final HistoricalRecordModel historicalRecordModel = this.historicalRecordsCollection.get(position);
        holder.textViewTitle.setText(historicalRecordModel.getTitle());
        Picasso.with(context).load(historicalRecordModel.getThumbnail()).placeholder(R.drawable.loading).into(holder.avatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HistoricalRecordsAdapter.this.onItemClickListener != null) {
                    HistoricalRecordsAdapter.this.onItemClickListener.onHistoricalRecordItemClicked(historicalRecordModel);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setHistoricalRecordsCollection(Collection<HistoricalRecordModel> historicalRecordsCollection) {
        validateUsersCollection(historicalRecordsCollection);
        this.historicalRecordsCollection.addAll(historicalRecordsCollection);
        notifyDataSetChanged();
//        notifyItemRangeInserted();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<HistoricalRecordModel> historicalRecordsCollection) {
        if (historicalRecordsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public interface OnItemClickListener {
        void onHistoricalRecordItemClicked(HistoricalRecordModel historicalRecordModel);
    }

    static class HistoricalRecordViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title)
        TextView textViewTitle;

        @InjectView(R.id.avatar)
        ImageView avatar;

        HistoricalRecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
