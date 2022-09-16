package com.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    @NotEmpty
    @Size(min = 4, message = "min 4 character required" )
    private String name;

    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "min 8 character required" )
    private String password;

	private String rol;
    
	private String code;
	
    private String status;

    public User() {
    }

    public User(String name, String email, String password, String code ) { // String rol
        this.name = name;
        this.email = email;
        this.password = password;
        this.code=code;
        //this.rol=rol;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) { this.rol = rol; }
    
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getCode() { return code; }

	public void setCode(String code) { this.code = code; }

	@Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", rol='" + rol +
                '}';
    }

}
