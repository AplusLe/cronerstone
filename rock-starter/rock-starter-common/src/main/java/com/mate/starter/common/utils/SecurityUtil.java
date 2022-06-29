package com.mate.starter.common.utils;

import com.mate.starter.common.constant.Oauth2Constant;
import com.mate.starter.common.constant.pool.StringPool;
import com.mate.starter.common.entity.LoginUser;
import com.mate.starter.common.enums.MateExceptionEnum;
import com.mate.starter.common.exception.TokenException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * token安全检测工具类
 *
 * @author pangu
 */
@Slf4j
public class SecurityUtil {


    public static String getHeaderToken(HttpServletRequest request) {
        return getHeaderToken(request, Oauth2Constant.HEADER_TOKEN);
    }

    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getHeaderToken(HttpServletRequest request, String tokenName) {
        return request.getHeader(tokenName);
    }

    public static String getToken(HttpServletRequest request) {
        return getToken(request, Oauth2Constant.HEADER_TOKEN);
    }

    /**
     * 从HttpServletRequest里获取token
     *
     * @param request HttpServletRequest
     * @return token
     */
    public static String getToken(HttpServletRequest request, String tokenName) {
        String headerToken = getHeaderToken(request, tokenName);

        if (!StringUtils.hasText(headerToken)) {
            throw new TokenException(MateExceptionEnum.TOKEN_DOES_NOT_EXIST);
        }
        return StringUtils.hasText(headerToken) ? TokenUtil.getToken(headerToken) : StringPool.EMPTY;
    }

    /**
     * 从Token解析获取Claims对象
     *
     * @param token Mate-Auth获取的token
     * @return Claims
     */
    public static Claims getClaims(String token, String signKey) {
        Claims claims = null;
        if (StringUtils.hasText(token)) {
            try {
                claims = TokenUtil.getClaims(token, signKey);
            } catch (Exception e) {
                throw new TokenException(MateExceptionEnum.TOKEN_HAS_EXPIRED);
            }
        }
        return claims;
    }

    /**
     * 从HttpServletRequest获取LoginUser信息
     *
     * @param request HttpServletRequest
     * @return LoginUser
     */
    public static LoginUser getUsername(HttpServletRequest request) {
        String token = getToken(request, Oauth2Constant.HEADER_TOKEN);
        Claims claims = getClaims(token, Oauth2Constant.SIGN_KEY);
        // 然后根据token获取用户登录信息
        LoginUser loginUser = new LoginUser();
        loginUser.setAccount(String.valueOf(claims.get(Oauth2Constant.ADD_USER_NAME)));
        loginUser.setId(ObjectUtil.toLong(claims.get(Oauth2Constant.ADD_USER_ID)));
        loginUser.setType(ObjectUtil.toInt(claims.get(Oauth2Constant.ADD_TYPE)));
        loginUser.setName(String.valueOf(claims.get(Oauth2Constant.ADD_NAME)));
        return loginUser;
    }
}
