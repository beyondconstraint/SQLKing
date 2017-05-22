package com.memtrip.sqlking.sample.comment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.memtrip.sqlking.sample.R;
import com.memtrip.sqlking.sample.model.Comment;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> comments;

    void addAll(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_adapter, parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.populate(comment);
    }

    @Override
    public int getItemCount() {
        return comments != null ? comments.size() : 0;
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.comment_adapter_author)
        TextView author;

        @Bind(R.id.comment_adapter_body)
        TextView body;

        @Bind(R.id.comment_adapter_timestamp)
        TextView timestamp;

        private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE d MMM HH:mm:ss")
                .withLocale(Locale.UK);

        CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void populate(Comment comment) {
            author.setText(comment.getUser().getUsername());
            body.setText(comment.getBody());
            timestamp.setText(dateTimeFormatter.print(comment.getTimestamp()));
        }
    }
}
