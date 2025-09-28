package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "error_info")
public class ErrorInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_description", columnDefinition = "TEXT")
    private String userDescription;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "system_error_info_id")
    private SystemErrorInfo systemErrorInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_error_info_id")
    private ApplicationErrorInfo applicationErrorInfo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public ErrorInfo() {}

    // Callback-методы JPA lifecycle
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserDescription() {
        return userDescription;
    }
    
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
    
    public SystemErrorInfo getSystemErrorInfo() {
        return systemErrorInfo;
    }
    
    public void setSystemErrorInfo(SystemErrorInfo systemErrorInfo) {
        this.systemErrorInfo = systemErrorInfo;
    }
    
    public ApplicationErrorInfo getApplicationErrorInfo() {
        return applicationErrorInfo;
    }
    
    public void setApplicationErrorInfo(ApplicationErrorInfo applicationErrorInfo) {
        this.applicationErrorInfo = applicationErrorInfo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
