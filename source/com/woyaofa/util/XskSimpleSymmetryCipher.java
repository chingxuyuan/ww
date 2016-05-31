
package com.woyaofa.util;

/**
 * �򵥼����㷨
 * 
 * @author
 *
 */
public class XskSimpleSymmetryCipher {

	public static final String KEY = "\r/\"@X!SK?=akte@6Q`g$qz<)LM(blsC+PY-c4ERVO.dn*j0~N5,8r9ZvWU[7oxw;H:\t1I$FiTA>GJ%fpym&u2^D}]3{B*h#)(&%$@*}&[#!{]|<,.>?)&^(%*!@_+-=?\n\\";

	// = "~!@#$%^&*()`?<>,.;:1qaz2wsx3edc4rfv5tgb6yhnmju7,ki8.lo9;p0";

	/**
	 * ���������
	 * 
	 * @param data
	 * @param key
	 */
	public static byte[] encryption (byte[] data, byte[] key) {

		//
		byte[] rData = new byte[data.length];

		for (int i = 0; i < data.length; ++i) {
			rData[i] = (byte) (data[i] ^ key[i % key.length]);
		}
		return rData;
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decryption (byte[] data, byte[] key) {

		return encryption(data, key);
	}

}
