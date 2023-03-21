


<h1 align="center">
  <br>
<a  href="https://spring.io/"  target="_blank"  rel="noreferrer"> <img  src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg"  alt="spring"  width="180"  height="180"/> </a>
  <br>
  Spring Security [Learning]
  <br>
  <br>
</h1>

<p align="center">
  <a href="#project-description">Project Description</a> |
  <a href="#tech-stack-and-libraries">Tech Stack and Libraries</a> |
  <a href="#how-it-works">How it Works</a> |
  <a href="#code-examples">Code Examples</a> |
  <a href="#acknowledgements">Acknowledgements</a>
</p>



<div id="project-description"></div>

## 🚀 Project Description
This project is based on the "Securing Spring" chapter of the "Spring in Action" book. The goal of this project is to learn how to secure a Spring application against various attacks, such as CSRF attacks, and how to define custom user storage and customize the login page.

<div id="tech-stack-and-libraries"></div>

## 🛠️ Tech Stack and Libraries
This project was built using the following tech stack and libraries:
- Java Spring
- Spring Security
- Spring Boot

<div id="how-it-works"></div>

## ⚙️ How it Works

The project contains a basic Spring application that has been configured with Spring Security. The application allows users to log in and view a secure page. The project includes custom user storage configuration and a custom login page.

<div id="code-examples"></div>

## 💻 Code Examples
1.Example of Spring Security Configuration :
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login**", "/css/**").permitAll()
                .antMatchers("/secure/**").hasRole("USER")
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/secure/home")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
2.Example of User Storage implementation :
```java
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}


```

<div id="acknowledgements"></div>

## 📚 Acknowledgements 
This project was created with the help of the book **"Spring in Action"** by **Craig Walls** and **Ryan Breidenbach**. Many of the concepts and techniques used in this project were learned from this valuable resource.

