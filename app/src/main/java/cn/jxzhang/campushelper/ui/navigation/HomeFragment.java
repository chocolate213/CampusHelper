package cn.jxzhang.campushelper.ui.navigation;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.adapter.ArticleAdapter;
import cn.jxzhang.campushelper.model.Article;
import cn.jxzhang.campushelper.util.ToastUtils;

public class HomeFragment extends Fragment {
    private static final String PAGE_INDEX = "index";

    private static final int DATASET_COUNT = 60;

    private int index;

    private List<Article> articles;

    @BindView(R.id.article_list)
    RecyclerView mArticleList;

    @BindView(R.id.refresh_article)
    SwipeRefreshLayout mRefreshArticle;

    public HomeFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index index.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(PAGE_INDEX);
        }

        Log.d("TAG", String.valueOf(index));

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, result);
        initRecycleView();
        initSwipeToRefreshLayout();
        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshArticle.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DummyBackgroundTask().execute();
            }
        });
    }

    private void initSwipeToRefreshLayout() {
        mRefreshArticle.setColorSchemeColors(
                getResources().getColor(R.color.refresh_color_1),
                getResources().getColor(R.color.refresh_color_2),
                getResources().getColor(R.color.refresh_color_3),
                getResources().getColor(R.color.refresh_color_4)
        );
    }

    private void initRecycleView() {
        int scrollPosition = 0;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        ArticleAdapter mAdapter = new ArticleAdapter(articles);
        mArticleList.setAdapter(mAdapter);
        mArticleList.setLayoutManager(mLayoutManager);

        if (mArticleList.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mArticleList.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mArticleList.setLayoutManager(mLayoutManager);
        mArticleList.scrollToPosition(scrollPosition);
    }

    private void initDataset() {
        articles = Arrays.asList(
                new Article("Video game Baftas get underway",
                        "Uncharted 4 was nominated for best narrative and artistic achievement as well as best game The puzzle-platformer Inside was the first winner at this year's Bafta Games Awards",
                        R.drawable.article_img1),
                new Article("POLL: 40 Percent of Americans More Cautious With Email After Election Hacking",
                        "Forty percent of Americans say they are more cautious about what they write in emails since last year's cyber attacks against the Democratic Party, according to a Reuters/Ipsos poll released on Thursday.",
                        R.drawable.article_img2),
                new Article("Astronaut John Glenn Laid to Rest at Arlington National Cemetery",
                        "Annie Glenn, seated, widow of John Glenn, watches as members of the U.S. Marine Corps from Marine Barracks Washington carry the casket of her husband during his graveside service at Arlington National Cemetery in Arlington,",
                        R.drawable.article_img3),
                new Article("Ofo cranks up the heat with BeiDou locks for its bikes",
                        "Chinese bike-sharing startup ofo Inc will equip its bicycles with BeiDou-enabled smart locks, as part of its efforts to leverage the nation's homegrown BeiDou navigation satellite system to offer better-positioning services.",
                        0),
                new Article("Tensions Rise as General Strike Paralyzes Argentina",
                        "Protestors are sprayed with water by Argentine police as they block a road during a 24-hour national strike in Buenos Aires, Argentina, April 6, 2017.",
                        R.drawable.article_img4),
                new Article("Huawei wins case vs Samsung",
                        "A Chinese court has ruled Samsung Electronics Co Ltd's Chinese subsidiaries must pay 80 million yuan ($11.6 million) to Huawei Technologies Co Ltd for patent infringement.",
                        0),
                new Article("Tech Firms Must Go Beyond Congo's ‘Conflict Minerals’ to Clean Supply Chain: Study",
                        "Abuses linked to mining in countries such as Myanmar and Colombia are being overlooked by technology companies focused only on eliminating \"conflict minerals\" from war-torn parts of Africa in their supply chains, researchers said on Thursday.",
                        R.drawable.article_img5),
                new Article("Atmosphere found around Earth-like planet GJ 1132b",
                        "Artist's impression of GJ 1132b: The planet's thick atmosphere may contain water or methane.",
                        R.drawable.article_img6),
                new Article("Syria war: US launches missile strikes following chemical 'attack'",
                        "The US has carried out a missile attack against targets in Syria in response to a suspected chemical weapons attack on a rebel-held town.",
                        0),
                new Article("Trump Adviser From Wall St. Backs US Bank Breakup Law",
                        "White House economic adviser Gary Cohn said he backed bringing back the Glass-Steagall Act, a Depression-era law that would revamp Wall Street banks by splitting their consumer-lending businesses from their investment arms.",
                        R.drawable.article_img7),
                new Article("UN report: Clean power is up, costs are down",
                        "The cost of offshore wind energy installations has tumbled over the past three years",
                        R.drawable.article_img8),
                new Article("Britney Spears Show Causes Israeli Election Change",
                        "Pop star Britney Spears may not be topping the charts these days, but she’s still big enough to influence elections in another country.",
                        0),
                new Article("Damian Lewis's ear almost ruined opening night of The Goat",
                        "Damian Lewis has revealed he thought he was going to faint on stage during the opening night of his new West End play, The Goat.",
                        R.drawable.article_img9),
                new Article("Turkey Targets Social Media Users Ahead of Referendum",
                        "A statue of modern Turkey's founder Mustafa Kemal Ataturk and a poster of Turkey's President Recep Tayyip Erdogan for the upcoming referendum is seen in his hometown city of Rize, April 4, 2017.",
                        R.drawable.article_img10)

        );
    }

    /**
     * Dummy {@link AsyncTask} which simulates a long running task to fetch new cheeses.
     */
    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected List<String> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            mRefreshArticle.setRefreshing(false);

            ToastUtils.toast(getActivity(), "新鲜事都让你刷没了~");
        }

    }
}
