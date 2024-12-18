package ma.sse.eas.capstoneproject.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    private static final String SIGNING_KEY = "Mq3vpOyK2wuhz_sMLwrSNnO8BJtnFzk9s0uooBmWwY1CZtKusIrhRA1ImhhaoakdcSEe4NS053-rXh-dYzmxOQ";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    public static String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRATION_TIME))
                .claim("roles", authorities)
                .signWith(Keys.hmacShaKeyFor(SIGNING_KEY.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public static Authentication parse(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth == null || !auth.startsWith("Bearer ")) {
            return null;
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY.getBytes())
                .build()
                .parseClaimsJws(auth.replace("Bearer ", ""))
                .getBody();

        String username = claims.getSubject();
        List<SimpleGrantedAuthority> authorities = ((List<?>) claims.get("roles")).stream()
                .map(role -> new SimpleGrantedAuthority((String) role))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
