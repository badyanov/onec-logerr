package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "onec_errors")
public class OneCError {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "error_id", unique = true, nullable = false)
    private String errorId;
    
    @Column(name = "time", nullable = false)
    private Instant time;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_info_id")
    private SessionInfo sessionInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "info_base_info_id")
    private InfoBaseInfo infoBaseInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "server_info_id")
    private ServerInfo serverInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "config_info_id")
    private ConfigInfo configInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "error_info_id")
    private ErrorInfo errorInfo;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "screenshot_file_id")
    private BinaryFile screenshot;
    
    @Column(name = "additional_data", columnDefinition = "TEXT")
    private String additionalData;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "onec_error_additional_files",
               joinColumns = @JoinColumn(name = "onec_error_id"),
               inverseJoinColumns = @JoinColumn(name = "binary_file_id"))
    private List<BinaryFile> additionalFiles;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dump_id")
    private Dump dump;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Конструкторы
    public OneCError() {}

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

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public InfoBaseInfo getInfoBaseInfo() {
        return infoBaseInfo;
    }

    public void setInfoBaseInfo(InfoBaseInfo infoBaseInfo) {
        this.infoBaseInfo = infoBaseInfo;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public BinaryFile getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(BinaryFile screenshot) {
        this.screenshot = screenshot;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public List<BinaryFile> getAdditionalFiles() {
        return additionalFiles;
    }

    public void setAdditionalFiles(List<BinaryFile> additionalFiles) {
        this.additionalFiles = additionalFiles;
    }

    public Dump getDump() {
        return dump;
    }

    public void setDump(Dump dump) {
        this.dump = dump;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
