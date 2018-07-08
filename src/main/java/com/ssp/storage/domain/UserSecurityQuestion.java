package com.ssp.storage.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

@Entity
@Table(name = "user_security_question", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "question" }))
public class UserSecurityQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.user_security_question_seq")
	@SequenceGenerator(sequenceName = "public.user_security_question_seq", allocationSize = 1, name = "public.user_security_question_seq")
	@JsonIgnore
	private Long id;
	private String question;
	private String answer;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSecurityQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSecurityQuestion(Long id, String question, String answer, User user) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.user = user;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
