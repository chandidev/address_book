package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.model.AddressBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chandimajanakantha on 28/10/17.
 */
@Component
public class AddressBookConverter {

    @Autowired
    private ModelMapper modelMapper;

    public AddressBook convert(AddressBookDTO addressBookDTO) {
        return  modelMapper.map(addressBookDTO, AddressBook.class);
    }

    public AddressBookDTO convert(AddressBook addressBook) {
        return modelMapper.map(addressBook, AddressBookDTO.class);
    }

}
