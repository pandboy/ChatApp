package org.xfl.chatapp;

public class ChatMessageEntity {
	private String name;
	private String date;
	private String content;
	private int layoutId;
	private boolean isLeft;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}
	public boolean isLeft() {
		return isLeft;
	}
	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
	public ChatMessageEntity(String name, String date, String content,
			int layoutId, boolean isLeft) {
		super();
		this.name = name;
		this.date = date;
		this.content = content;
		this.layoutId = layoutId;
		this.isLeft = isLeft;
	}
	public ChatMessageEntity() {
		super();
	}
	
}
