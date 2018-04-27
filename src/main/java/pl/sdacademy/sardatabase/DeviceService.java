package pl.sdacademy.sardatabase;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeviceService {
        private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device create(Device device){

            return deviceRepository.save(device);
        }

     public Device findById(int id){

        return deviceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

     }
    }
