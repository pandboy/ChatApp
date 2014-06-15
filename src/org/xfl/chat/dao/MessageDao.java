package org.xfl.chat.dao;

import java.util.ArrayList;

import org.xfl.chat.utils.DBHelper;
import org.xfl.chatapp.ChatMessageEntity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDao {
	private DBHelper mDBHelper;
	private static String TAG = MessageDao.class.getName();
	private Context ctx;
	public MessageDao(Context ctx) {
		this.ctx = ctx;
		mDBHelper = new DBHelper(ctx);
	}
	
	public void addMessage(ChatMessageEntity msgEntity) {
		String sql = "insert into chatmsg (content_time, name, content, isleft) values(?, ?, ?, ?)";
		SQLiteDatabase database = mDBHelper.getWritableDatabase();
		if (database.isOpen()) {
			Object[] bindArgs = new Object[]{msgEntity.getDate(), msgEntity.getName(), msgEntity.getContent(), msgEntity.isLeft()==true?1:0};
			database.execSQL(sql, bindArgs);
			database.close();
		}
	}
	
	public ArrayList<ChatMessageEntity> queryAllChatMsg() {
		ChatMessageEntity chatMsg = new ChatMessageEntity();
		ArrayList<ChatMessageEntity> chatMsgs = new ArrayList<ChatMessageEntity>();
		SQLiteDatabase database = mDBHelper.getReadableDatabase();
		if (!database.isOpen()) {
			return chatMsgs;
		}
		String sql = "select msgid, name, content_time, content, isleft from chatmsg";
		String[] selectionArgs = null;
		Cursor cursor = database.rawQuery(sql, selectionArgs);
		while (cursor.moveToNext()) {
			int idIdx = cursor.getColumnIndex("msgid");
			int nameIdx = cursor.getColumnIndex("name");
			int contentTimeIdx = cursor.getColumnIndex("content_time");
			int contentIdx = cursor.getColumnIndex("content");
			int leftIdx = cursor.getColumnIndex("isleft");
			chatMsg.setContent(cursor.getString(contentIdx));
			chatMsg.setName(cursor.getString(nameIdx));
			chatMsg.setDate(cursor.getString(contentTimeIdx));
			chatMsg.setLeft(cursor.getInt(leftIdx)==0?true:false);
			chatMsgs.add(chatMsg);
		}
		database.close();
		return chatMsgs;
	}
}
