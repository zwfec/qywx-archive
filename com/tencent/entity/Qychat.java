package com.tencent.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Qychat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String seq;

	private String msgid;

	private String publickey_ver;

	/**加密RSA秘钥*/
	private String encrypt_random_key;

	/**加密消息*/
	private String encrypt_chat_msg;
}
