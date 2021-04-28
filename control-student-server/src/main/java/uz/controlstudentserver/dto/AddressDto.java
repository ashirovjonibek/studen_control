package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private UUID id;
    private String address;
    private Integer districtId;
    private String addrType;

    public AddressDto(String address, Integer districtId, String addrType) {
        this.address = address;
        this.districtId = districtId;
        this.addrType = addrType;
    }
}
