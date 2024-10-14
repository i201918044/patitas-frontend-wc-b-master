package pe.edu.cibertec.patitas_frontend_wc.Cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.patitas_frontend_wc.dto.LogoutResponseDTO;

public interface LogoutCliente {

    @PostMapping("/logout")
    ResponseEntity<LogoutResponseDTO> logout(@RequestBody LogoutResponseDTO logoutResponseDTO);



}
