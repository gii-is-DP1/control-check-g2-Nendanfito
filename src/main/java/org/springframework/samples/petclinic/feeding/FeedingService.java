package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class FeedingService {
	
	@Autowired
	private FeedingRepository fr;
	
	@Transactional
    public List<Feeding> getAll(){
        return fr.findAll();
    }
    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return fr.findAllFeedingTypes();
    }
    @Transactional
    public FeedingType getFeedingType(String typeName) {
        return fr.getFeedingType(typeName);
    }
    
    @Transactional(rollbackOn = UnfeasibleFeedingException.class)
    public Feeding save(Feeding f) throws UnfeasibleFeedingException {
    	PetType pt1 =f.getPet().getType();
    	PetType pt2=f.getFeedingType().getPetType();
        if (pt1!=pt2) {            	
        	throw new UnfeasibleFeedingException();
        }else
            fr.save(f);
        return f;
    }

    
}
