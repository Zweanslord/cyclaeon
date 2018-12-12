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
import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleQueryService;

@Controller
public class UpdateCycleController {

	private final CycleApplicationService cycleApplicationService;
	private final CycleQueryService cycleQueryService;
	private final String cycleMasterKey;

	@Autowired
	public UpdateCycleController(
			CycleApplicationService cycleApplicationService,
			CycleQueryService cycleQueryService,
			@Value("${cycle.master.key}") String cycleMasterKey) {
		this.cycleApplicationService = cycleApplicationService;
		this.cycleQueryService = cycleQueryService;
		this.cycleMasterKey = cycleMasterKey;
	}

	@GetMapping("/master/{key}/cycle/{name}")
	public String form(@PathVariable String key, @PathVariable String name, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		CycleDto cycleDto = cycleQueryService.findCycle(name);
		var updateCycleForm = new UpdateCycleForm(cycleDto.description);

		model.addAttribute("updateCycleForm", updateCycleForm);
		return "updateCycle";
	}

	@PostMapping("/master/{key}/cycle/{name}")
	public String update(@PathVariable String key, @PathVariable String name,
			@ModelAttribute UpdateCycleForm updateCycleForm) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		cycleApplicationService.updateDescription(name, updateCycleForm.getDescription());

		return "redirect:/master/" + cycleMasterKey + "/cycle/" + name;
	}

}