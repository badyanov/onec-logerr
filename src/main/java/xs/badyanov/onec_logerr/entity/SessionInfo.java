package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "session_info")
public class SessionInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "data_separation")
    private String dataSeparation;
    
    @Column(name = "platform_interface_language_code")
    private String platformInterfaceLanguageCode;
    
    @Column(name = "configuration_interface_language_code")
    private String configurationInterfaceLanguageCode;
    
    @Column(name = "locale_code")
    private String localeCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public SessionInfo() {}

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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getDataSeparation() {
        return dataSeparation;
    }
    
    public void setDataSeparation(String dataSeparation) {
        this.dataSeparation = dataSeparation;
    }
    
    public String getPlatformInterfaceLanguageCode() {
        return platformInterfaceLanguageCode;
    }
    
    public void setPlatformInterfaceLanguageCode(String platformInterfaceLanguageCode) {
        this.platformInterfaceLanguageCode = platformInterfaceLanguageCode;
    }
    
    public String getConfigurationInterfaceLanguageCode() {
        return configurationInterfaceLanguageCode;
    }
    
    public void setConfigurationInterfaceLanguageCode(String configurationInterfaceLanguageCode) {
        this.configurationInterfaceLanguageCode = configurationInterfaceLanguageCode;
    }
    
    public String getLocaleCode() {
        return localeCode;
    }
    
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
