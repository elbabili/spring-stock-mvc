package fr.ldnr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity		//désactive le formulaire d'authentification par défaut de spring
						//et active notre stratégie de sécurité
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	DataSource dataSource;	//pointe vers la base de donnée
	
	@Override  //cette méthode précise si les users sont en base, dans un fichier, en mémoire comme ci-dessous
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//PasswordEncoder pe = passwordEncoder();
		//Il est impératif de toujours stocké en mémoire ou en base des mots de pass crypté
		//création d'utilisateurs en mémoire avec mot de passe crypté et des rôles distincts		
		//auth.inMemoryAuthentication().withUser("mohamed").password(pe.encode("12345")).roles("ADMIN","USER");	
		//auth.inMemoryAuthentication().withUser("aymene").password(pe.encode("12345")).roles("USER");
		//indique à Spring l'algo utilisé pour le cryptage des pwd
		//auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)			//Spring va ici vérifier si l'utilisateur existe ou pas, si oui il compare les pwd, si ok il enchaine
			.usersByUsernameQuery("select username as principal, password as credentials, active from T_Users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from T_Users_Roles where username=?") //chargement des rôles pour username
			.rolePrefix("ROLE_")		//ajout d'un prefix, par ex si le role est ADMIN => ROLE_ADMIN
			.passwordEncoder(passwordEncoder());	//indique l'algo utilisé pour crypter les pwd
	}
	
	@Bean	//annotation permettant à cet objet d'être inscrit dans le contexte de Spring
			//et delors peut être utilisé ailleurs dans l'appli via @Autowired
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");	//indique à Spring que nous fournissons notre page d'authentification
		//http.formLogin();	//indique à Spring de gérer l'authentification
	
		//attribution des accès aux pages en fonction des rôles
		http.authorizeRequests().antMatchers("/confirm","/porder","/order","/save","/delete","/edit","/article").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/confirm","/porder","/order").hasRole("USER");
		
		http.exceptionHandling().accessDeniedPage("/403");	//au cas ou un utilisateur tente d'accéder à une page non authorisée
	}
}
