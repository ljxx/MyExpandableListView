package com.alertviewdemo.www.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alertviewdemo.www.OtherActivity;
import com.alertviewdemo.www.R;
import com.alertviewdemo.www.bean.User;

import java.util.List;
import java.util.Map;

public class MyListAdapter extends BaseExpandableListAdapter {

	private List<String> mGroup;// 组名
	private Map<Integer, List<User>> mChildren;// 每一组对应的child
	private LayoutInflater mInflater;
	private Context mContext;

	public MyListAdapter(Context context,List<String> group,Map<Integer, List<User>> children) {
		this.mInflater = LayoutInflater.from(context);
		this.mGroup = group;
		this.mChildren = children;
		this.mContext = context;
	}

	public void addUser(User u) {
		int groupId = u.getGroup();
		if (groupId < mGroup.size()) {
			mChildren.get(groupId).add(u);
			notifyDataSetChanged();// 更新一下
		}
	}

	public Object getChild(int groupPosition, int childPosition) {
		return mChildren.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return mChildren.get(groupPosition).size();
	}

	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder vHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.contact_list_item_for_buddy, null);
			vHolder = new ViewHolder();
			vHolder.nick = (TextView) convertView.findViewById(R.id.contact_list_item_name);
			vHolder.head = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(vHolder);
		}else {
			vHolder = (ViewHolder) convertView.getTag();
		}

		final User u = (User) getChild(groupPosition, childPosition);
		vHolder.nick.setText(u.getNick());
		vHolder.head.setImageResource(u.getHeadIcon());

		convertView.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//每一项单击事件
				Intent intent = new Intent();
				intent.setClass(mContext, OtherActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("headImage", u.getHeadIcon());
				bundle.putString("userName", u.getNick());
				intent.putExtras(bundle);
				mContext.startActivity(intent);
			}
		});

		convertView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				new AlertDialog.Builder(mContext)
						.setMessage("确定删除 " + u.getNick()+" 吗？")
						.setPositiveButton(android.R.string.ok,
								new OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,int which) {
										//长按删除事件
									}
								})
						.setNegativeButton(android.R.string.cancel, null)
						.create().show();
				return false;
			}
		});
		return convertView;
	}

	class ViewHolder{
		public TextView nick;
		public ImageView head;
	}

	public Object getGroup(int groupPosition) {
		return mGroup.get(groupPosition);
	}

	public int getGroupCount() {
		return mGroup.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * create group view and bind data to view
	 */
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.contact_buddy_list_group, null);
		}
		TextView groupName = (TextView) convertView.findViewById(R.id.group_name);
		groupName.setText(getGroup(groupPosition).toString());
		TextView onlineNum = (TextView) convertView.findViewById(R.id.online_count);

		onlineNum.setText(getChildrenCount(groupPosition) + "/"+ getChildrenCount(groupPosition));

		ImageView indicator = (ImageView) convertView.findViewById(R.id.group_indicator);
		if (isExpanded)
			indicator.setImageResource(R.drawable.indicator_expanded);
		else
			indicator.setImageResource(R.drawable.indicator_unexpanded);
		return convertView;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}
}
