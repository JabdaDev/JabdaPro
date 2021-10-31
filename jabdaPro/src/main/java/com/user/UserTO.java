package com.user;

import lombok.Data;

@Data
public class UserTO {
	private String seq;
	private String nickname;
	private String email;
	private String password;
	private String date;
	private String rank;
}
