package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "client_info")
public class ClientInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "platform_type")
    private String platformType;
    
    @Column(name = "app_version")
    private String appVersion;
    
    @Column(name = "app_name")
    private String appName;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "system_info_id")
    private SystemInfo systemInfo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public ClientInfo() {}

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
    
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
