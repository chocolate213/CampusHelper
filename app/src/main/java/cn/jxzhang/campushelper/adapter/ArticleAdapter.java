package cn.jxzhang.campushelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.model.Article;

/**
 * Created on 2017-04-05 20:02
 * <p>Title:       ArticleAdapter</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articles;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artical_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTitle().setText(articles.get(position).getTitle());
        holder.getDescription().setText(articles.get(position).getDescription());
        if (articles.get(position).getImage() == 0) {
            holder.getImage().setVisibility(View.GONE);
            holder.getContainer().setLayoutParams(layoutParams);
        } else {
            holder.getImage().setImageResource(articles.get(position).getImage());
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final ImageView image;
        private final LinearLayout container;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onclick");
                }
            });

            title = (TextView) v.findViewById(R.id.article_title);
            description = (TextView) v.findViewById(R.id.article_description);
            image = (ImageView) v.findViewById(R.id.article_image);
            container = (LinearLayout) v.findViewById(R.id.article_container);
        }

        TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }

        public ImageView getImage() {
            return image;
        }

        public LinearLayout getContainer() {
            return container;
        }

    }
}
