package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.ComplaintDTO;
import cri.vaycompany.linkserve.models.Complaint;
import cri.vaycompany.linkserve.repositories.ComplaintRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, ModelMapper modelMapper) {
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
    }

    public ComplaintDTO createComplaint(ComplaintDTO complaintDTO) {
        Complaint complaint = modelMapper.map(complaintDTO, Complaint.class);
        Complaint savedComplaint = complaintRepository.save(complaint);
        return modelMapper.map(savedComplaint, ComplaintDTO.class);
    }

    public Optional<ComplaintDTO> getComplaintById(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        return complaint.map(value -> modelMapper.map(value, ComplaintDTO.class));
    }

    public List<ComplaintDTO> getAllComplaints(){
        List<Complaint> complaints = complaintRepository.findAll();
        return complaints.stream()
                .map(complaint -> modelMapper.map(complaint, ComplaintDTO.class))
                .collect(Collectors.toList());
    }

    public ComplaintDTO updateComplaint(ComplaintDTO complaintDTO) {
        Complaint complaint = modelMapper.map(complaintDTO, Complaint.class);
        Complaint updatedComplaint = complaintRepository.save(complaint);
        return modelMapper.map(updatedComplaint, ComplaintDTO.class);
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
