package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.model.AddressBook;
import au.com.chandidev.addressbook.repository.AddressDTOFixture;
import au.com.chandidev.addressbook.repository.AddressFixture;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by chandimajanakantha on 29/10/17.
 */
public class AddressBookConverterTest {

    private AddressBookConverter addressBookConverter = new AddressBookConverter();

    private ModelMapper modelMapper = new ModelMapper();

    @Before
    public void setup(){
        ReflectionTestUtils.setField(addressBookConverter, "modelMapper", modelMapper);
    }

    @Test
    public void testModeltoDTOConversion() {

        AddressBook addressBook = AddressFixture.newAddressBook("book1");
        addressBook.setId(22);

        AddressBookDTO addressBookDTO = addressBookConverter.convert(addressBook);

        Assert.assertEquals(addressBook.getId(), addressBookDTO.getId());
        Assert.assertEquals(addressBook.getName(), addressBookDTO.getName());
    }

    @Test
    public void testDTOtoModelConversion() {

        AddressBookDTO addressBookDTO = AddressDTOFixture.newAddressBook("book1");
        addressBookDTO.setId(22);

        AddressBook addressBook = addressBookConverter.convert(addressBookDTO);

        Assert.assertEquals(addressBookDTO.getId(), addressBook.getId());
        Assert.assertEquals(addressBookDTO.getName(), addressBook.getName());
    }
}
