package io.security.corespringsecurity.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "persistent_logins")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersistentLogin {
    @Id
    @Column(name = "series", nullable = false)
    private String id;

    @Column(name = "last_used")
    private Instant lastUsed;

    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String username;

}