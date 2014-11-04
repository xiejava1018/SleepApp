package com.doit8.sleep.pojo;

public class Subject {
	private String topicId;
	private String topic;
	private String[] answers;
	private Subject nextSubject;
	private Subject previouSubject;
	
	public Subject(String topicId, String topic, String[] answers) {
		super();
		this.topicId = topicId;
		this.topic = topic;
		this.answers = answers;
	}
	
	public Subject getNextSubject() {
		return nextSubject;
	}
	public void setNextSubject(Subject nextSubject) {
		this.nextSubject = nextSubject;
	}
	public Subject getPreviouSubject() {
		return previouSubject;
	}
	public void setPreviouSubject(Subject previouSubject) {
		this.previouSubject = previouSubject;
	}

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	
	
}
