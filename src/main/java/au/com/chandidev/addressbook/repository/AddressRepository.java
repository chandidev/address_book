package au.com.chandidev.addressbook.repository;

import au.com.chandidev.addressbook.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chandimajanakantha on 26/10/17.
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query("SELECT a from Address a where a.addressBook.id = :addressBookId")
    List<Address> findAddressByAddressBookId(@Param("addressBookId") int addressBookId);

}
