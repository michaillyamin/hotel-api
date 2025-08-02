package com.gpsolutions.hotelapi.mapper;

import com.gpsolutions.hotelapi.model.dto.ContactsDto;
import com.gpsolutions.hotelapi.model.entity.Contacts;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsMapper {

    private final ModelMapper modelMapper;

    public Contacts convertToContacts(ContactsDto contactsDto) {
        return modelMapper.map(contactsDto, Contacts.class);
    }
}
