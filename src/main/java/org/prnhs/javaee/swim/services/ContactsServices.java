package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.ContactsDao;
import org.prnhs.javaee.swim.core.dto.ContactsDto;
import org.prnhs.javaee.swim.core.entity.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsServices {

    @Autowired
    private ContactsDao dao;

    public ContactsDto save(ContactsDto contactsDto) {

        if (contactsDto == null) {
            throw new IllegalArgumentException("Hey, you were supposed to give me a contact");
        }
        
        Contacts contacts = dao.findOne(contactsDto.getId());

        if (contacts == null) {

            contacts = ContactTranslator.toEntity(contactsDto);
        } 
        
        contacts = dao.save(contacts);
        
        ContactsDto savedContact = ContactTranslator.toDto(contacts);
        
        return savedContact;
    }

    public ContactsDto getById(Integer id){
        
        ContactsDto dto = null;
        Contacts contacts = dao.findOne(id);
        if(contacts != null){
            dto = ContactTranslator.toDto(contacts);
        }
        
        return dto;                
    }
    
    public List<ContactsDto> getAll(){
        
        Iterable<Contacts> contacts = dao.findAll();
        Iterator<Contacts> it = contacts.iterator();
        List<ContactsDto> dtos = new ArrayList<>();
        
        while(it.hasNext()) {
            Contacts c = it.next();
            ContactsDto dto = ContactTranslator.toDto(c);
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    public void delete(Integer id) {
        Contacts contacts = dao.findOne(id);
        if (contacts != null){
            dao.delete(contacts);
        }
    }
}