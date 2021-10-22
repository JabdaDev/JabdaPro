package com.user;

import lombok.Data;

@Data
public class UserTO {
	private String seq;
	private String email;
	private String password;
	private String phone;
	private String nickname;
	private String interest;
	private String date;
	private String role;
}
