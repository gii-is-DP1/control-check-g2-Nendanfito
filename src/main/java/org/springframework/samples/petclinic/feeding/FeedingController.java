package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/feeding")
public class FeedingController {
	
	@Autowired
	private FeedingService fs;
	
	@Autowired
	private PetService ps;
	
	@PostMapping(path = "/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap modelMap) throws UnfeasibleFeedingException {
		if (result.hasErrors()) {
			modelMap.addAttribute("feeding",feeding);
			return "feedings/createOrUpdateFeedingForm";
		}
		else {
			fs.save(feeding);
			modelMap.addAttribute("message","Feeding agregado correctamente");
			return "welcome";
		}
	}
	
	@GetMapping()
	public String feedingTypes(ModelMap modelMap) {
		List<FeedingType> feedingTypes = fs.getAllFeedingTypes();
		modelMap.addAttribute("feedingTypes", feedingTypes);
		return "feedings/createOrUpdateFeedingForm";
	}
	
	@GetMapping()
	public String pets(ModelMap modelMap) {
		List<Pet> pets = ps.getAllPets();
		modelMap.addAttribute("pets", pets);
		return "feedings/createOrUpdateFeedingForm";
	}
}
   
