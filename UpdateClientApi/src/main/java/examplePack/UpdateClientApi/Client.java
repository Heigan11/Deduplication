package examplePack.UpdateClientApi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String fullName;
    private String passport;
    private String birthDate;
    private String clientId;
    private String  toDelete;
    private String oldClientId;

    public String getToDelete() {
        return toDelete;
    }

    public String getOldClientId() {
        return oldClientId;
    }

    public void setOldClientId(String oldClientId) {
        this.oldClientId = oldClientId;
    }

    public void setToDelete(String toDelete) {
        this.toDelete = toDelete;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client() {
    }

    public Client(String fullName, String passport, String birthDate) {
        this.fullName = fullName;
        this.passport = passport;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
