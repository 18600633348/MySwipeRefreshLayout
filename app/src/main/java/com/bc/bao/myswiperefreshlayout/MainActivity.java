package com.bc.bao.myswiperefreshlayout;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mLayout;
    private RecyclerView rv;
    private List<String> datas = new ArrayList<String>();
    private  MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        rv = (RecyclerView) findViewById(R.id.rv);
        mLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        initRecyclerView();
        initRefeshLayout();
    }

    private void initRecyclerView() {
        myAdapter = new MyAdapter(datas);
        rv.setLayoutManager(new LinearLayoutManager(this));
///     rv.setLayoutManager(new GridLayoutManager(this,3));
///     rv.setLayoutManager(new StaggeredGridLayoutManager(
///               3,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(myAdapter);
        rv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String str) {
                Toast.makeText(MainActivity.this, position + str, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRefeshLayout(){
        mLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mLayout.setDistanceToTriggerSync(100);
        mLayout.setProgressBackgroundColorSchemeColor(
                getResources().getColor(R.color.item_press));
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<10; i++){
                            myAdapter.addData(i,"sss"+i);
                        }

                        myAdapter.notifyItemRangeChanged(0,10);
                        rv.scrollBy(0,datas.size());
                        mLayout.setRefreshing(false);//ture 正在刷新 false 没有刷新或刷新完成
                    }
                },3000);
            }
        });
    }

    private void initData() {
        datas.add("as");
        datas.add("ass");
        datas.add("asss");
        datas.add("assss");
        datas.add("asssss");
        datas.add("assssss");
        datas.add("asssssss");
        datas.add("assssssss");
        datas.add("asssssssss");
        datas.add("assssssssss");
        datas.add("asssssssssss");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add:
                myAdapter.addData(0,"new str");
                break;
            case R.id.action_delete:
                myAdapter.removeData(1);
                break;
            case R.id.action_grideview:
                rv.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_listview:
                rv.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staview:
                rv.setLayoutManager(new StaggeredGridLayoutManager(
                        3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return true;
    }
}
