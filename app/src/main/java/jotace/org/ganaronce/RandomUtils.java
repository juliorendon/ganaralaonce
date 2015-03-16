package jotace.org.ganaronce;

import java.security.SecureRandom;

public class RandomUtils {
	
	public static int generateRandomDigit() {
		SecureRandom randomGenerator = new SecureRandom();
		return randomGenerator.nextInt(10);
	}

}