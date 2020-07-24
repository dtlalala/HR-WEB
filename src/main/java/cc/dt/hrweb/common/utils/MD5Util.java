package cc.dt.hrweb.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author: dt_啦la啦
 * @version: 1.0.0
 * @date: 2020/3/5
 * @description: MD5工具类
 */
public class MD5Util {

	protected MD5Util(){

	}

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String password) {
		/*
		SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
		参数含意：
		source: 要散列的值（这里是明文密码）
		salt: 盐，用于与source一起散列的值，一般随机生成，用于防止暴力破解   使用 ByteSource.Util.bytes() 来计算盐值
		hashIterations: 散列的次数
		algorithmName: simpleHash是其它散列的父类（如下图），如果要用simpleHash就要告诉shiro使用哪种hash方式
		 */
		return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(password), HASH_ITERATIONS).toHex();
	}

	public static String encrypt(String username, String password) {
		return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username.toLowerCase() + password),
				HASH_ITERATIONS).toHex();
	}

	public static void main(String[] args) {
		System.out.println(encrypt("dtlalala","dt1515303832dt"));
	}

}
