package au.com.chandidev.addressbook.service;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.repository.AddressDTOFixture;
import au.com.chandidev.addressbook.repository.AddressRepository;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.repository.AddressBookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ValidationException;
import java.util.List;

/**
 *
 *
 * Created by chandimajanakantha on 29/10/17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressBookServiceImplTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private AddressBookConverter addressBookConverter = new AddressBookConverter();

    private AddressConverter addressConverter = new AddressConverter();

    private AddressBookService addressBookService = new AddressBookServiceImpl();



    private AddressDTO address1_1 = null;
    private AddressDTO address1_2 = null;
    private AddressDTO address1_3 = null;
    private AddressDTO address2_2 = null;
    private AddressDTO address2_3 = null;
    private AddressDTO address2_4 = null;

    AddressBookDTO addressBook1;
    AddressBookDTO addressBook2;

    @Before
    public void setup(){

        //Setup services
        ReflectionTestUtils.setField(addressConverter, "addressBookRepository", addressBookRepository);

        ReflectionTestUtils.setField(addressBookService, "addressConverter", addressConverter);
        ReflectionTestUtils.setField(addressBookService, "addressBookConverter", addressBookConverter);
        ReflectionTestUtils.setField(addressBookService, "addressRepository", addressRepository);
        ReflectionTestUtils.setField(addressBookService, "addressBookRepository", addressBookRepository);

        ReflectionTestUtils.setField(addressConverter, "modelMapper", modelMapper);
        ReflectionTestUtils.setField(addressBookConverter, "modelMapper", modelMapper);


        addressBook1 = AddressDTOFixture.newAddressBook("book1");
        addressBook2 = AddressDTOFixture.newAddressBook("book2");

        address1_1 = AddressDTOFixture.newAddress("John Smith", "+61403445666", "0355869393", "0244956677", 0 );
        address1_2 = AddressDTOFixture.newAddress("Lisa Smith", "+61403445667", "0355869393", null, 0 );
        address1_3 = AddressDTOFixture.newAddress("Andrew Smith", "+61403445668", "0355869393", null, 0 );
        address2_2 = AddressDTOFixture.newAddress("Lisa Smith", "+61403445667", "0355869393", null, 0 );
        address2_3 = AddressDTOFixture.newAddress("Andrew Smith", "+61403445668", "0355869393", null, 0 );
        address2_4 = AddressDTOFixture.newAddress("Sarah Smith", "+61403445669", "0355869393", "+61403445666", 0 );

    }

    @Test
    public void addAddressBook(){
        Assert.assertEquals(0, addressBookService.getAddressBooks().size());
        Assert.assertEquals(0, addressBook1.getId());

        AddressBookDTO savedAddressBook = addressBookService.saveAddressBook(addressBook1);

        //make sure that the objects are saved
        Assert.assertTrue( addressBookService.getAddressBooks().size() != 0);
        Assert.assertTrue(savedAddressBook.getId() != 0);
    }

    @Test(expected = ValidationException.class)
    public void duplicateAddressBookValidationFails(){
        AddressBookDTO savedAddressBook = addressBookService.saveAddressBook(addressBook1);

        //try to save another one with the same name
        addressBook1.setId(0);
        addressBookService.saveAddressBook(addressBook1);
    }

    @Test(expected = ValidationException.class)
    public void failsWhenTryingToCreateAddressOnNonExistingAddressBook(){
        addressBookService.saveAddress(address1_1);
    }

    @Test
    public void saveAddressBook(){
        AddressBookDTO savedAddressBok = addressBookService.saveAddressBook(addressBook1);
        address1_1.setAddressBookId(savedAddressBok.getId());
        AddressDTO savedAddress = addressBookService.saveAddress(address1_1);
        Assert.assertTrue(savedAddress.getId() != 0);
    }

    @Test
    public void getAddressByAddressBook() {
        saveAllRecords();

        List<AddressDTO> addressDTOList1 = addressBookService.getAddresses(addressBook1.getId());
        Assert.assertEquals(3, addressDTOList1.size());
        addressDTOList1.forEach(addressDTO -> {
            Assert.assertEquals(addressBook1.getId(), addressDTO.getAddressBookId());
        });

    }

    @Test
    public void getAllUniqueAddresses() {
        saveAllRecords();

        List<AddressDTO> addressDTOList1 = addressBookService.getAddresses();
        //there are only 4 unique addresses.
        Assert.assertEquals(4, addressDTOList1.size());

    }

    @Test
    public void deleteAddress() {
        saveAllRecords();

        List<AddressDTO> addressDTOList1 = addressBookService.getAddresses(addressBook1.getId());

        //now delete one of them
        addressBookService.deleteAddress(addressDTOList1.get(0).getId());

        List<AddressDTO> addressDTOList2 = addressBookService.getAddresses(addressBook1.getId());

        Assert.assertEquals(addressDTOList1.size() - 1, addressDTOList2.size());

    }

    @Test
    public void getAllAddressBooks() {
        saveAllRecords();

        List<AddressBookDTO> addressBooks = addressBookService.getAddressBooks();

        Assert.assertEquals(2, addressBooks.size());
    }


    private void saveAllRecords() {
        addressRepository.deleteAll();
        addressBookRepository.deleteAll();

        addressBook1 = addressBookService.saveAddressBook(addressBook1);
        addressBook2 = addressBookService.saveAddressBook(addressBook2);

        address1_1.setAddressBookId(addressBook1.getId());
        address1_1 = addressBookService.saveAddress(address1_1);

        address1_2.setAddressBookId(addressBook1.getId());
        address1_2 = addressBookService.saveAddress(address1_2);

        address1_3.setAddressBookId(addressBook1.getId());
        address1_3 = addressBookService.saveAddress(address1_3);

        address2_2.setAddressBookId(addressBook2.getId());
        address2_2 = addressBookService.saveAddress(address2_2);

        address2_3.setAddressBookId(addressBook2.getId());
        address2_3 = addressBookService.saveAddress(address2_3);

        address2_4.setAddressBookId(addressBook2.getId());
        address2_4 = addressBookService.saveAddress(address2_4);
    }

}
