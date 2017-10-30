package au.com.chandidev.addressbook.repository;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;

/**
 * Created by chandimajanakantha on 29/10/17.
 */
public class AddressDTOFixture {

    public static AddressBookDTO newAddressBook(String name){
        AddressBookDTO addressBook = new AddressBookDTO();
        addressBook.setName(name);
        return addressBook;
    }

    public static AddressDTO newAddress(String name, String mobileNumber, String homeNumber, String officeNumber, int addressBookId) {
        AddressDTO address = new AddressDTO();
        address.setAddressBookId(addressBookId);
        address.setHomePhone(homeNumber);
        address.setMobilePhone(mobileNumber);
        address.setOfficePhone(officeNumber);
        address.setName(name);

        return address;
    }

    public static AddressBookDTO validAddressBook() {
        return newAddressBook("book1");
    }

    public static AddressDTO validAddress(int addressBookId) {
        return newAddress("John Smith", "0344455555", "0344445555", "0456444222", addressBookId);
    }

}
