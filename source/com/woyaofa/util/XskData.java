
package com.woyaofa.util;

import java.io.UnsupportedEncodingException;

public class XskData {

	/**
	 * 加密
	 * 
	 * @param strDataA
	 * @return
	 */
	public static String encode (String strPlainDataA) {

		String strCipher = "";
		strCipher = XskData.encodePSB(strPlainDataA);
		return strCipher;
	}

	/**
	 * 解密
	 * 
	 * @param strDataA
	 * @return
	 */
	public static String decode (String strCipherA) {

		String strPlain = "";
		strPlain = XskData.decodePSB(strCipherA);
		return strPlain;
	}

	private static String encodePSB (String strPlainDataA) {

		String strBase64Ret = "";
		byte[] byPlainData = null;
		try {
			byPlainData = strPlainDataA.getBytes("UTF-8");
			byte[] byCipherData = XskSimpleSymmetryCipher.encryption(
					byPlainData, XskSimpleSymmetryCipher.KEY.getBytes());
			strBase64Ret = XskBase64.encode2(byCipherData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strBase64Ret;
	}

	private static String decodePSB (String strBase64DataA) {

		String strPlainRet = "";
		byte[] byBase64Data = null;
		try {
			byBase64Data = strBase64DataA.getBytes("UTF-8");
			byte[] byCipherData = XskBase64.decode2(byBase64Data);
			byte[] byData = XskSimpleSymmetryCipher.encryption(byCipherData,
					XskSimpleSymmetryCipher.KEY.getBytes());
			strPlainRet = new String(byData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return strPlainRet;
	}

	public static void main (String[] args) {

		String string = "123";
		System.out.println("加密前：" + string);
		String encode = XskData.encode(string);
		System.out.println("加密后：" + encode);
		String decode = XskData.decode(encode);
		System.out.println("解密后：" + decode);
	}
}
