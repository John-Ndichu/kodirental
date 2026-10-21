package com.johnndichu.kodirental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.johnndichu.kodirental.dtos.RentSubmissionRequest;
import com.johnndichu.kodirental.dtos.RentSubmissionResponse;
import com.johnndichu.kodirental.entity.HouseType;
import com.johnndichu.kodirental.service.RentSubmissionService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Rent Submissions", description = "Submit and view rent reports")
@RestController
@RequestMapping("/rent-submissions")
public class RentSubmissionController {
    public final RentSubmissionService rentSubmissionService;

    public RentSubmissionController(RentSubmissionService rentSubmissionService) {
        this.rentSubmissionService = rentSubmissionService;
    }

    @Operation(
            summary = "Submit a rent report",
            description = "Reports what you actually pay for a house type in an area. " +
                    "If you already have an active submission for this exact segment " +
                    "(area + house type + utilities status), it is updated in place " +
                    "rather than creating a duplicate. New submissions may be " +
                    "automatically flagged if they're a statistical outlier compared " +
                    "to existing data for that segment. Requires authentication."
    )

    @PostMapping
    public RentSubmissionResponse submit(@Valid @RequestBody RentSubmissionRequest request, Authentication authentication) {
        String contributorEmail = authentication.getName();
        return rentSubmissionService.submit(request, contributorEmail);
    }

    @Operation(
            summary = "List active rent submissions",
            description = "Returns active rent submissions, optionally filtered by " +
                    "area and/or house type. Requires authentication."
    )

    @GetMapping
    public List<RentSubmissionResponse> listSubmissions(
            @Parameter(description = "UUID of the area to filter by") @RequestParam(required = false) UUID areaId,
            @Parameter(description = "House type to filter by") @RequestParam(required = false) HouseType houseType
    ) {
        return rentSubmissionService.listSubmissions(areaId, houseType);
    }

    @Operation(
            summary = "List my rent submissions",
            description = "Returns active rent submissions belonging to the logged-in " +
                    "contributor. Requires authentication."
    )
    @GetMapping("/mine")
    public List<RentSubmissionResponse> listMySubmissions(Authentication authentication) {
        String contributorEmail = authentication.getName();
        return rentSubmissionService.listMySubmissions(contributorEmail);
    }
}
