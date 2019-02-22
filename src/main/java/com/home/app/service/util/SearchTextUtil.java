package com.home.app.service.util;

import java.math.BigInteger;
import java.util.Random;

public class SearchTextUtil {

	public int search(String txt) {

		int N = txt.length();
		
		if (N < M){
			return N;
		}
		
		long txtHash = hash(txt, M);

		if ((patHash == txtHash) && check(txt, 0)){
			return 0;
		}

		for (int i = M; i < N; i++) {
			
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;

			int offset = i - M + 1;
			
			if ((patHash == txtHash) && check(txt, offset)){
				return offset;
			}
		}

		return N;
	}

	public static boolean search(String source, String pattern, int lengSource) {

		int offset = _instance._searchIndex(source, pattern);

		return offset < lengSource;
	}

	public static int searchIndex(String source, String pattern) {
		return _instance._searchIndex(source, pattern);
	}

	private int _searchIndex(String source, String pattern) {

		_instance._SearchTextUtil(pattern);

		return _instance.search(source);
	}

	private SearchTextUtil() {}

	private void _SearchTextUtil(String pat) {
		
		this.pat = pat; 
		
		R = 256;
		M = pat.length();
		Q = longRandomPrime();		
		RM = 1;
		
		for (int i = 1; i <= M - 1; i++){
			RM = (R * RM) % Q;
		}
		
		patHash = hash(pat, M);
	}

	private static long longRandomPrime() {
		
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		
		return prime.longValue();
	}

	private long hash(String key, int M) {
		
		long h = 0;
		
		for (int j = 0; j < M; j++){
			h = (R * h + key.charAt(j)) % Q;
		}
			
		return h;
	}

	private boolean check(String txt, int i) {
		
		for (int j = 0; j < M; j++){
			
			if (pat.charAt(j) != txt.charAt(i + j)){
				return false;
			}
		}

		return true;
	}

	private static SearchTextUtil _instance = new SearchTextUtil();
	private String pat; // the pattern // needed only for Las Vegas
	private long patHash; // pattern hash value
	private int M; // pattern length
	private long Q; // a large prime, small enough to avoid long overflow
	private int R; // radix
	private long RM; // R^(M-1) % Q
}
