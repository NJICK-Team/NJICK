package com.sparta.njick.domain.user.entity;

import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
public class User extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(nullable = false, unique = true)
    private String nickname;

}
