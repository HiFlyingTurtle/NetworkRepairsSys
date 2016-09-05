package com.scut.wwh.sys.model;

public class Task {
	private int taskId;				//任务编号
	private String userName;		//用户姓名
	private String publishTime;		//发布时间,报修时间
	private String userAddress;		//用户地址
	private String type;			//故障类型
	private String troubelDesc;		//故障描述
	private String repairer;		//维修者
	
	private String repairTime;		//维修时间
	private String finishTime;		//完成时间
	private String state;			//状态
	private String dealWay;			//处理方法

	public Task(){
		super();
	}
	//构造方法
	public Task(String userName, String publishTime,
			String userAddress, String type, String troubelDesc,
			String repairer, String repairTime, String finishTime,  String dealWay,String state) {
		super();
		this.userName = userName;
		this.publishTime = publishTime;
		this.userAddress = userAddress;
		this.type = type;
		this.troubelDesc = troubelDesc;
		this.repairer = repairer;
		this.repairTime = repairTime;
		this.finishTime = finishTime;
		this.dealWay = dealWay;
		this.state = state;
		
	}
	
	//构造方法
		public Task(String publishTime,
				String userAddress, String type, String troubelDesc,
				String repairer, String repairTime, String finishTime,  String dealWay,String state) {
			super();
			this.publishTime = publishTime;
			this.userAddress = userAddress;
			this.type = type;
			this.troubelDesc = troubelDesc;
			this.repairer = repairer;
			this.repairTime = repairTime;
			this.finishTime = finishTime;
			this.dealWay = dealWay;
			this.state = state;
			
		}
	//属性的getset方法
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTroubelDesc() {
		return troubelDesc;
	}
	public void setTroubelDesc(String troubelDesc) {
		this.troubelDesc = troubelDesc;
	}
	public String getRepairer() {
		return repairer;
	}
	public void setRepairer(String repairer) {
		this.repairer = repairer;
	}
	public String getRepairTime() {
		return repairTime;
	}
	public void setRepairTime(String repairtime) {
		this.repairTime = repairtime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDealWay() {
		return dealWay;
	}
	public void setDealWay(String dealWay) {
		this.dealWay = dealWay;
	}
}
