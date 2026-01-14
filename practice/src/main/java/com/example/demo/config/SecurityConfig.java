package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/contact", "/contact/**", "/admin/signup", "/admin/signup/**", "/admin/signin").permitAll()
				.requestMatchers("/admin/**").authenticated()
				.anyRequest().permitAll()  // ログインページは誰でもアクセス可能
			)
			.formLogin((form) -> form
				.loginPage("/admin/signin") 
				.usernameParameter("email") // ログインフォームのユーザー名をemailに
				.passwordParameter("password") // ログインフォームのパスワードはそのまま
				.defaultSuccessUrl("/admin/contacts", true)
				.failureUrl("/admin/signin?error")
				.permitAll()
			)
			.logout((logout) -> logout
				.logoutUrl("/admin/logout") // ログアウト処理を実行するURL
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/admin/signin") // ログアウトしたらログイン画面に遷移
				.permitAll()
			)
			.csrf(csrf -> csrf.disable());
		
		return http.build();
	}
	
	// パスワードエンコーダーのBean定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}