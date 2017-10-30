package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.model.Address;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.model.AddressBook;
import au.com.chandidev.addressbook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by chandimajanakantha on 28/10/17.
 */
@Component
public class AddressConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressBookRepository addressBookRepository;

    public Address convert(AddressDTO addressDTO) {

        Address address = modelMapper.map(addressDTO, Address.class);
        Optional<AddressBook> addressBookOptional= addressBookRepository.findById(addressDTO.getAddressBookId());

        if (addressBookOptional.isPresent()) {
            address.setAddressBook(addressBookOptional.get());
        } else
        {
            throw new javax.validation.ValidationException("Invalid address book");
        }
        return address;
    }

    public AddressDTO convert(Address address) {
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        addressDTO.setAddressBookId(address.getAddressBook().getId());
        return addressDTO;
    }
}
