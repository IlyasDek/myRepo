package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.dto.ComplaintDTO;
import cri.vaycompany.linkserve.models.Complaint;
import cri.vaycompany.linkserve.services.ComplaintService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ModelMapper modelMapper;

    @Autowired
    public ComplaintController(ComplaintService complaintService, ModelMapper modelMapper) {
        this.complaintService = complaintService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ComplaintDTO createComplaint(@RequestBody ComplaintDTO complaintDto) {
        ComplaintDTO savedComplaint = complaintService.createComplaint(complaintDto);
        return savedComplaint;
    }

    @GetMapping
    public List<ComplaintDTO> getAllComplaints() {
        List<ComplaintDTO> complaints = complaintService.getAllComplaints();
        return complaints.stream()
                .map(complaint -> modelMapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id)
                .map(complaint -> ResponseEntity.ok(modelMapper.map(complaint, ComplaintDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDTO> updateComplaint(@PathVariable Long id, @RequestBody ComplaintDTO complaintDto) {
        if (!id.equals(complaintDto.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        ComplaintDTO updatedComplaint = complaintService.updateComplaint(complaintDto);
        if (updatedComplaint == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedComplaint);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
