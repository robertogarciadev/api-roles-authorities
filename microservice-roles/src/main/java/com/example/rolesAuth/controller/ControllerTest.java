package com.example.rolesAuth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/controller/test")
@Tag(name = "Test", description = "EndPoints test")
public class ControllerTest {

    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('DELETE')")
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminProfile() {
        return ResponseEntity.ok("Perfil sólo para ADMIN");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getUserProfile() {
        return ResponseEntity.ok("Perfil para ADMIN y USER");
    }

    @GetMapping("/invited")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('INVITED')")
    public ResponseEntity<?> getInvitedProfile() {
        return ResponseEntity.ok(
                "Perfil para ADMIN, USER e INVITADO");
    }




    //PERMISSION
    @GetMapping("/read")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> permissionRead(){
        return ResponseEntity.ok("Tienes permiso para READ");
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> permissionCreate(){
        return ResponseEntity.ok("Tienes permiso para CREATE");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<?> permissionUpdate(){
        return ResponseEntity.ok("Tienes permiso para UPDATE");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<?> permissionDelete(){
        return ResponseEntity.ok("Tienes permiso para DELETE");
    }

}
