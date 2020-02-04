package com.nevercaution.demoinflearnapi.accounts;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;

    // 여러개의 enum 을 가질 수 있기 때문에 설절.
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<AccountRole> roles;
}
