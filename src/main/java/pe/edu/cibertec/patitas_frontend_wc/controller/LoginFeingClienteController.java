package pe.edu.cibertec.patitas_frontend_wc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.patitas_frontend_wc.Cliente.LogoutCliente;
import pe.edu.cibertec.patitas_frontend_wc.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_frontend_wc.dto.LogoutResponseDTO;
import pe.edu.cibertec.patitas_frontend_wc.viewmodel.LoginModel;

@RestController

@RequestMapping
@CrossOrigin
public class LoginFeingClienteController {


    @Autowired
    LogoutCliente logoutCliente;

    @PostMapping("/logout-async")
    public LogoutResponseDTO logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        System.out.println("Consumiendo con Feign Client");
        //validar campos de entrada
        if(logoutRequestDTO.tipoDocumento() == null || logoutRequestDTO.tipoDocumento().trim().length()==0
                ||logoutRequestDTO.numeroDocumento() == null || logoutRequestDTO.numeroDocumento().trim().length()==0) {
            LoginModel loginModel = new LoginModel("91","Error: Debe completar correctamente sus credenciales","");

            return new LogoutResponseDTO(false,null,"Error: Debe completar correctamente sus credenciales"
            );
        }
        try {
            ResponseEntity<LogoutResponseDTO> responseEntity = logoutCliente.logout(logoutRequestDTO);

            if(responseEntity.getStatusCode().is2xxSuccessful()){

                LogoutResponseDTO logoutResponseDTO = responseEntity.getBody();
                if (logoutResponseDTO.resultado().equals(true)){
                    //algo
                    return new LogoutResponseDTO(true,logoutResponseDTO.fecha(),logoutResponseDTO.mensajeError());
                }else {
                    //otra cosa
                    return new LogoutResponseDTO(false,null,"Error: No se pudo cerrar sesion");
                }
            }
            else {
                return new LogoutResponseDTO(false,null,"Error: No se pudo cerrar sesion");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LogoutResponseDTO(false,null,"Error: Error en el logout");
        }
    }
}
