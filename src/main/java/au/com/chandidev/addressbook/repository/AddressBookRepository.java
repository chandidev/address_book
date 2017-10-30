package au.com.chandidev.addressbook.repository;

import au.com.chandidev.addressbook.model.AddressBook;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chandimajanakantha on 26/10/17.
 */
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {

    public AddressBook findAddressBooksByName(String name);

}
