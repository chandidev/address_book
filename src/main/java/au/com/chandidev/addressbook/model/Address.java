package au.com.chandidev.addressbook.model;

import javax.persistence.*;

/**
 * Created by chandimajanakantha on 25/10/17.
 */
@Entity
@Table(name = "table_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private AddressBook addressBook;

    @Column
    private String name;

    @Column(name="home_phone")
    private String homePhone;

    @Column(name="mobile_phone")
    private String mobilePhone;

    @Column(name="office_phone")
    private String officePhone;

    public int getId() {
        return id;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public String getName() {
        return name;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }


    public boolean equals(Object obj) {
        Address address = (Address) obj;

        return obj != null
                && isSameValue(this.getName(), (address).getName())
                && isSameValue(this.getMobilePhone(), address.getMobilePhone())
                && isSameValue(this.getHomePhone(), address.getHomePhone())
                && isSameValue(this.getOfficePhone(), address.getOfficePhone());
    }

    private boolean isSameValue(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || (obj1.equals(obj2));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
