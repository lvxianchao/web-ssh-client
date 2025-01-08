package com.lvxianchao.api.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthSaTokenConfig implements WebMvcConfigurer {

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        // token 名称（同时也是 cookie 名称）
        config.setTokenName("Authorization");
        // token 有效期（单位：秒），默认30天，-1代表永不过期
        config.setTimeout(30 * 24 * 60 * 60);
        // token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结
        config.setActiveTimeout(-1);
        // 是否允许同一账号多地同时登录（为 true 时允许一起登录，为 false 时新登录挤掉旧登录）
        config.setIsConcurrent(false);
        // 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token，为 false 时每次登录新建一个 token）
        config.setIsShare(true);
        // token 风格
        config.setTokenStyle("simple-uuid");
        // 是否输出操作日志
        config.setIsLog(false);
        // token 前缀
        config.setTokenPrefix("Bearer");
        return config;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa -Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
            .addPathPatterns("/**")
            .excludePathPatterns(
                // 获取用户身信息
                "/auth/userinfo",
                // 用户注册
                "/auth/register",
                // 用户登录
                "/auth/login"
            );
    }

}
