package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "system_error_info")
public class SystemErrorInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_stack", columnDefinition = "TEXT")
    private String clientStack;
    
    @Column(name = "client_stack_hash")
    private String clientStackHash;
    
    @Column(name = "server_stack", columnDefinition = "TEXT")
    private String serverStack;
    
    @Column(name = "server_stack_hash")
    private String serverStackHash;
    
    @Column(name = "system_crash")
    private Boolean systemCrash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public SystemErrorInfo() {}

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
    
    public String getClientStack() {
        return clientStack;
    }
    
    public void setClientStack(String clientStack) {
        this.clientStack = clientStack;
    }
    
    public String getClientStackHash() {
        return clientStackHash;
    }
    
    public void setClientStackHash(String clientStackHash) {
        this.clientStackHash = clientStackHash;
    }
    
    public String getServerStack() {
        return serverStack;
    }
    
    public void setServerStack(String serverStack) {
        this.serverStack = serverStack;
    }
    
    public String getServerStackHash() {
        return serverStackHash;
    }
    
    public void setServerStackHash(String serverStackHash) {
        this.serverStackHash = serverStackHash;
    }
    
    public Boolean getSystemCrash() {
        return systemCrash;
    }
    
    public void setSystemCrash(Boolean systemCrash) {
        this.systemCrash = systemCrash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
