package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.AddressDto;
import uz.controlstudentserver.dto.DistrictDto;
import uz.controlstudentserver.dto.RegionDto;
import uz.controlstudentserver.entity.Country;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.servise.AddressService;
import uz.controlstudentserver.servise.CountryService;
import uz.controlstudentserver.servise.DistrictService;
import uz.controlstudentserver.servise.RegionService;

import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @Autowired
    DistrictService districtService;

    @Autowired
    RegionService regionService;

    @Autowired
    CountryService countryService;
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/saveOrEditCountry")
    public HttpEntity<?> saveCountry(@RequestBody Country country){
        ApiResponse response = countryService.saveOrEdit(country);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getAllCountry")
    public HttpEntity<?> getAllCountry(){
        return ResponseEntity.ok(countryService.findAll());
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getById/{id}")
    public HttpEntity<?> getByCountryId(@PathVariable Integer id){
        return ResponseEntity.ok(countryService.getOne(id));
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/saveOrEditRegion")
    public HttpEntity<?> saveOrEditRegion(@RequestBody RegionDto dto){
        ApiResponse response = regionService.saveOrEdit(dto);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getAllRegion")
    public HttpEntity<?> getAllRegion(){
        return ResponseEntity.ok(regionService.findAll());
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getByIdRegion/{id}")
    public HttpEntity<?> getByIdRegion(@PathVariable Integer id){
        ApiResponse one = regionService.getOne(id);
        return ResponseEntity.ok(one);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/saveOrEditDistrict")
    public HttpEntity<?> saveOrEditDistrict(@RequestBody DistrictDto dto){
        ApiResponse response = districtService.saveOrEdit(dto);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getAllDirection")
    public HttpEntity<?> getAllDirection(){
        return ResponseEntity.ok(districtService.findAll());
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getByIdDirection/{id}")
    public HttpEntity<?> getByIdDirection(@PathVariable Integer id){
        ApiResponse one = districtService.getOne(id);
        return ResponseEntity.ok(one);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/saveOrEditAddress")
    public HttpEntity<?> saveOrEditAddress(@RequestBody AddressDto dto){
        ApiResponse response = addressService.saveOrEdit(dto);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/getAddressById/{id}")
    public HttpEntity<?> getAddressById(@PathVariable UUID id){
        return ResponseEntity.ok(addressService.getOne(id));
    }

}
