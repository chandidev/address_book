package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.model.Address;
import au.com.chandidev.addressbook.model.AddressBook;
import au.com.chandidev.addressbook.repository.AddressBookRepository;
import au.com.chandidev.addressbook.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by chandimajanakantha on 28/10/17.
 */
@Service
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private AddressBookConverter addressBookConverter;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    public AddressBookDTO saveAddressBook(AddressBookDTO addressBookDTO) {

        AddressBook existing = addressBookRepository.findAddressBooksByName(addressBookDTO.getName());

        if (existing != null) {
            throw new javax.validation.ValidationException("address book already exists");
        }

        AddressBook addressBook = addressBookConverter.convert(addressBookDTO);
        addressBook = addressBookRepository.save(addressBook);
        return addressBookConverter.convert(addressBook);
    }

    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = addressConverter.convert(addressDTO);
        address = addressRepository.save(address);
        return addressConverter.convert(address);
    }

    public List<AddressDTO> getAddresses(int addressBookId) {
        Iterable<Address> addresses = addressRepository.findAddressByAddressBookId(addressBookId);

        return getAddressDTOS(addresses);
    }

    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }

    public List<AddressDTO> getAddresses() {
        Iterable<Address> addresses = addressRepository.findAll();

        //filter unique addresses
        Set<Address> addressSet = new HashSet<>();
        addresses.forEach(addressSet::add);

        return getAddressDTOS(addressSet);
    }

    public List<AddressBookDTO> getAddressBooks() {
        Iterable<AddressBook> addressBooks = addressBookRepository.findAll();

        List<AddressBookDTO> addressBookList = new ArrayList<>();
        addressBooks.forEach(addressBook -> {
            addressBookList.add(addressBookConverter.convert(addressBook));
        });
        return addressBookList;
    }

    private List<AddressDTO> getAddressDTOS(Iterable<Address> addresses) {
        List<AddressDTO> addressList = new ArrayList<>();
        addresses.forEach(address -> {
            addressList.add(addressConverter.convert(address));
        });
        return addressList;
    }


}
