package se.consys.silverkissen.utilities;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TokenIssuer {
	public static final long EXPIRE_MINS = 60L;
	
	public String issueToken(String username) {
		LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(EXPIRE_MINS);
		System.out.println("expiryPeriod: " +expiryPeriod);
		Date expirationDateTime = Date.from(expiryPeriod.atZone(ZoneId.systemDefault()).toInstant());
		System.out.println("expirationDateTime: " + expirationDateTime);
		
		Key key = new SecretKeySpec("secret".getBytes(), "DES");
		System.out.println("key: " + key);
		
		String compactJws = Jwts.builder()
				.setSubject(username)
				.signWith(SignatureAlgorithm.HS256, key)
				.setIssuedAt(new Date())
				.setExpiration(expirationDateTime)
				.compact();
		System.out.println("compactJws: " + compactJws);
		return compactJws;
	}
}
