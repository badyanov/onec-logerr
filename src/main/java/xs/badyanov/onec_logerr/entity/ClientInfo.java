package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ClientInfo {

    @Column(name = "client_platform_type")
    private String platformType;

    @Column(name = "client_app_version")
    private String appVersion;

    @Column(name = "client_app_name")
    private String appName;

    @Embedded
    private SystemInfo systemInfo;

    // Конструкторы
    public ClientInfo() {}

    // Геттеры и сеттеры
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
}
