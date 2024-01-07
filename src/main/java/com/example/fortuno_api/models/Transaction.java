package com.example.fortuno_api.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "wallet", referencedColumnName = "id")
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedAt;
}
