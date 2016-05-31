
package com.woyaofa.util;

import java.util.Random;

public class Key {

	protected static char[] seed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	public static String createKey (int len) {

		StringBuilder key = new StringBuilder();
		Random rand = new Random();

		for (int i = 0; i < len; i++) {
			int index = rand.nextInt(seed.length);
			char c = seed[index];
			key.append(c);
		}

		return key.toString();
	}
}
