package cyclaeon.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleService;

@Controller
public class CreateCycleController {
	
	private final String cycleMasterKey;
	private final CycleService cycleService;
	
	@Autowired
	public CreateCycleController(CycleService cycleService,
			@Value("${cycle.master.key}") String cycleMasterKey) {
		this.cycleService = cycleService;
		this.cycleMasterKey = cycleMasterKey;
	}
	
	@GetMapping("/master/{key}/create")
    public String cycles(@PathVariable String key, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}
		
        model.addAttribute("createCycleForm", new CreateCycleForm());
        return "create";
    }
	
	@PostMapping("/master/{key}/create")
	public String cycles(@PathVariable String key, @ModelAttribute CreateCycleForm createCycleForm) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}
		
		cycleService.create(toDto(createCycleForm));
		return "redirect:/cycles";
	}
	
	private static final CycleDto toDto(CreateCycleForm form) {
		return new CycleDto(form.getName());
	}
	
}