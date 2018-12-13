package cyclaeon.view.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cyclaeon.service.CycleApplicationService;

@Controller
public class CreateCycleController {

	private final String cycleMasterKey;
	private final CycleApplicationService cycleApplicationService;

	@Autowired
	public CreateCycleController(CycleApplicationService cycleApplicationService,
			@Value("${cycle.master.key}") String cycleMasterKey) {
		this.cycleApplicationService = cycleApplicationService;
		this.cycleMasterKey = cycleMasterKey;
	}

	@GetMapping("/master/{key}/create")
	public String form(@PathVariable String key, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		model.addAttribute("createCycleForm", new CreateCycleForm());
		return "create";
	}

	@PostMapping("/master/{key}/create")
	public String create(@PathVariable String key, @ModelAttribute CreateCycleForm createCycleForm) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		cycleApplicationService.create(createCycleForm.getId());
		return "redirect:/master/" + key;
	}

}