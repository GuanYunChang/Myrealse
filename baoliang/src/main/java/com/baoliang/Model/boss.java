package com.baoliang.Model;
// Generated 2018-4-6 21:51:29 by Hibernate Tools 5.2.8.Final

/**
 * Boss generated by hbm2java
 */
public class boss implements java.io.Serializable {

	private String bossphone;
	private String bosspass;
	private String bossname;
	private Integer verify;
	private String carda;
	private String cardb;
	private String description;

	public boss() {
	}

	public boss(String bossphone, String carda, String cardb, String description) {
		this.bossphone = bossphone;
		this.carda = carda;
		this.cardb = cardb;
		this.description = description;
	}

	public boss(String bossphone, String bosspass, String bossname, Integer verify, String carda, String cardb,
			String description) {
		this.bossphone = bossphone;
		this.bosspass = bosspass;
		this.bossname = bossname;
		this.verify = verify;
		this.carda = carda;
		this.cardb = cardb;
		this.description = description;
	}

	public String getBossphone() {
		return this.bossphone;
	}

	public void setBossphone(String bossphone) {
		this.bossphone = bossphone;
	}

	public String getBosspass() {
		return this.bosspass;
	}

	public void setBosspass(String bosspass) {
		this.bosspass = bosspass;
	}

	public String getBossname() {
		return this.bossname;
	}

	public void setBossname(String bossname) {
		this.bossname = bossname;
	}

	public Integer getVerify() {
		return this.verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	public String getCarda() {
		return this.carda;
	}

	public void setCarda(String carda) {
		this.carda = carda;
	}

	public String getCardb() {
		return this.cardb;
	}

	public void setCardb(String cardb) {
		this.cardb = cardb;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
