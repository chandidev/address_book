package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.model.Address;
import au.com.chandidev.addressbook.model.AddressBook;
import au.com.chandidev.addressbook.repository.AddressBookRepository;
import au.com.chandidev.addressbook.repository.AddressDTOFixture;
import au.com.chandidev.addressbook.repository.AddressFixture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

/**
 * Created by chandimajanakantha on 29/10/17.
 */
public class AddressConverterTest {

    private AddressConverter addressConverter = new AddressConverter();

    private ModelMapper modelMapper = new ModelMapper();

    private AddressBookRepository addressBookRepository;

    @Before
    public void setup(){

        ReflectionTestUtils.setField(addressConverter, "modelMapper", modelMapper);
        addressBookRepository = Mockito.mock(AddressBookRepository.class);
        ReflectionTestUtils.setField(addressConverter, "addressBookRepository", addressBookRepository);
    }

    @Test
    public void testModeltoDTOConversion() {

        AddressBook addressBook = AddressFixture.newAddressBook("book1");
        Address address = AddressFixture.newAddress("John Smith", "0233334444",
                "0244443333", "0344445555", addressBook);
        addressBook.setId(22);
        address.setId(25);

        AddressDTO addressDTO = addressConverter.convert(address);

        Assert.assertEquals(address.getId(), addressDTO.getId());
        Assert.assertEquals(address.getName(), addressDTO.getName());
        Assert.assertEquals(address.getHomePhone(), addressDTO.getHomePhone());
        Assert.assertEquals(address.getMobilePhone(), addressDTO.getMobilePhone());
        Assert.assertEquals(address.getOfficePhone(), addressDTO.getOfficePhone());
        Assert.assertEquals(address.getAddressBook().getId(), addressDTO.getAddressBookId());
    }

    @Test
    public void testDTOtoModelConversion() {

        AddressBookDTO addressBookDTO = AddressDTOFixture.newAddressBook("book1");
        addressBookDTO.setId(22);
        AddressDTO addressDTO = AddressDTOFixture.newAddress("John Smith", "mobilePhone",
                "homePhone", "officePhone", addressBookDTO.getId());

        AddressBook mockAddressBook = Mockito.mock(AddressBook.class);
        Mockito.when(mockAddressBook.getId()).thenReturn(addressBookDTO.getId());
        Optional<AddressBook> addressBookOptional = Optional.of(mockAddressBook);

        Mockito.when(addressBookRepository.findById(addressBookDTO.getId())).thenReturn(addressBookOptional);
        Address address = addressConverter.convert(addressDTO);

        Assert.assertEquals(addressDTO.getId(), address.getId());
        Assert.assertEquals(addressDTO.getName(), address.getName());
    }
}
