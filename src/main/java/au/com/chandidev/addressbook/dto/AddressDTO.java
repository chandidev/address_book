package au.com.chandidev.addressbook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created by chandimajanakantha on 25/10/17.
 */
public class AddressDTO {

    private int id;

    private int addressBookId;

    @Length(min = 2, max = 10, message = "The field must be at least 2 characters and less than 100 characters")
    private String name;

    @Pattern(message="invalid phone number" , regexp="^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$")
    private String homePhone;

    @Pattern(message="invalid phone number" , regexp="^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$")
    @NotEmpty
    private String mobilePhone;

    @Pattern(message="invalid phone number" , regexp="^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$")
    private String officePhone;

    public int getId() {
        return id;
    }

    public int getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(int addressBookId) {
        this.addressBookId = addressBookId;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("home_phone")
    public String getHomePhone() {
        return homePhone;
    }

    @JsonProperty("mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    @JsonProperty("office_phone")
    public String getOfficePhone() {
        return officePhone;
    }

    public void setId(int id) {
        this.id = id;
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
        AddressDTO address = (AddressDTO) obj;

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
