package io.security.corespringsecurity.module.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_resources")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}