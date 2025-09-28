package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemErrorInfo {

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

    // Конструкторы
    public SystemErrorInfo() {}

    // Геттеры и сеттеры
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
}
