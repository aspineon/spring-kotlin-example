package de.meshcloud.example.springkotlin.web;

import de.meshcloud.example.springkotlin.repositories.ProjectRepository;
import de.meshcloud.example.springkotlin.services.CostCalculationService;
import de.meshcloud.example.springkotlin.util.PerformanceLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private CostCalculationService costCalculationService;

    private ProjectRepository projectRepository;

    public ProjectController(
            CostCalculationService costCalculationService,
            ProjectRepository projectRepository
    ) {
        this.costCalculationService = costCalculationService;
        this.projectRepository = projectRepository;
    }

    @GetMapping("{projectId}/costs")
    public Double calculateCosts(@PathVariable("projectId") Long projectId) {
        return PerformanceLogger.logPerformance(
                () -> costCalculationService.calculateCosts(projectId),
                "Cost Calculation for " + projectId
        );
    }

}
