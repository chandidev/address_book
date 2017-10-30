package au.com.chandidev.addressbook.repository;

import au.com.chandidev.addressbook.model.Address;
import au.com.chandidev.addressbook.model.AddressBook;

/**
 * Created by chandimajanakantha on 29/10/17.
 */
public class AddressFixture {

    public static AddressBook newAddressBook(String name){
        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);
        return addressBook;
    }

    public static Address newAddress(String name, String mobileNumber, String homeNumber, String officeNumber, AddressBook addressBook) {
        Address address = new Address();
        address.setAddressBook(addressBook);
        address.setHomePhone(homeNumber);
        address.setMobilePhone(mobileNumber);
        address.setOfficePhone(officeNumber);
        address.setName(name);

        return address;
    }

}
