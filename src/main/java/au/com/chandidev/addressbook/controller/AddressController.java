package au.com.chandidev.addressbook.controller;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by chandimajanakantha on 26/10/17.
 */

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/addressBooks")
    public List<AddressBookDTO> getAddressBooks() {
        return addressBookService.getAddressBooks();
    }

    @PostMapping("/addressBook")
    public AddressBookDTO addAddressBook(@Valid @RequestBody AddressBookDTO addressBook) {
        return addressBookService.saveAddressBook(addressBook);
    }

    @PutMapping("/addressBook/{id}")
    public AddressBookDTO updateAddressBook(@Valid @RequestBody AddressBookDTO addressBook, @PathVariable int id) {
        addressBook.setId(id);
        return addressBookService.saveAddressBook(addressBook);
    }


    @GetMapping("/addresses/{addressBookId}")
    public Iterable<AddressDTO> getAddresses(@PathVariable int addressBookId) {
        return addressBookService.getAddresses(addressBookId);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity deleteAddress(@PathVariable int addressId) {

        addressBookService.deleteAddress(addressId);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/addresses")
    public Iterable<AddressDTO> getAddresses() {
        return addressBookService.getAddresses();
    }

    @PostMapping("/address")
    public AddressDTO addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        return addressBookService.saveAddress(addressDTO);
    }

    @PutMapping("/address/{id}")
    public AddressDTO updateAddress(@Valid @RequestBody AddressDTO addressDTO, @PathVariable int id) {
        addressDTO.setId(id);
        return addressBookService.saveAddress(addressDTO);
    }

    @ExceptionHandler
    void handleValidationException(ValidationException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
