package org.xfl.chatapp;


import org.xfl.chat.dao.MessageDao;
import org.xfl.chat.utils.DBHelper;
import org.xfl.chat.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChatActivity extends Activity {

	private static final String TAG = ChatActivity.class.getName();
	private Button btnSend;
	private ImageButton ibFace;
	private EditText etMsg;
	private ListView lvShowMsg;
	private static ChatMsgViewAdapter msgAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Log.i(TAG, "----> onCreate()");
		init();
		btnSend.setOnClickListener(new SendMsgOnClicListener());
		ibFace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		etMsg.setOnKeyListener(new EnterKeyListener());
	};
	
	private void init() {
		etMsg = (EditText) findViewById(R.id.edt_msg);
		ibFace = (ImageButton) findViewById(R.id.ibtn_face);
		lvShowMsg = (ListView) findViewById(R.id.lst_chat);
		btnSend = (Button) findViewById(R.id.btn_send);
		if (msgAdapter == null) {
			msgAdapter = new ChatMsgViewAdapter(getApplicationContext());
		}
		lvShowMsg.setAdapter(msgAdapter);
		lvShowMsg.setSelection(msgAdapter.getCount()-1);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}
	
	private class SendMsgOnClicListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			sendMsg();
		}
	}
	
	private class EnterKeyListener implements OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
				sendMsg();
				return true;
			}
			return false;
		}
	}
	
	@Override
	protected void onResume() {
		Log.i(TAG, "----> onResume()");
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		Log.i(TAG, "----> onStart()");
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		Log.i(TAG, "----> onStop()");
		super.onStop();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.i(TAG, "onConfigurationChanged()----> orientation");
		}
		else {
			Log.i(TAG, "onConfigurationChanged()----> not orientation");
		}
	}
	
	private boolean sendMsg() {
		String sendMsg = etMsg.getText().toString();
		if (null == sendMsg) {
			return false;
		}
		if (("").equals(sendMsg)) {
			return false;
		}
		msgAdapter.add(new ChatMessageEntity("xfl", Utils.getCurrDateTime(), sendMsg, 0, false));
		etMsg.setText("");
		//自动回复
		msgAdapter.add(new ChatMessageEntity("机器人", Utils.getCurrDateTime(), "卧槽，回家，打灰机", 0, true));
		msgAdapter.notifyDataSetChanged();
		lvShowMsg.setSelection(msgAdapter.getCount()-1);
		return true;
	}
	public boolean isSendSucc() {
		return true;
	}
	
	public SQLiteDatabase createDB() {
		DBHelper helper = new DBHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		helper.close();
		return db;
	}
	
	public void addMsg() {
		ChatMessageEntity msg = new ChatMessageEntity();
    	msg.setName("xfl");
    	msg.setContent("hello mm");
    	msg.setDate(Utils.getCurrDateTime());
    	msg.setLeft(false);
    	MessageDao dao = new MessageDao(this);
    	dao.addMessage(msg);
	}
}
