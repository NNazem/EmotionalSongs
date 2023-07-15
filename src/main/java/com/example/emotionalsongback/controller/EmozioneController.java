package com.example.emotionalsongback.controller;

import com.example.emotionalsongback.dto.EmozioneDto;
import com.example.emotionalsongback.entity.Emozione;
import com.example.emotionalsongback.repository.EmozioneRepository;
import com.example.emotionalsongback.service.EmozioneService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/canzone/{canzoneId}/emozioni")
@AllArgsConstructor
public class EmozioneController {


    private EmozioneService emozioneService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<String> addEmozione(@PathVariable Long canzoneId,@RequestBody EmozioneDto emozioneDto){
        String response = emozioneService.addEmozione(canzoneId,emozioneDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Set<Emozione>> getEmozioni(@PathVariable Long canzoneId){
        Set<Emozione> emozioniAssociate = emozioneService.getEmozioni(canzoneId);
        return new ResponseEntity<>(emozioniAssociate,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{emozioneId}")
    public ResponseEntity<String> deleteEmozione(@PathVariable Long canzoneId,@PathVariable Long emozioneId){
        String response = emozioneService.deleteEmozione(canzoneId,emozioneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
