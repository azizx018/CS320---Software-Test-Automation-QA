package Contract;

import org.assertj.core.api.Assertions;
import org.example.Contact.Contact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContactTest {

    @Test
    void successfulCreation() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertAll("Contact creation assertions",
                () -> Assertions.assertThat(contact).isNotNull(),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("id", "1"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("firstName", "John"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("lastName", "Doe"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("number", "1234567890"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("address", "123 Main St")
        );

    }

    @Test
    void successfulSetters() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Jane");
        contact.setLastName("Doe");
        contact.setNumber("9876543210");
        contact.setAddress("456 Elm St");
        assertAll("Contact successful setters assertions",
                () -> Assertions.assertThat(contact).isNotNull(),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("firstName", "Jane"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("lastName", "Doe"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("number", "9876543210"),
                () -> Assertions.assertThat(contact).hasFieldOrPropertyWithValue("address", "456 Elm St")
        );
    }

    @ParameterizedTest
    @CsvSource({
            ", John, Doe, 123456789, 123 Main St, id cannot be null",//null id
            "028273738292928387382, John, Doe, 123456789, 123 Main St, id must be between 1 and 10 characters",//id is too long
            "'', Jane, Doe, 123456789, 865 main street, id cannot be blank",//blank id
            "1, , Doe, 987654321, 456 Elm St, first name cannot be null",//null first name
            "2, jibjabthegreatonethatisreallymighty, Doe, 987654321, 456 Elm St, first name must be between 1 and 10 characters",// first name is too long
            "3, Mark, , 123456789, 123 Main St, last name cannot be null",//null last name
            "3, Mark, jibjabthegreatonethatisreallymighty , 123456789, 123 Main St, last name must be between 1 and 10 characters",//last name is too long
            "4, Jane, Doe,8900jjhjhh, 456 Elm St, number must be a number", //not a valid number
            "5, Jane, Doe, 1234567890, , address cannot be null",//null address
            "6, Jane, Doe, 1234567890, thisisareallylongaddressthatistoolong , address must be between 1 and 30 characters",// address is too long
            "7,Jack, Barlow, 934, 123 Main St, number must be 10 digits",})//phone needs to be 10 digits
    void failedCreation(String id, String firstName, String lastName, String number, String address, String message) throws Exception{
        System.out.println(id + " " + firstName + " " + lastName + " " + number + " " + address + " " + message);
        assertThatThrownBy(() -> new Contact(id, firstName, lastName, number, address))
                .isNotNull()
                .hasMessage(message);
    }

    @CsvSource({
            ", first name cannot be null",
            "'    ', first name cannot be blank",
            "ThisisareallylongfirstNameWow, first name must be between 1 and 10 characters",
    })
    @ParameterizedTest
    void settingFirstName(String firstName, String message) throws Exception{
        Contact contact = new Contact("3", "Paul", "Blart", "1234567890","12345 test lane");
        assertThatThrownBy(() -> contact.setFirstName(firstName)).isNotNull().hasMessage(message);

    }
    @CsvSource({
            ", last name cannot be null",
            "'    ', last name cannot be blank",
            "ThisisareallylongfirstNameWow, last name must be between 1 and 10 characters",
    })
    @ParameterizedTest
    void settingLastName(String lastName, String message) throws Exception{
        Contact contact = new Contact("3", "Paul", "Blart", "1234567890","12345 test lane");
        assertThatThrownBy(() -> contact.setLastName(lastName)).isNotNull().hasMessage(message);

    }
    @CsvSource({
            ", address cannot be null",
            "'    ', address cannot be blank",
            "ThisisareallylongfirstAddressWow, address must be between 1 and 30 characters",
    })
    @ParameterizedTest
    void settingAddress(String address, String message) throws Exception{
        Contact contact = new Contact("3", "Paul", "Blart", "1234567890","12345 test lane");
        assertThatThrownBy(() -> contact.setAddress(address)).isNotNull().hasMessage(message);

    }
    @CsvSource({
            ", number cannot be null",
            "ThisisareallylongfirstAddressWow, number must be a number",
            "9877777, number must be 10 digits",
    })
    @ParameterizedTest
    void settingPhone(String number, String message) throws Exception{
        Contact contact = new Contact("3", "Paul", "Blart", "1234567890","12345 test lane");
        assertThatThrownBy(() -> contact.setNumber(number)).isNotNull().hasMessage(message);

    }


}
