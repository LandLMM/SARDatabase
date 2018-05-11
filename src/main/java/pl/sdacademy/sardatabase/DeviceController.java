package pl.sdacademy.sardatabase;

import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sardatabase.Device;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DeviceController {
    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("unauth/device/{id}")
    public Device getById(@PathVariable("id") int id) {
        return deviceService.findById(id);
    }

    @PutMapping("unauth/device/{id}")
    public Device getById(@PathVariable("id") int id, @RequestBody @Valid Device device) {
        return deviceService.findById(id);
    }

    @DeleteMapping("/unauth/device/{id}")
    public Device delete(@PathVariable("id") int id) {
        return deviceService.delete(id);
    }

    @PostMapping("/unauth/device")
    public Device create(@RequestBody @Valid Device device) {
        return deviceService.create(device);
    }

    @GetMapping("/unauth/device")
    public List<Device> getAll(){
        return deviceService.findAll();
    }
}
