package com.alertviewdemo.www;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.alertviewdemo.www.adapter.MyListAdapter;
import com.alertviewdemo.www.bean.User;
import com.alertviewdemo.www.xlistview.MyExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

	private MyExpandableListView mxListView;  //自定义控件
	private MyListAdapter mAdapter;   //适配器
	private List<String> mGroup;// 组名
	private Map<Integer, List<User>> mChildren;// 每一组对应的child

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
	}

	private void initUI() {
		//关联控件的id
		mxListView = (MyExpandableListView) findViewById(R.id.friend_xlistview);

		mxListView.setGroupIndicator(null);
		mxListView.setPullLoadEnable(false);// 禁止下拉加载更多
		mxListView.setXListViewListener(new MyExpandableListView.IXListViewListener() {

			@Override
			public void onRefresh() {
				//设置显示刷新的提示
				initUserData1();     //测试下拉刷新的数据
				mxListView.setAdapter(mAdapter);
				mxListView.stopRefresh();
				mxListView.setRefreshTime(System.currentTimeMillis());
			}

			@Override
			public void onLoadMore() {
			}
		});

		//初始化的数据
		initUserData();
	}

	//测试下拉刷新的数据
	@SuppressLint("UseSparseArrays")
	private void initUserData1() {
		mGroup = new ArrayList<String>();
		mChildren = new HashMap<Integer, List<User>>();// 给每一组实例化child

		// 初始化组名和child
		for (int i = 0; i < groups.length; ++i) {
			mGroup.add(groups[i]);// 组名
			List<User> childUsers = new ArrayList<User>();// 每一组的child
			mChildren.put(i, childUsers);
		}

		nameArray = getResources().getStringArray(R.array.jazzy_effects_ch);
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < mGroup.size(); ++j){
				int icon = heads[i];
				String name = nameArray[i];
				User user = new User(name, icon,0);
				mChildren.get(j).add(user);
			}
		}

		//
		mAdapter = new MyListAdapter(this, mGroup, mChildren);
		mxListView.setAdapter(mAdapter);
	}

	//初始化的数据
	@SuppressLint("UseSparseArrays")
	private void initUserData() {
		mGroup = new ArrayList<String>();
		mChildren = new HashMap<Integer, List<User>>();// 给每一组实例化child

		// 初始化组名和child
		for (int i = 0; i < groups.length; ++i) {
			mGroup.add(groups[i]);// 组名
			List<User> childUsers = new ArrayList<User>();// 每一组的child
			mChildren.put(i, childUsers);
		}

		nameArray = getResources().getStringArray(R.array.jazzy_effects_ch);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < mGroup.size(); ++j){
				int icon = heads[i];
				String name = nameArray[i];
				User user = new User(name, icon,0);
				mChildren.get(j).add(user);
			}
		}

		//加载数据，新建适配器
		mAdapter = new MyListAdapter(this, mGroup, mChildren);
		//列表绑定适配器
		mxListView.setAdapter(mAdapter);
	}

	public static  int[] heads = { R.drawable.h0, R.drawable.h1,
			R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
			R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9,
			R.drawable.h10, R.drawable.h11, R.drawable.h12, R.drawable.h13,
			R.drawable.h14, R.drawable.h15, R.drawable.h16, R.drawable.h17,
			R.drawable.h18, R.drawable.h19, R.drawable.h20, R.drawable.h21};

	public static String[] nameArray = new String[21];

	private static final String[] groups = { "未分组好友", "我的好友", "我的同学", "我的家人",
			"我的同事" };
}
