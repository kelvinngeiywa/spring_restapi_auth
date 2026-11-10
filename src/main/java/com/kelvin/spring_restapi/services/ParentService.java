package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Parent;
import com.kelvin.spring_restapi.entities.dtos.AddParentDto;
import com.kelvin.spring_restapi.entities.dtos.ParentResponseDto;
import com.kelvin.spring_restapi.entities.dtos.TeacherResponseDto;
import com.kelvin.spring_restapi.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParentService {

    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository){
        this.parentRepository=parentRepository;
    }

    public ParentResponseDto addParent(AddParentDto parentDto){
        if(parentRepository.existsByEmail(parentDto.email())){
            throw new DuplicateEntryException("Email exists");
        }

        if(parentRepository.existsByPhoneNumber(parentDto.phoneNumber())){
            throw new DuplicateEntryException("Phone number exists!!");
        }

        Parent newParent=new Parent();
        newParent.setName(parentDto.name());
        newParent.setEmail(parentDto.email());
        newParent.setPhoneNumber(parentDto.phoneNumber());
        newParent.setResidence(parentDto.residence());

        parentRepository.save(newParent);

        return toParentResponseDto(newParent);
//        return new ParentResponseDto(
//                newParent.getName(),newParent.getEmail(),newParent.getPhoneNumber(),newParent.getResidence()
//        );

    }

    public List<ParentResponseDto> getAllParents(){
        return parentRepository.findAll().stream().map(this::toParentResponseDto).toList();
//        return parentRepository.findAll().stream().map(
//                parent-> new ParentResponseDto(
//                        parent.getName(),parent.getEmail(),parent.getPhoneNumber(),parent.getResidence()
//                )
//        ).toList();

    }

    public ParentResponseDto getParentById(UUID id){
        Parent parent=parentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Parent not found!!")
        );
        return toParentResponseDto(parent);

//        return new ParentResponseDto(
//                parent.getName(),parent.getEmail(),parent.getPhoneNumber(),parent.getResidence()
//        );
    }

    public ParentResponseDto updateParent(UUID id,AddParentDto parentDto){
        Parent parent= parentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Parent not found!!"));

        if(!parent.getEmail().equals(parentDto.email()) && parentRepository.existsByEmail(parentDto.email())){
            throw new DuplicateEntryException("Email exists");
        }
        if(!parent.getPhoneNumber().equals(parentDto.phoneNumber()) &&
                parentRepository.existsByPhoneNumber(parentDto.phoneNumber())){
            throw new DuplicateEntryException("Phone number exists");
        }

        parent.setName(parentDto.name());
        parent.setEmail(parentDto.email());
        parent.setPhoneNumber(parentDto.phoneNumber());
        parent.setResidence(parentDto.residence());

        parentRepository.save(parent);

        return toParentResponseDto(parent);
    }

    public String deleteParent(UUID id){
        Parent parent= parentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Parent not found!!!"));

        parentRepository.delete(parent);
        return "";
    }

    private ParentResponseDto toParentResponseDto(Parent parent){
        return new ParentResponseDto(
                parent.getName(),parent.getEmail(),parent.getPhoneNumber(),parent.getResidence()
        );
    }
}
