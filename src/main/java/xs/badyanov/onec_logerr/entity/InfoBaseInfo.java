package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class InfoBaseInfo {

    @Column(name = "infobase_locale_code")
    private String localeCode;

    // Конструкторы
    public InfoBaseInfo() {}

    // Геттеры и сеттеры
    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}
