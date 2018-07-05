/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pltfhs.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Client 1
 */
@Entity
@Table(name = "system_roles")
@NamedQueries({
    @NamedQuery(name = "SystemRoles.findAll", query = "SELECT s FROM SystemRoles s")})
public class SystemRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Short roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "role_name")
    private String roleName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_type_id")
    private short userTypeId;
    @ManyToMany(mappedBy = "systemRolesSet", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Users> usersSet;

    public SystemRoles() {
    }

    public SystemRoles(Short roleId) {
        this.roleId = roleId;
    }

    public SystemRoles(Short roleId, String roleName, short userTypeId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.userTypeId = userTypeId;
    }

    public Short getRoleId() {
        return roleId;
    }

    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public short getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(short userTypeId) {
        this.userTypeId = userTypeId;
    }

    public List<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(List<Users> usersSet) {
        this.usersSet = usersSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemRoles)) {
            return false;
        }
        SystemRoles other = (SystemRoles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pltfhs.car.entity.SystemRoles[ roleId=" + roleId + " ]";
    }

}
