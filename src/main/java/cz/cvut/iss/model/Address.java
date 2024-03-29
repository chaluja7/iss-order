package cz.cvut.iss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Cloneable<Address> {

    @XmlElement(required = true)
    private String firstName;

    @XmlElement(required = true)
    private String lastName;

    @XmlElement(required = true)
    private String street;

    @XmlElement(required = true)
    private String city;

    @XmlElement(required = true)
    private String zipCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isValid() {
        return firstName != null && lastName != null && street != null && city != null && zipCode != null
            && firstName.length() > 0 && lastName.length() > 0 && street.length() > 0 && city.length() > 0 && zipCode.length() > 0;
    }

    @Override
    public Address getClone() {
        Address address = new Address();
        address.setCity(city);
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setStreet(street);
        address.setZipCode(zipCode);

        return address;
    }
}
