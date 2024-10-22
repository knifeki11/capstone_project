package ma.sse.eas.capstoneproject.services;

import ma.sse.eas.capstoneproject.persistence.entities.Tenant;
import ma.sse.eas.capstoneproject.persistence.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public Tenant createTenant(String name, String description) {
        if (tenantRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Tenant name already exists");
        }

        Tenant tenant = new Tenant(name, description);
        return tenantRepository.save(tenant);
    }

    public Tenant getTenantById(Long tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant updateTenant(Long tenantId, String name, String description) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found"));

        tenant.setName(name);
        tenant.setDescription(description);
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Long tenantId) {
        tenantRepository.deleteById(tenantId);
    }
}
