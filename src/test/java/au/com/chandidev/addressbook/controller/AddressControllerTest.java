package au.com.chandidev.addressbook.controller;

import au.com.chandidev.addressbook.dto.AddressBookDTO;
import au.com.chandidev.addressbook.dto.AddressDTO;
import au.com.chandidev.addressbook.repository.AddressDTOFixture;
import au.com.chandidev.addressbook.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by chandimajanakantha on 29/10/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressBookService addressBookService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testNewAddressBook() throws Exception {
        AddressBookDTO addressBookDTO = AddressDTOFixture.validAddressBook();

        this.mvc.perform(post("/address/addressBook/").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressBookDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(addressBookService, times(1)).saveAddressBook(addressBookDTO);

    }

    @Test
    public void testNewAddressBookValidation() throws Exception {
        AddressBookDTO addressBookDTO = AddressDTOFixture.validAddressBook();
        addressBookDTO.setName("");

        this.mvc.perform(post("/address/addressBook/").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressBookDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(addressBookService, times(0)).saveAddressBook(addressBookDTO);

    }



    @Test
    public void updateAddressBook() throws Exception {
        AddressBookDTO addressBookDTO = AddressDTOFixture.validAddressBook();

        this.mvc.perform(put("/address/addressBook/2").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressBookDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        addressBookDTO.setId(2);
        verify(addressBookService, times(1)).saveAddressBook(addressBookDTO);

    }


    @Test
    public void testNewAddress() throws Exception {
        AddressDTO addressDTO = AddressDTOFixture.validAddress(1);


        this.mvc.perform(post("/address/address/").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(addressBookService, times(1)).saveAddress(addressDTO);

    }

    @Test
    public void testUpdateAddress() throws Exception {
        AddressDTO addressDTO = AddressDTOFixture.validAddress(1);


        this.mvc.perform(put("/address/address/5").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addressDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        addressDTO.setId(5);

        verify(addressBookService, times(1)).saveAddress(addressDTO);

    }

    @Test
    public void getAddressBooks() throws Exception {
        this.mvc.perform(get("/address/addressBooks/"))
                .andExpect(status().isOk());

        verify(addressBookService, times(1)).getAddressBooks();

    }

    @Test
    public void getAddresses() throws Exception {
        this.mvc.perform(get("/address/addresses/2"))
                .andExpect(status().isOk());

        verify(addressBookService, times(1)).getAddresses(2);

    }

    @Test
    public void getUniqueAddresses() throws Exception {
        this.mvc.perform(get("/address/addresses"))
                .andExpect(status().isOk());

        verify(addressBookService, times(1)).getAddresses();

    }

    @Test
    public void deleteAddresses() throws Exception {
        this.mvc.perform(delete("/address/addresses/3"))
                .andExpect(status().isNotFound());

        verify(addressBookService, times(1)).deleteAddress(3);

    }

}
