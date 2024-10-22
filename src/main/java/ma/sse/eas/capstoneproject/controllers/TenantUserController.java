package ma.sse.eas.capstoneproject.controllers;

import ma.sse.eas.capstoneproject.persistence.dtos.UserDto;
import ma.sse.eas.capstoneproject.persistence.entities.User;
import ma.sse.eas.capstoneproject.persistence.entities.Tenant;
import ma.sse.eas.capstoneproject.security.JwtHelper;
import ma.sse.eas.capstoneproject.services.TenantService;
import ma.sse.eas.capstoneproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantUserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserService userService;

    // Tenant Endpoints
    @PostMapping("/create")
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.createTenant(tenant.getName(), tenant.getDescription());
    }

    @GetMapping("/{tenantId}")
    public Tenant getTenantById(@PathVariable Long tenantId) {
        return tenantService.getTenantById(tenantId);
    }

    @PutMapping("/update/{tenantId}")
    public Tenant updateTenant(@PathVariable Long tenantId, @RequestBody Tenant tenant) {
        return tenantService.updateTenant(tenantId, tenant.getName(), tenant.getDescription());
    }

    @DeleteMapping("/delete/{tenantId}")
    public void deleteTenant(@PathVariable Long tenantId) {
        tenantService.deleteTenant(tenantId);
    }

    // User Endpoints
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        return JwtHelper.generateToken(authentication);
    }

    @PostMapping("/{tenantId}/users/create")
    public boolean createUser(@RequestBody UserDto dto, Long tenantId) {
        if (!check(dto))
            return false;

        userService.createUser(dto.getUsername(), dto.getPassword(), dto.getFirstName(), dto.getLastName(),
                dto.getEmail(), dto.getPhone(),tenantId);
        return true;
    }

    @GetMapping("/{tenantId}/users")
    public List<User> getUsersByTenant(@PathVariable Long tenantId) {
        return userService.getUsersByTenant(tenantId);
    }

    @PatchMapping("/{userId}")
    public User update(@PathVariable("userId") long userId, @RequestBody UserDto dto) {
        if (userId < 1)
            throw new RuntimeException();

        userService.update(userId, dto.getPassword(), dto.getNewPassword(), dto.getFirstName(), dto.getLastName(),
                dto.getEmail(), dto.getPhone());

        return new User(dto.getUsername(), null, dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.getPhone());
    }


    private boolean check(UserDto userDto) {
        return (userDto.getUsername() != null && userDto.getPassword() != null && userDto.getFirstName() != null
                && userDto.getLastName() != null && userDto.getEmail() != null && userDto.getPhone() != null);
    }
}
