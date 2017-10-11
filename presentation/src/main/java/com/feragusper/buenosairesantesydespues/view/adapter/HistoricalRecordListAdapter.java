package com.feragusper.buenosairesantesydespues.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
public class HistoricalRecordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;
    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<HistoricalRecordModel> historicalRecordsCollection = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private boolean hasError;

    public HistoricalRecordListAdapter(Context context, Collection<HistoricalRecordModel> historicalRecordsCollection) {
        this.context = context;
        this.validateUsersCollection(historicalRecordsCollection);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.historicalRecordsCollection = (List<HistoricalRecordModel>) historicalRecordsCollection;
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

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder view = null;
        if (viewType == VIEW_TYPE_ITEM) {
            view = new HistoricalRecordViewHolder(layoutInflater.inflate(R.layout.historical_record_item, parent, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            view = new LoadingViewHolder(layoutInflater.inflate(R.layout.layout_loading_item, parent, false));
        }

        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HistoricalRecordViewHolder) {
            final HistoricalRecordModel historicalRecordModel = this.historicalRecordsCollection.get(position);
            ((HistoricalRecordViewHolder) holder).textViewTitle.setText(historicalRecordModel.getTitle());
            Picasso.with(context).load(historicalRecordModel.getThumbnail()).placeholder(R.drawable.loading).into(((HistoricalRecordViewHolder) holder).avatar);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (HistoricalRecordListAdapter.this.onItemClickListener != null) {
                        HistoricalRecordListAdapter.this.onItemClickListener.onHistoricalRecordItemClicked(historicalRecordModel);
                    }
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
            if (hasError) {
                loadingViewHolder.progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position >= historicalRecordsCollection.size() || historicalRecordsCollection.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (this.historicalRecordsCollection != null) ? this.historicalRecordsCollection.size() + 1 : 0;
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

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
