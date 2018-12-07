package cyclaeon.view;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleService;

@Controller
public class CreateCycleController {

	private final CycleService cycleService;
	
	public CreateCycleController(CycleService cycleService) {
		this.cycleService = cycleService;
	}
	
	@GetMapping("/create")
    public String cycles(Model model) {
        model.addAttribute("createCycleForm", new CreateCycleForm());
        return "create";
    }
	
	@PostMapping("create")
	public String cycles(@ModelAttribute CreateCycleForm createCycleForm) {
		cycleService.create(toDto(createCycleForm));
		return "redirect:cycles";
	}
	
	private static final CycleDto toDto(CreateCycleForm form) {
		return new CycleDto(form.getName());
	}
	
}