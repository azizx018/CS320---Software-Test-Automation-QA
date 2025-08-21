package Contract;

import org.example.Contact.Contact;
import org.example.Contact.ContactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContactServiceTest {

    @BeforeEach
    void setUp() {
        ContactService.getInstance().contactsList.clear();
    }
    @Test
    void getInstance() {
        assertThat(ContactService.getInstance()).isNotNull();
    }

    @Test
    void testAddContact() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        ContactService.getInstance().addContact(contact);
        assertThat(ContactService.getInstance().contactsList.containsValue(contact));
    }
    @Test
    void testAddMultipleContacts() throws Exception {
        Contact contact1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("2", "Jane", "Doe", "9876543210", "456 Elm St");
        ContactService.getInstance().addContact(contact1);
        ContactService.getInstance().addContact(contact2);
        assertAll("Contact add assertions",
                () -> assertThat(ContactService.getInstance().contactsList.containsValue(contact1)).isTrue(),
                () -> assertThat(ContactService.getInstance().contactsList.containsValue(contact2)).isTrue());

    }
    @Test
    void testAddDuplicateContactId() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1", "Jane", "Doe", "9876543210", "456 Elm St");
        ContactService.getInstance().addContact(contact);
        ContactService.getInstance().addContact(contact2);
        assertThat(ContactService.getInstance().addContact(contact2)).isFalse();
    }

    @Test
    void testRemoveContact() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        ContactService.getInstance().addContact(contact);
        ContactService.getInstance().removeContact(contact.getId());
        assertThat(ContactService.getInstance().contactsList.containsValue(contact)).isFalse();
    }

    @Test
    void testRemoveNonExistentContact() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThat(ContactService.getInstance().removeContact(contact.getId())).isFalse();
    }

    @Test
    void testUpdateContact() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        ContactService.getInstance().addContact(contact);
        Contact updatedContact = new Contact("1", "Jane", "Doe", "1234567890", "9876 Main St");
        ContactService.getInstance().updateContact(contact.getId(), updatedContact);
        assertThat(ContactService.getInstance().contactsList.get(contact.getId()))
                .hasFieldOrPropertyWithValue("firstName", "Jane")
                .hasFieldOrPropertyWithValue("lastName", "Doe")
                .hasFieldOrPropertyWithValue("number", "1234567890")
                .hasFieldOrPropertyWithValue("address", "9876 Main St");

    }
    @Test
    void testUpdateNonExistentContact() throws Exception {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThat(ContactService.getInstance().updateContact(contact.getId(), contact)).isFalse();
    }

}
