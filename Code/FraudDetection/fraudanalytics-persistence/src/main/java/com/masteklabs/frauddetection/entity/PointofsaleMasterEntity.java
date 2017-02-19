package com.masteklabs.frauddetection.entity;

import java.io.Serializable;

public class PointofsaleMasterEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long posid;
	private Integer categoryid;
	private String description;
	private boolean isOnline;
	public Long getPosid() {
		return posid;
	}
	public void setPosid(Long posid) {
		this.posid = posid;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(int val) {
		if(val==0){
			isOnline=false;
		}
		else{
			isOnline=true;
		}
	}
	
	
	
}
