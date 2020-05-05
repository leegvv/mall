package net.arver.mall.security.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * Created by macro on 2018/4/26.
 */
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(final String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (final Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * @param token token
     * @param userDetails 数据库中查询出来的用户信息
     * @return
     */
    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        final HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshHeadToken(final String oldToken) {
        if (StrUtil.isEmpty(oldToken)) {
            return null;
        }
        final String token = oldToken.substring(tokenHead.length());
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        // token校验不通过
        final Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        // 如果token已经过期， 不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        // 如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, 30 * 60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token
     * @param time
     * @return
     */
    private boolean tokenRefreshJustBefore(final String token, final int time) {
        final Claims claims = getClaimsFromToken(token);
        final Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        final Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time))) {
            return true;
        }
        return false;
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(final String token) {
        final Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(final String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据负载生成token.
     * @param claims 负载
     * @return
     */
    private String generateToken(final Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    /**
     * 从token中获取jwt中的负载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(final String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        }catch (final Exception e) {
            LOGGER.info("JWT格式验证失败：{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


}
