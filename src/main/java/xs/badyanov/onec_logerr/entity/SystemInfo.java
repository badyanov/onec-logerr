package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SystemInfo {

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

    // Конструкторы
    public SystemInfo() {}

    // Геттеры и сеттеры
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
}
