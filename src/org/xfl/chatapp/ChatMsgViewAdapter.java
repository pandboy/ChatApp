package org.xfl.chatapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatMsgViewAdapter extends BaseAdapter {

	private ArrayList<ChatMessageEntity> chatMsgs = new ArrayList<ChatMessageEntity>();
	private LinearLayout wrapper;
	private TextView tvMsg;
	private Context ctx;
	
	public ChatMsgViewAdapter(Context ctx) {
		this.ctx = ctx;
	}
	public void add(ChatMessageEntity object) {
		chatMsgs.add(object);
	}
	
	public int getCount() {
		return chatMsgs.size();
	}
	
	public ChatMessageEntity getItem(int position) {
		// TODO Auto-generated method stub
		return this.chatMsgs.get(position);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ChatMessageEntity entity = getItem(position);
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (entity.isLeft()) {
			rowView = inflater.inflate(R.layout.msg_item_left, parent, false);
			wrapper = (LinearLayout) rowView.findViewById(R.id.wrapper_left);
		}
		else {
			rowView = inflater.inflate(R.layout.msg_item_right, parent, false);
			wrapper = (LinearLayout) rowView.findViewById(R.id.wrapper_right);
		}
		
		tvMsg = (TextView) wrapper.findViewById(R.id.msg_entity);
		tvMsg.setText(entity.getContent());
		//tvMsg.setBackgroundResource(entity.isLeft() ? R.drawable.bubble_yellow: R.drawable.bubble_green);
		wrapper.setGravity(entity.isLeft() ? Gravity.LEFT: Gravity.RIGHT);
		return rowView;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
