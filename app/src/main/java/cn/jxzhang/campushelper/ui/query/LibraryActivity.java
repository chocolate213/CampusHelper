package cn.jxzhang.campushelper.ui.query;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;

public class LibraryActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_library;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_library, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search_book).getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("图书馆馆藏图书查询");
        ComponentName componentName = new ComponentName(this, BookListActivity.class);
        SearchableInfo searchableInfo = searchManager
                .getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);
        return true;
    }
}
