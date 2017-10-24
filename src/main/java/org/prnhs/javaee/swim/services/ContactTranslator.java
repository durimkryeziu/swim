package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.dto.ContactsDto;
import org.prnhs.javaee.swim.core.entity.Contacts;

public class ContactTranslator {

    public static ContactsDto toDto(Contacts contacts) {

        if (contacts == null) {
            throw new IllegalArgumentException("Hey, you were supposed to give me a contact");
        }

        ContactsDto dto = new ContactsDto();
        dto.setKey(contacts.getId());
        dto.setFirstName(contacts.getFirstName());
        dto.setMiddleName(contacts.getMiddleName());
        dto.setLastName(contacts.getLastName());
        dto.setTitle(contacts.getTitle());

        return dto;
    }

    public static Contacts toEntity(ContactsDto dto) {

        if(dto == null){
            throw new IllegalArgumentException("Hey, you were supposed to give me a contact");
        }

        Contacts contacts = new Contacts();
        contacts.setId(dto.getKey());
        contacts.setFirstName(dto.getFirstName());
        contacts.setMiddleName(dto.getMiddleName());
        contacts.setLastName(dto.getLastName());
        contacts.setTitle(dto.getTitle());

        return contacts;
    }
}
