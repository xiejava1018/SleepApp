package com.doit8.util.version.pojo;

public class VersionPojo {
	private String version;
	private String versionName;
	private String versionUrl;
	private String versionDesc;
	
	public VersionPojo()
	{}
	public VersionPojo(String version, String versionName, String versionUrl,
			String versionDesc) {
		super();
		this.version = version;
		this.versionName = versionName;
		this.versionUrl = versionUrl;
		this.versionDesc = versionDesc;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getVersionUrl() {
		return versionUrl;
	}
	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}
	public String getVersionDesc() {
		return versionDesc;
	}
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	
}
