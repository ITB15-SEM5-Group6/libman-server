package at.fhv.itb.sem5.team6.libman.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("hoechst").password("buch").roles("BÜCHEREI").and()
                .withUser("bregenz").password("buch").roles("BÜCHEREI").and()
                .withUser("kennelbach").password("buch").roles("BÜCHEREI");
                /*
                .ldapAuthentication()
                .userDnPatterns("uid={0},o=fhv.at")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://openldap.fhv.at")
                .and()
                .passwordCompare()
                .passwordAttribute("userPassword");
                */
    }
}
