package com.diablo.floatlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        //set recycleview
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
        RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("position","="+position);
                Toast.makeText(MainActivity.this, productList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        MasonryAdapter adapter=new MasonryAdapter(productList,itemClickListener);
        recyclerView.setAdapter(adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private void initData() {
        productList=new ArrayList<Product>();
        Product p1=new Product(R.mipmap.p1,"我是照片1");
        productList.add(p1);
        Product p2=new Product(R.mipmap.p2,"我是照片2");
        productList.add(p2);
        Product p3=new Product(R.mipmap.p3,"我是照片3");
        productList.add(p3);
        Product p4=new Product(R.mipmap.p4,"我是照片4");
        productList.add(p4);
        Product p5=new Product(R.mipmap.p5,"我是照片5");
        productList.add(p5);
        Product p6=new Product(R.mipmap.p6,"我是照片6");
        productList.add(p6);
        Product p7=new Product(R.mipmap.p2,"我是照片7");
        productList.add(p7);
        Product p8=new Product(R.mipmap.p1,"我是照片8");
        productList.add(p8);
        Product p9=new Product(R.mipmap.p4,"我是照片9");
        productList.add(p9);
        Product p10=new Product(R.mipmap.p6,"我是照片10");
        productList.add(p10);
        Product p11=new Product(R.mipmap.p3,"我是照片11");
        productList.add(p11);
    }


}
