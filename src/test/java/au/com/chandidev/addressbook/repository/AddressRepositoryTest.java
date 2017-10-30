package au.com.chandidev.addressbook.repository;

import au.com.chandidev.addressbook.model.Address;
import au.com.chandidev.addressbook.model.AddressBook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

import java.util.List;

/**
 * testing only queries. The basic Dao operations are not tested since it's provided by the framework.
 *
 * Created by chandimajanakantha on 29/10/17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    private Address address1_1 = null;
    private Address address1_2 = null;
    private Address address1_3 = null;
    private Address address2_2 = null;
    private Address address2_3 = null;

    @Before
    public void setup(){

        //delete all data in in-memory database.
        addressRepository.deleteAll();
        addressBookRepository.deleteAll();

        AddressBook addressBook1 = addressBookRepository.save(AddressFixture.newAddressBook("book1"));
        AddressBook addressBook2 = addressBookRepository.save(AddressFixture.newAddressBook("book2"));

        Address addres1_1 = addressRepository
                .save(AddressFixture.newAddress("John Smith", "+61403445666", "0355869393", "0244956677", addressBook1 ));
        Address addres1_2 = addressRepository
                .save(AddressFixture.newAddress("Lisa Smith", "+61403445667", "0355869393", null, addressBook1 ));
        Address addres1_3 = addressRepository
                .save(AddressFixture.newAddress("Andrew Smith", "+61403445668", "0355869393", null, addressBook1 ));
        Address addres2_2 = addressRepository
                .save(AddressFixture.newAddress("Lisa Smith", "+61403445667", "0355869393", null, addressBook2 ));
        Address addres2_3 = addressRepository
                .save(AddressFixture.newAddress("Andrew Smith", "+61403445668", "0355869393", null, addressBook2 ));


    }

    @Test
    public void findAddressByAddressBookId() {

        List<Address> addressList = addressRepository.findAddressByAddressBookId(1);
        Assert.assertEquals(3, addressList.size());
        addressList.forEach(address -> {
            Assert.assertEquals(1, address.getAddressBook().getId());
        });

        addressList = addressRepository.findAddressByAddressBookId(2);
        Assert.assertEquals(2, addressList.size());
        addressList.forEach(address -> {
            Assert.assertEquals(2, address.getAddressBook().getId());
        });

    }


}
