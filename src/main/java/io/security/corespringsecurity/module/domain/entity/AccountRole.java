package io.security.corespringsecurity.module.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}