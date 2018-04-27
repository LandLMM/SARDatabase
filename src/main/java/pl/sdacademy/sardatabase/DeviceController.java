package pl.sdacademy.sardatabase;

import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sardatabase.Device;

@RestController
public class DeviceController {
private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("unauth/device/{id}")
    public Device getById(@PathVariable("id") int id){

        return deviceService.findById(id);
    }

    @PostMapping("/auth/device")
    public Device create (@RequestBody Device device) {

        return deviceService.create(device);
    }
}
