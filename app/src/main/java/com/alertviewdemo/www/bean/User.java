package com.alertviewdemo.www.bean;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nick;//
	private int headIcon;//
	private int group;

	public User( String nick, int headIcon,
			int group) {
		this.nick = nick;
		this.headIcon = headIcon;
		this.group = group;
	}

	public User() {

	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(int headIcon) {
		this.headIcon = headIcon;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
}
