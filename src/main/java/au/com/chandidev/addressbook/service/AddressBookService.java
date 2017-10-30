package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;

import java.util.*;

/**
 * Created by chandimajanakantha on 28/10/17.
 */
public interface AddressBookService {

    AddressBookDTO saveAddressBook(AddressBookDTO addressBookDTO);

    AddressDTO saveAddress(AddressDTO addressDTO);

    List<AddressDTO> getAddresses(int addressBookId);

    void deleteAddress(int addressId);

    List<AddressDTO> getAddresses();

    List<AddressBookDTO> getAddressBooks();
}
