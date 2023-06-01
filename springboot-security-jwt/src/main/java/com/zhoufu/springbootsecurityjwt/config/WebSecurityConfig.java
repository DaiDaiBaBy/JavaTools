package com.zhoufu.springbootsecurityjwt.config;

import com.zhoufu.springbootsecurityjwt.filter.JwtAuthenticationTokenFilter;
import com.zhoufu.springbootsecurityjwt.security.MyAuthExcetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;

/**
 * @author zf
 * @CreateTime 2020/4/16 22:07
 * @description
 *
 * Security配置类
 *
 * configureAuthentication() 配置身份验证信息
 * passwordEncoder() 配置密码的加密使用的算法
 * authenticationTokenFilterBean() 构建自定义jwt权限认证过滤器
 * configure() 配置资源过滤和注入自定义jwt过滤器
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthExcetion.MyAuthenticationEntry myAuthenticationEntry;

    @Autowired
    private MyAuthExcetion.MyAccessDenied myAccessDenied;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private IgnoreSecurityPropetties ignoreSecurityPropetties;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * configureAuthentication() 配置身份验证信息
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置 UserDetailsService
                .userDetailsService(userDetailsService)
                // 使用 BCrypt 进行密码的 hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载BCrypt密码编码器
     * passwordEncoder() 配置密码的加密使用的算法
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置自定义jwt权限认证过滤器
     *
     * authenticationTokenFilterBean() 构建自定义jwt权限认证过滤器
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 设置需要授权认证的资源,不需要权认能访问的资源
     *
     * configure() 配置资源过滤和注入自定义jwt过滤器
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        String[] all = new String[ignoreSecurityPropetties.getAll().size()];
        String[] get = new String[ignoreSecurityPropetties.getGet().size()];
        String[] post = new String[ignoreSecurityPropetties.getPost().size()];
        ignoreSecurityPropetties.getGet().toArray(get);
        ignoreSecurityPropetties.getPost().toArray(post);
        ignoreSecurityPropetties.getAll().toArray(all);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequest = httpSecurity.authorizeRequests();
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .cors().and() // 跨域
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntry).and()
                .exceptionHandling().accessDeniedHandler(myAccessDenied).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests();

        if (all.length > 0) {
            authorizeRequest.antMatchers(all).permitAll();
        }
        if (get.length > 0) {
            authorizeRequest.antMatchers(HttpMethod.GET, get).permitAll();
        }
        if (post.length > 0) {
            authorizeRequest.antMatchers(HttpMethod.POST, post).permitAll();
        }
        // 其它接口都要认证
        authorizeRequest.anyRequest().authenticated();
        //将token验证添加在密码验证前面
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}
