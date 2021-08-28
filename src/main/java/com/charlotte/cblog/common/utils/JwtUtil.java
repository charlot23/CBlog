//package com.charlotte.cblog.common.utils;
//
//
//
//import java.util.Date;
//
//public class JwtUtil {
//
//    // 过期时间5分钟
//    private final static long EXPIRE_TIME = 5 * 60 * 1000;
//
//    /**
//     * 生成签名,5min后过期
//     * @param username 用户名
//     * @param secret 用户的密码
//     * @return 加密的token
//     */
//    public static String sign(String username, String secret) throws Exception{
//        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//        Algorithm algorithm = Algorithm.HMAC256(secret);
//        return JWT.create()
//                .withClaim("username", username)
//                .withExpiresAt(expireDate)
//                .sign(algorithm);
//    }
//
//    /**
//     * 校验token是否正确
//     * @param token 密钥
//     * @param secret 用户的密码
//     * @return 是否正确
//     */
//    public static boolean verify(String token, String username, String secret) throws Exception{
//        Algorithm algorithm = Algorithm.HMAC256(secret);
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withClaim("username", username)
//                .build();
//        DecodedJWT jwt = verifier.verify(token);
//        return true;
//    }
//
//    /**
//     * 获得token中的信息无需secret解密也能获得
//     * @return token中包含的用户名
//     */
//    public static String getUsername(String token) throws Exception{
//        DecodedJWT jwt = JWT.decode(token);
//        return jwt.getClaim("username").asString();
//    }
//}
