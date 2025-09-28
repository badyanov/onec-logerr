package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "system_info")
public class SystemInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "os_version")
    private String osVersion;
    
    @Column(name = "full_ram")
    private Long fullRAM;
    
    @Column(name = "free_ram")
    private Long freeRAM;
    
    @Column(name = "processor")
    private String processor;
    
    @Column(name = "useragent")
    private String useragent;

    @Column(name = "client_id")
    private String clientID;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public SystemInfo() {}

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
    
    public String getOsVersion() {
        return osVersion;
    }
    
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
    
    public Long getFullRAM() {
        return fullRAM;
    }
    
    public void setFullRAM(Long fullRAM) {
        this.fullRAM = fullRAM;
    }
    
    public Long getFreeRAM() {
        return freeRAM;
    }
    
    public void setFreeRAM(Long freeRAM) {
        this.freeRAM = freeRAM;
    }
    
    public String getProcessor() {
        return processor;
    }
    
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public String getUseragent() {
        return useragent;
    }
    
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
