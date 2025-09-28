package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "server_info")
public class ServerInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "platform_type")
    private String platformType;
    
    @Column(name = "app_version")
    private String appVersion;
    
    @Column(name = "dbms")
    private String dbms;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public ServerInfo() {}

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
    
    public String getPlatformType() {
        return platformType;
    }
    
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }
    
    public String getAppVersion() {
        return appVersion;
    }
    
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    
    public String getDbms() {
        return dbms;
    }
    
    public void setDbms(String dbms) {
        this.dbms = dbms;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
