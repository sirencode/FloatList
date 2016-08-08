package com.diablo.floatlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diablo on 16/8/4.
 */
public class InformationsFragment extends Fragment implements LRecyclerView.LScrollListener,InformationsItemClickListener{
    //服务器端一共多少条数据
    private static final int TOTAL_COUNTER = 34;
    //每一页展示多少条数据
    private static final int REQUEST_COUNT = 10;
    //已经获取到多少条数据了
    private static int mCurrentCounter = 0;
    private static final int REFRESH_SUCCESS = 1;
    private static final int REFRESH_FILED = 2;
    private Activity activity;
    private LRecyclerView recyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private boolean isRefresh = false;
    private List<InformationData> productList = new ArrayList<InformationData>();
    private InformationsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.information_list, container, false);
        activity = getActivity();
        inintViews(baseView);
        return baseView;
    }

    private void inintViews(View view) {
        recyclerView = (LRecyclerView) view.findViewById(R.id.recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InformationsAdapter(productList,getActivity());
        adapter.setItemClickListener(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(getActivity(), adapter);
        recyclerView.setAdapter(mLRecyclerViewAdapter);
        InformationsSpacesItemDecoration decoration = new InformationsSpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLScrollListener(this);
        recyclerView.setRefreshing(true);
        ImageView goTop = (ImageView) view.findViewById(R.id.btn_go_top);
        goTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });
    }

    private void addItems(ArrayList<InformationData> list) {
        adapter.addAll(list);
        mCurrentCounter += list.size();
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {

        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟组装6个数据
                final ArrayList<InformationData> newList = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    InformationData item = new InformationData();
                    item.setType(i);
                    item.setTitle("Diablo " + i);
                    item.setContent("qweqwefqewrqewrqewreqwrqewrqewrqwereqwreqwrqewrqwereqwrrweqrewqreqwrqwerqewrqewrweqqqrewqrewqrqwerweqrqwerewqrewqrqewrweqrewqqewrq");
                    newList.add(item);
                }

                //模拟一下网络请求失败的情况
                if (true) {
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setData(REFRESH_SUCCESS, newList);
                            }
                        });
                    }
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData(REFRESH_FILED, newList);
                        }
                    });
                }
            }
        }.start();
    }

    private void setData(int msg, ArrayList<InformationData> productList) {
        switch (msg) {
            case REFRESH_SUCCESS:
                System.out.println("refresh success");
                refreshOrLoad(productList);
                break;
            case REFRESH_FILED:
                System.out.println("refresh filed");
                onRefreshFiled();
                break;
        }
    }

    private void refreshOrLoad(ArrayList<InformationData> products) {
        if (isRefresh) {
            adapter.clear();
            mCurrentCounter = 0;
        }
        addItems(products);
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            notifyDataSetChanged();
        } else {
            RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Normal);
        }
    }

    private void onRefreshFiled() {
        if (isRefresh) {
            isRefresh = false;
            recyclerView.refreshComplete();
            notifyDataSetChanged();
        } else {
            RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, REQUEST_COUNT, LoadingFooter.State.NetWorkError, mFooterClick);
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        requestData();
    }

    @Override
    public void onScrollUp() {
    }

    @Override
    public void onScrollDown() {
    }

    @Override
    public void onBottom() {
        LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
        if (state == LoadingFooter.State.Loading) {
            System.out.println("the state is Loading, just wait..");
            return;
        }

        if (mCurrentCounter < TOTAL_COUNTER) {
            // loading more
            RecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        } else {
            //the end
            RecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);
        }
    }

    @Override
    public void onScrolled(int distanceX, int distanceY) {
    }

    @Override
    public void onItemClick(InformationData data) {
        Toast.makeText(getActivity(), data.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
