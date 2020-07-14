
package com.streamit.streamitdemo.model.entity;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private String username;
    private Set<Playlist> playlists;
    private Set<Song> songs;
    private Set<Show> shows;
    private Set<User> followers;
    private Set<UserRole> roles;
    private List<Message> messagesReceived;
    private List<Message> messagesSent;


    public User() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Email(message = "Enter valid email address!")
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @Column(name = "password", nullable = false)
    @Length(min = 6, message = "Password length must be at least six characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @Column(name = "username", nullable = false)
    @Length(min = 2, message = "Username length must be at least two characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String displayName) {
        this.username = displayName;
    }

    @OneToMany(mappedBy = "user")
    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    @ManyToMany(mappedBy = "users")
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @ManyToMany(mappedBy = "users")
    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

    @OneToMany
    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
    @OneToMany(mappedBy = "receiver")
    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }
    @OneToMany(mappedBy = "sender")
    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    @Override
    @Transient
    public Collection<UserRole> getAuthorities() {
        return this.roles;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<UserRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Transient
    @Override
    public boolean isEnabled() {
        return false;
    }


}