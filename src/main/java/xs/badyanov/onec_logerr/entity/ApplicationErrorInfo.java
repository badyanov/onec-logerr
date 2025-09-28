package xs.badyanov.onec_logerr.entity;

import jakarta.persistence.*;
import java.util.List;

@Embeddable
public class ApplicationErrorInfo {

    @ElementCollection
    @CollectionTable(name = "application_error_descriptions",
                     joinColumns = @JoinColumn(name = "onec_error_id"))
    private List<ErrorDescription> errors;

    @ElementCollection
    @CollectionTable(name = "application_stack_descriptions",
                     joinColumns = @JoinColumn(name = "onec_error_id"))
    private List<StackDescription> stack;

    @Column(name = "stack_hash")
    private String stackHash;

    // Конструкторы
    public ApplicationErrorInfo() {}

    // Геттеры и сеттеры
    public List<ErrorDescription> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDescription> errors) {
        this.errors = errors;
    }

    public List<StackDescription> getStack() {
        return stack;
    }

    public void setStack(List<StackDescription> stack) {
        this.stack = stack;
    }

    public String getStackHash() {
        return stackHash;
    }

    public void setStackHash(String stackHash) {
        this.stackHash = stackHash;
    }
}
