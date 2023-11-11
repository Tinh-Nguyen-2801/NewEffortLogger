package helper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneralHelper {
	/**
	 * Get the byte array of a string input
	 * 
	 * @param input string
	 * @return byte array of the string
	 */
	private byte[] getSHA(String input) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * MessageDigest instance for hashing using SHA256
	 * 
	 * @param a string
	 * @return byte array of the string
	 */
	private String toHexString(byte[] hash) {
		// Convert byte array of hash into digest
		BigInteger number = new BigInteger(1, hash);

		// Convert the digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	/**
	 * Process a string
	 * 
	 * @param input
	 * @return who knows?
	 */
	public String generalHelp(String input) throws NoSuchAlgorithmException {
		return toHexString(getSHA(input));
	}
}
