package au.com.chandidev.addressbook.dto;


import javax.validation.constraints.NotEmpty;

/**
 * Created by chandimajanakantha on 25/10/17.
 */

public class AddressBookDTO {

    private int id;

    @NotEmpty
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        AddressBookDTO addressBookDTO = (AddressBookDTO) obj;
        return isSameValue(this.name, addressBookDTO.getName());

    }

    private boolean isSameValue(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || (obj1.equals(obj2));
    }

}
