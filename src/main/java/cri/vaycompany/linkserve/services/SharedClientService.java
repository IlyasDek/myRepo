package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.SharedClientDTO;
import cri.vaycompany.linkserve.models.SharedClient;
//import cri.vaycompany.linkserve.repositories.ConnectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SharedClientService {

//    private final SharedClientRepository sharedClientRepository;
//    private final ConnectionRepository connectionRepository;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public SharedClientService(SharedClientRepository sharedClientRepository,
//                               ConnectionRepository connectionRepository,
//                               ModelMapper modelMapper) {
//        this.sharedClientRepository = sharedClientRepository;
//        this.connectionRepository = connectionRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public SharedClientDTO createSharedClient(SharedClientDTO sharedClientDTO) {
//        // Проверяем, есть ли связь между провайдерами
//        boolean connectionExists = connectionRepository.existsByProviderOneAndProviderTwo(
//                sharedClientDTO.getSenderProvider().getId(),
//                sharedClientDTO.getReceiverProvider().getId());
//
//        if (!connectionExists) {
//            throw new IllegalArgumentException("Connection does not exist between providers");
//        }
//
//        SharedClient sharedClient = modelMapper.map(sharedClientDTO, SharedClient.class);
//        SharedClient createdSharedClient = sharedClientRepository.save(sharedClient);
//        return modelMapper.map(createdSharedClient, SharedClientDTO.class);
//    }
//
//    public SharedClientDTO confirmSharedClient(Long sharedClientId) {
//        Optional<SharedClient> existingSharedClient = sharedClientRepository.findById(sharedClientId);
//
//        if (existingSharedClient.isPresent()) {
//            SharedClient sharedClientToConfirm = existingSharedClient.get();
//            sharedClientToConfirm.setConfirmed(true);
//
//            SharedClient confirmedSharedClient = sharedClientRepository.save(sharedClientToConfirm);
//            return modelMapper.map(confirmedSharedClient, SharedClientDTO.class);
//        } else {
//            throw new IllegalArgumentException("No shared client with the provided ID found");
//        }
//    }
//
//    public List<SharedClientDTO> getSharedClientsByProvider(Long providerId) {
//        List<SharedClient> sharedClients = sharedClientRepository.findBySenderProviderIdOrReceiverProviderId(providerId, providerId);
//        return sharedClients.stream()
//                .map(sharedClient -> modelMapper.map(sharedClient, SharedClientDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<SharedClientDTO> getSharedClientById(Long id) {
//        Optional<SharedClient> sharedClient = sharedClientRepository.findById(id);
//        return sharedClient.map(sharedClientEntity -> modelMapper.map(sharedClientEntity, SharedClientDTO.class));
//    }
//
//    public void markSharedClientAsDeleted(Long sharedClientId) {
//        Optional<SharedClient> existingSharedClient = sharedClientRepository.findById(sharedClientId);
//        if (existingSharedClient.isPresent()) {
//            SharedClient sharedClientToDelete = existingSharedClient.get();
//            sharedClientToDelete.setDeleted(true);
//            sharedClientRepository.save(sharedClientToDelete);
//        } else {
//            throw new IllegalArgumentException("No shared client with the provided ID found");
//        }
//    }
}

