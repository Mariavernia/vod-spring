package es.upm.miw.mariavernia.vod.vodspring.domain.model;

public enum Role {
    ADMIN, STUDENT, PROFESSOR;
    public static final String PREFIX = "ROLE_";

    public static Role of(String withPrefix) {
        return Role.valueOf(withPrefix.replace(Role.PREFIX, ""));
    }

    public String withPrefix() {
        return PREFIX + this.toString();
    }
}
