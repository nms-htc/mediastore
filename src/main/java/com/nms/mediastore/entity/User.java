/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByUP", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password"),
    @NamedQuery(name = "User.findByU", query = "SELECT u FROM User u WHERE u.username = :username")
})
@Table(name = "MS_USER")
@XmlRootElement
public class User extends BaseEntity {

    private static final long serialVersionUID = 6010197496803589311L;

    @NotNull
    @Size(max = 75)
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Column(name = "FULLNAME")
    @Size(max = 100)
    private String fullname;

    @NotNull
    @Size(max = 150)
    @Column(name = "PASSWORD")
    private String password;

    @Size(max = 150)
    @Column(name = "SALT")
    private String salt;

    @NotNull
    @Size(max = 200)
    @Column(name = "EMAIL", unique = true)
    private String email;

    @ElementCollection(targetClass = Group.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "MS_USERGROUP", joinColumns = {
        @JoinColumn(name = "USERID")})
    @Column(name = "GROUPNAME")
    private Set<Group> groups;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
