package io.github.jvondoellinger.api_user_module.domain;

import io.github.jvondoellinger.rising_helpdesk.kernel.domain.AggregateRoot;
import io.github.jvondoellinger.rising_helpdesk.kernel.domain.DomainEntity;
import io.github.jvondoellinger.rising_helpdesk.kernel.domain.DomainRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User
	   extends DomainEntity<User>
	   implements AggregateRoot {
	private String nickname;
	private String email;
	private String password;
	private final Date createdAt;
	private final Date updatedAt;

	public User(List<DomainRule<User>> rules,
			  String nickname,
			  String email,
			  String password,
			  Date createdAt,
			  Date updatedAt) {
		super(rules);
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public User(String nickname,
			  String email,
			  String password,
			  Date createdAt,
			  Date updatedAt) {
		super(new ArrayList<>());
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void changeNickname(String nickname) {
		if (nickname == null || nickname.isBlank())
			throw new IllegalArgumentException("Nickname can't be blank");
		this.nickname = nickname;
	}
	public void changeEmail(String email) {
		if (email == null || email.isBlank())
			throw new IllegalArgumentException("Email can't be blank");
		this.email = email;
	}
	public void changePassword(String password) {
		if (password == null || password.isBlank())
			throw new IllegalArgumentException("Password can't be blank");
		this.password = password;
	}
}
