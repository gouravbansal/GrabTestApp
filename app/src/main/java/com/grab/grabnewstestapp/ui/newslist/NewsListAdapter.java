package com.grab.grabnewstestapp.ui.newslist;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.grab.grabnewstestapp.R;
import com.grab.grabnewstestapp.data.model.News;
import com.grab.grabnewstestapp.ui.MainActivity;
import com.grab.grabnewstestapp.ui.customview.NewsTextView;
import com.grab.grabnewstestapp.ui.newsdetails.NewDetailsFragment;
import com.grab.grabnewstestapp.util.CommonUtil;

import org.w3c.dom.Text;

public class NewsListAdapter extends PagedListAdapter<News, NewsListAdapter.ViewHolder> {

    private MainActivity mainActivity;

    protected NewsListAdapter(MainActivity mainActivity) {
        super(DIFF_CALLBACK);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);
        return new ViewHolder(layoutInflater.inflate(R.layout.item_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News item = (News) getItem(position);
        if (item != null) {
            holder.hours.setText(CommonUtil.getHoursDiff(item.publishedAt).toString() + holder.hours.getContext().getString(R.string.hrsago));
            if (!TextUtils.isEmpty(item.title)) {
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(item.title);
            } else {
                holder.title.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(item.content)) {
                holder.content.setVisibility(View.VISIBLE);
                holder.content.setText(item.content);
            } else {
                holder.content.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(item.author)) {
                holder.fromText.setVisibility(View.VISIBLE);
                holder.from.setVisibility(View.VISIBLE);
                holder.from.setText(item.author);
            } else {
                holder.from.setVisibility(View.GONE);
                holder.fromText.setVisibility(View.GONE);
            }
            Glide.with(holder.itemView.getContext())
                    .load(item.urlToImage)
                    .into(holder.avatar);

            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.replaceFragment(NewDetailsFragment.getInstance(item.url), "NewDetailsFragment");
                }
            });
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        ImageView avatar;
        NewsTextView title;
        NewsTextView hours;
        NewsTextView content;
        NewsTextView from, fromText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            avatar = itemView.findViewById(R.id.avatar);
            title = itemView.findViewById(R.id.title);
            hours = itemView.findViewById(R.id.hours);
            content = itemView.findViewById(R.id.content);
            from = itemView.findViewById(R.id.from);
            fromText = itemView.findViewById(R.id.fromText);

        }
    }

    static final DiffUtil.ItemCallback<News> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<News>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull News oldUser, @NonNull News newUser) {
                    return oldUser.getTitle().equals(newUser.getTitle());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull News oldUser, @NonNull News newUser) {
                    return oldUser.equals(newUser);
                }
            };

}




