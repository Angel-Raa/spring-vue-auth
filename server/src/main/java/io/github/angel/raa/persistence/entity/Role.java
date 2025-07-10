package io.github.angel.raa.persistence.entity;

public enum Role {
    ADMIN,
    USER,
    GUEST;

    public String getRoleName() {
        return this.name();
    }

}
