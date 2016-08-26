package com.scut.wwh.sys.model;

public class Message {
	private int messId;			//通知的编号
	private String title;		//通知的标题
	private String who;			//通知对象
	private String content;		//通知的内容
	private String time;		//通知发布时间
	private String infoment;	//通知人
	
	public Message() {
		super();
	}
	//有参的构造方法
	public Message(String title, String who,String content, String time, String infoment) {
		super();
		this.title = title;
		this.content = content;
		this.time = time;
		this.infoment = infoment;
		this.who= who;
	}

	public int getMessId() {
		return messId;
	}
	public void setMessId(int messId) {
		this.messId = messId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInfoment() {
		return infoment;
	}
	public void setInfoment(String infoment) {
		this.infoment = infoment;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
}
