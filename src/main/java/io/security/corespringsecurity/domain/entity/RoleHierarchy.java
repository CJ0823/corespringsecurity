package io.security.corespringsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_hierarchy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleHierarchy {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "child_name")
    private String childName;


    @Column(name = "parent_name")
    private String parentName;

}