/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nms.mediastore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class User implements Serializable {

    private static final long serialVersionUID = 6010197496803589311L;

    @Id
    @TableGenerator(
            name = "userSQ",
            table = "MS_SEQUENCE",
            pkColumnName = "NAME",
            pkColumnValue = "VALUE",
            valueColumnName = "USER_SQ"
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userSQ")
    @Column(name = "USERID")
    private Long userId;

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

    @NotNull
    @Size(max = 150)
    @Column(name = "SALT")
    private String salt;

    @NotNull
    @Size(max = 200)
    @Column(name = "EMAIL")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIEDDATE")
    private Date modifiedDate;

    @ElementCollection(targetClass = Group.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "MS_USERGROUP", joinColumns = {
        @JoinColumn(name = "USERID")})
    @Column(name = "GROUPNAME")
    private Set<Group> groups;

    public User() {
        Date now = new Date();
        createDate = now;
        modifiedDate = now;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.userId, other.userId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.userId ^ (this.userId >>> 32));
        return hash;
    }

}
