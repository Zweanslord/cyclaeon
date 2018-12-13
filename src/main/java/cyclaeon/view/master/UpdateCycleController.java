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

	@GetMapping("/master/{key}/cycle/{id}")
	public String form(@PathVariable String key, @PathVariable String id, Model model) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		CycleDto cycleDto = cycleQueryService.findCycle(id)
				.orElseThrow(() -> new IllegalStateException(String.format("Did not find Cycle '%s'.", id)));

		var updateCycleForm = new UpdateCycleForm(cycleDto.name, cycleDto.description);

		model.addAttribute("cycle", cycleDto);
		model.addAttribute("updateCycleForm", updateCycleForm);
		return "updateCycle";
	}

	@PostMapping("/master/{key}/cycle/{id}")
	public String update(@PathVariable String key, @PathVariable String id, @ModelAttribute UpdateCycleForm updateCycleForm) {
		if (!cycleMasterKey.equals(key)) {
			throw new IllegalArgumentException();
		}

		cycleApplicationService.updateDescriptionAndName(id, updateCycleForm.getName(), updateCycleForm.getDescription());

		return "redirect:/master/" + cycleMasterKey + "/cycle/" + id;
	}

}