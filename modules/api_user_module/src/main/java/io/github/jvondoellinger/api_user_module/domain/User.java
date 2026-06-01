package io.github.jvondoellinger.api_user_module.domain;

import io.github.jvondoellinger.rising_helpdesk.shared.domain.AggregateRoot;
import io.github.jvondoellinger.rising_helpdesk.shared.domain.DomainEntity;
import io.github.jvondoellinger.rising_helpdesk.shared.domain.DomainRule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User
	   extends DomainEntity<User>
	   implements AggregateRoot {
	private String nickname;
	private String email;
	private String password;

	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public User(List<DomainRule<User>> rules,
			  String nickname,
			  String email,
			  String password,
			  LocalDateTime createdAt,
			  LocalDateTime updatedAt) {
		super(rules);
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public User(String nickname,
			  String email,
			  String password) {
		super(new ArrayList<>());
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
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
