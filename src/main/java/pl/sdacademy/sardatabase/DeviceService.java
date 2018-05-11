package pl.sdacademy.sardatabase;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DeviceService {
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device create(Device device) {
        return deviceRepository.save(device);
    }

    public Device findById(int id) {
        return deviceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device update(int id, Device device) {
        Device persistedDevice = findById(id);
        persistedDevice.setManufacturerName(device.getManufacturerName());
        persistedDevice.setModelName(device.getModelName());
        deviceRepository.save(persistedDevice);
        return persistedDevice;
    }

    public Device delete(int id) {
        Device device = findById(id);
        deviceRepository.delete(device);
        return device;
    }

}
