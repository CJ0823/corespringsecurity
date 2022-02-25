package io.security.corespringsecurity.module.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role_hierarchy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleHierarchy {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name")
    private RoleHierarchy parentName;

    @OneToMany(mappedBy = "parentName", fetch = FetchType.LAZY)
    private List<RoleHierarchy> roleHierarchy = new ArrayList<>();
}