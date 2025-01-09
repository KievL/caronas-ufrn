package br.ufrn.umbrella.caronasufrn.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufrn.umbrella.caronasufrn.models.enums.UserRoles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	private String email;
	
	@Column(name="password_hash")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String passwordHash;
	
	private Float rating;

	private String course;
	private String car;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="rides_provided")
	private Integer ridesProvided;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "passengers")
	private List<Ride> ridesAsPassenger;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "requesters")
	private List<Ride> ridesRequested;

	private String enrollmentNumber;
	
	@Enumerated(EnumType.STRING)
	private UserRoles role;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return null;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}
		
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired(){
		return UserDetails.super.isAccountNonExpired();
	}

	@JsonIgnore
	@Override
	public boolean isEnabled(){
		return UserDetails.super.isEnabled();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked(){
		return UserDetails.super.isAccountNonLocked();
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired(){
		return UserDetails.super.isCredentialsNonExpired();
	}
}
