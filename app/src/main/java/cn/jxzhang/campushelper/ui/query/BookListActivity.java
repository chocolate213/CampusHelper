package cn.jxzhang.campushelper.ui.query;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jxzhang.campushelper.R;
import cn.jxzhang.campushelper.adapter.BookInfoAdapter;
import cn.jxzhang.campushelper.base.BaseAppCompatActivity;
import cn.jxzhang.campushelper.model.BookInfo;
import cn.jxzhang.campushelper.open.Constants;
import cn.jxzhang.campushelper.util.LibraryHTMLParse;
import cn.jxzhang.campushelper.util.LibraryHttpUtil;
import cn.jxzhang.campushelper.util.ToastUtils;

public class BookListActivity extends BaseAppCompatActivity {

    private SearchBookTask mAuthTask = null;
    private LinearLayout mBookProgressBar;
    private LinearLayout mBookListView;
    private LinearLayout mBookErrorPage;
    private String mBookTotalRecord;
    private TextView mBookTotalRecordInfo;

    private List<BookInfo> bookList = new ArrayList<BookInfo>();
    ListView mListView;
    private static int errorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("查询结果");
        initView();
        String bookName = getSearchQuery(getIntent());
        showProgress(true);
        mAuthTask = new SearchBookTask(bookName);
        mAuthTask.execute((Void) null);
    }

    private void initView() {
        mBookProgressBar = (LinearLayout) findViewById(R.id.book_progress_bar);
        mBookListView = (LinearLayout) findViewById(R.id.book_list_view);
        mBookErrorPage = (LinearLayout)findViewById(R.id.error_page_layout);
        mBookTotalRecordInfo = (TextView) findViewById(R.id.total_book_num);
        mListView = (ListView) findViewById(R.id.book_list);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_book_list;
    }

    private String getSearchQuery(Intent intent) {
        String bookName = "";
        if (intent == null)
            return null;
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            bookName = intent.getStringExtra(SearchManager.QUERY);
        }
        return bookName;
    }

    private class SearchBookTask extends AsyncTask<Void, Void, Boolean> {

        private final String book_name;
        private LibraryHttpUtil libraryHttpUtil;
        public SearchBookTask(String _book_name) {
            book_name = _book_name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            libraryHttpUtil = new LibraryHttpUtil(book_name, BookListActivity.this.getApplicationContext());
            if(libraryHttpUtil.getBookInfo()){
                LibraryHTMLParse libraryHTMLParse = new LibraryHTMLParse(BookListActivity.this.getApplicationContext());
                bookList = libraryHTMLParse.getList();
                if(bookList.size() == 0){
                    errorCode = Constants.LOGIN_NO_SEARCH_BOOKS;
                    return false;
                } else {
                    return true;
                }
            }
            errorCode = libraryHttpUtil.getErrorMessageCode();
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mAuthTask = null;
            if (result) {
                BookInfoAdapter bookInfoAdapter = new BookInfoAdapter(BookListActivity.this, R.layout.book_list_item, bookList);
                mListView.setAdapter(bookInfoAdapter);
                mListView.requestFocus();
                //共查找到（）本书；
                mBookTotalRecord = bookList.get(0).getB_10_TotalRecord();
                if(Integer.parseInt(mBookTotalRecord) > 500){
                    mBookTotalRecordInfo.setText("共为您找到" + mBookTotalRecord + "条搜索结果,以下为您展示前500条：");
                    Toast.makeText(BookListActivity.this, "与关键词匹配的书目过多，请尝试重新键入合适的关键词以缩小检索范围", Toast.LENGTH_LONG).show();
                } else {
                    mBookTotalRecordInfo.setText("共为您找到" + mBookTotalRecord + "条搜索结果:");
                }

                //监听ListViewItem点击事件
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        BookInfo bookInfo = bookList.get(position);
                        String book_code = bookInfo.getB_11_Codes();
                        String book_name = bookInfo.getB_1_Name();
                        //打开图书结果Activity
                        Intent intent = new Intent(BookListActivity.this, BookInfoActivity.class);
                        intent.putExtra("book_code", book_code);
                        intent.putExtra("book_name", book_name);
                        startActivity(intent);
                    }
                });

                showProgress(false);
            } else {
                showProgress(false);
                showErrorPage(true);
                switch (errorCode){
                    case Constants.LOGIN_NETWORK_WRONG:
                        ToastUtils.toast(BookListActivity.this,"啊哦...网络好像出了点问题...");
                        break;
                    case Constants.LOGIN_SYSTEM_WRONG:
                        ToastUtils.toast(BookListActivity.this,"系统错误，请重试");
                        break;
                    case Constants.LOGIN_NO_SEARCH_BOOKS:
                        ToastUtils.toast(BookListActivity.this,"没有找到相关书籍，请检查书名是否正确或尝试重新检索");
                    default:
                        break;
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
            showErrorPage(true);
        }

    }


    private void showProgress(final boolean show) {
        mBookProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mBookListView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
    private void showErrorPage(final boolean show){
        mBookProgressBar.setVisibility(show ? View.GONE : View.VISIBLE);
        mBookListView.setVisibility(show ? View.GONE : View.VISIBLE);
        mBookErrorPage.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
